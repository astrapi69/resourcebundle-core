/**
 * The MIT License
 *
 * Copyright (C) 2012 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.resourcebundle.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import de.alpharogroup.file.FileExtension;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.lang.PackageExtensions;
import de.alpharogroup.resourcebundle.file.namefilter.PropertiesResourceBundleFilenameFilter;

/**
 * The Class {@link PropertiesExtensions} provides methods loading properties and other related operations for
 * properties like find redundant values or getting all available languages from a bundle.
 */
public final class PropertiesExtensions
{

	/**
	 * The Constant SEARCH_FILE_PATTERN is a regex for searching java and html files.
	 */
	public static final String SEARCH_FILE_PATTERN = "([^\\s]+(\\.(?i)(java|html|htm))$)";
	/**
	 * The Constant PROPERTIES_DELIMITERS contains all valid delimiters for properties files.
	 */
	public static final String[] PROPERTIES_DELIMITERS = { "=", ":", " " };

	/** The LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(PropertiesExtensions.class.getName());

	/**
	 * Finds redundant values from the given Properties object and saves it to a Map.
	 *
	 * @param properties
	 *            The Properties to check.
	 * @return A map that contains the redundant value as the key of the map and a List(as value of
	 *         the map) of keys that have the redundant value.
	 */
	public static Map<String, List<String>> findRedundantValues(final Properties properties)
	{
		final Map<String, List<String>> reverseEntries = new LinkedHashMap<>();
		for (final Map.Entry<Object, Object> entry : properties.entrySet())
		{
			final String key = (String)entry.getKey();
			final String value = (String)entry.getValue();
			if (!reverseEntries.containsKey(value))
			{
				final List<String> keys = new ArrayList<>();
				keys.add(key);
				reverseEntries.put(value, keys);
			}
			else
			{
				final List<String> keys = reverseEntries.get(value);
				keys.add(key);
			}
		}
		final Map<String, List<String>> redundantValues = new LinkedHashMap<>();
		for (final Map.Entry<String, List<String>> entry : reverseEntries.entrySet())
		{
			final String key = entry.getKey();
			final List<String> keys = entry.getValue();
			if (1 < keys.size())
			{
				redundantValues.put(key, keys);
			}
		}
		return redundantValues;
	}

	/**
	 * Gets the properties.
	 *
	 * @param componentClass
	 *            the component class
	 * @param defaultClass
	 *            the default class
	 * @param locale
	 *            the locale
	 * @return the properties
	 * @throws Exception
	 *             the exception
	 */
	public static Properties getLocalPropertiesFromClass(final Class<?> componentClass,
		final Class<?> defaultClass, final Locale locale) throws Exception
	{
		// Try to find the properties file and the resource
		Properties properties = null;
		if (componentClass != null)
		{
			properties = PropertiesExtensions.loadPropertiesFromClassObject(componentClass, locale);
		}
		else
		{
			properties = PropertiesExtensions.loadPropertiesFromClassObject(defaultClass, locale);
		}
		return properties;
	}

	/**
	 * Finds all keys with the same key prefixes from the given Properties and saves them to a Map
	 * with the prefix as a key and holds a List with the whole keys the starts with the same key
	 * prefix.
	 *
	 * @param enProperties
	 *            the en properties
	 * @return the matched prefix lists
	 */
	public static Map<String, List<String>> getMatchedPrefixLists(final Properties enProperties)
	{
		final Enumeration<?> e = enProperties.propertyNames();
		final Map<String, List<String>> matchedPrefixes = new LinkedHashMap<>();
		while (e.hasMoreElements())
		{
			final String key = (String)e.nextElement();
			final int lastIndex = key.lastIndexOf(".");
			String subKey = null;
			if (0 < lastIndex)
			{
				subKey = key.substring(0, lastIndex);
			}
			else
			{
				subKey = key;
			}
			if (matchedPrefixes.containsKey(subKey))
			{
				final List<String> fullKeys = matchedPrefixes.get(subKey);
				fullKeys.add(key);
			}
			else
			{
				final List<String> fullKeys = new ArrayList<>();
				fullKeys.add(key);
				matchedPrefixes.put(subKey, fullKeys);
			}
		}
		return matchedPrefixes;
	}

	/**
	 * Finds the property parameters from the given propertyValue.
	 *
	 * @param propertyValue
	 *            the property value
	 * @return the property parameters as a list.
	 */
	public static List<String> getPropertyParameters(final String propertyValue)
	{
		final List<String> parameterList = new ArrayList<>();
		final Pattern pattern = Pattern.compile("\\{.*?\\}");
		final Matcher matcher = pattern.matcher(propertyValue);
		while (matcher.find())
		{
			final String parameter = matcher.group();
			final String at = parameter.substring(1, parameter.length() - 1);
			parameterList.add(at);
		}
		return parameterList;
	}

	/**
	 * Load properties.
	 *
	 * @param clazz
	 *            the clazz
	 * @param name
	 *            the package path with the file name
	 * @return the properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadProperties(final Class<?> clazz, final String name)
		throws IOException
	{
		Properties properties = loadProperties(name);
		if (properties == null)
		{
			final InputStream is = ClassExtensions.getResourceAsStream(clazz.getClass(), name);
			if (is != null)
			{
				properties = new Properties();
				properties.load(is);
			}
		}
		return properties;
	}

	/**
	 * Load {@link Properties} object from the given arguments.
	 *
	 * @param <T> the generic type of the object
	 * @param object the object for get the package path
	 * @param propertiesFilename the properties filename
	 * @return the loaded {@link Properties} or null if the loading process failed.
	 */
	public static<T> Properties loadProperties(final T object, final String propertiesFilename)
	{
		Properties properties = null;
		final String packagePath = PackageExtensions.getPackagePathWithSlash(object);
		final String propertiespath = packagePath + propertiesFilename;
		try
		{
			properties = PropertiesExtensions.loadProperties(object.getClass(), propertiespath);
		}
		catch (final IOException e)
		{
			LOGGER.error("Loading properties file '"+propertiespath+"' with method 'PropertiesExtensions.loadProperties(object.getClass(), propertiespath)' failed.", e);
		}
		if (properties == null)
		{
			try
			{
				properties = PropertiesExtensions.getLocalPropertiesFromClass(object.getClass(), object.getClass(), null);
			}
			catch (final Exception e)
			{
				LOGGER.error("Loading properties file '"+propertiespath+"' with method 'PropertiesExtensions.getLocalPropertiesFromClass(object.getClass(), object.getClass(), null)' failed.", e);
			}
		}
		return properties;
	}

	/**
	 * Load properties.
	 *
	 * @param clazz
	 *            the clazz
	 * @param packagePath
	 *            the package path without the file name
	 * @param fileName
	 *            the file name
	 * @return the properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadProperties(final Class<?> clazz, final String packagePath,
		final String fileName) throws IOException
	{
		return loadProperties(clazz, packagePath + fileName);
	}

	/**
	 * Load a Properties-object from the given File-object.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @return the properties or null if the file is not found.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadProperties(final File propertiesFile) throws IOException
	{
		Properties properties = null;
		InputStream is = null;
		if (propertiesFile.exists())
		{
			is = propertiesFile.toURI().toURL().openStream();
			if (is != null)
			{
				properties = new Properties();
				properties.load(is);
			}
		}
		else
		{
			throw new FileNotFoundException(propertiesFile.getName() + " not found.");
		}
		return properties;
	}

	/**
	 * Gives a Properties-object from the given packagepath.
	 *
	 * @param packagePath
	 *            The package-path and the name from the resource as a String.
	 * @return The Properties-object from the given packagepath.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadProperties(final String packagePath) throws IOException
	{
		Properties properties = null;
		final URL url = ClassExtensions.getResource(packagePath);
		if (url != null)
		{
			properties = new Properties();
			properties.load(url.openStream());
		}
		else
		{
			final InputStream is = ClassExtensions.getResourceAsStream(packagePath);
			if (is != null)
			{
				properties = new Properties();
				properties.load(is);
			}
		}
		return properties;
	}

	/**
	 * Load properties.
	 *
	 * @param packagePath
	 *            the package path without the file name
	 * @param fileName
	 *            the file name
	 * @return the properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadProperties(String packagePath, String fileName) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		packagePath = FilenameUtils.normalize(packagePath);
		final String slash = "/";
		if (packagePath.startsWith(slash))
		{
			// remove first slash...
			if (packagePath.endsWith(slash))
			{
				sb.append(packagePath.substring(1, packagePath.length()));
			}
			else
			{
				// append slash at the end...
				sb.append(packagePath.substring(1, packagePath.length())).append(slash);
			}
		}
		else
		{
			if (packagePath.endsWith(slash))
			{
				// remove first char...
				sb.append(packagePath);
			}
			else
			{
				// remove first char...
				sb.append(packagePath).append(slash);
			}
		}
		packagePath = sb.toString().trim();
		sb = new StringBuilder();
		if (fileName.startsWith(slash))
		{
			sb.append(fileName.substring(1, fileName.length()));
		}
		fileName = sb.toString().trim();
		return loadProperties(packagePath + fileName);
	}

	/**
	 * Load the properties file from the given class object. The filename from the properties file
	 * is the same as the simple name from the class object and it looks at the same path as the
	 * given class object. If locale is not null than the language will be added to the filename
	 * from the properties file.
	 *
	 * @param clazz
	 *            the clazz
	 * @param locale
	 *            the locale
	 * @return the properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadPropertiesFromClassObject(final Class<?> clazz, final Locale locale)
		throws IOException
	{
		if (null == clazz)
		{
			throw new IllegalArgumentException("Class object must not be null!!!");
		}
		StringBuilder propertiesName = new StringBuilder();
		Properties properties = null;
		String language = null;
		String filename = null;
		String pathAndFilename = null;
		File propertiesFile = null;
		String absoluteFilename = null;
		final String packagePath = PackageExtensions.getPackagePathWithSlash(clazz);
		final List<String> missedFiles = new ArrayList<>();
		if (null != locale)
		{
			propertiesName.append(clazz.getSimpleName());
			language = locale.getLanguage();
			if ((null != language) && !language.isEmpty())
			{
				propertiesName.append("_").append(language);
			}

			final String country = locale.getCountry();
			if ((null != country) && !country.isEmpty())
			{
				propertiesName.append("_").append(country);
			}
			propertiesName.append(FileExtension.PROPERTIES.getExtension());
			filename = propertiesName.toString().trim();
			pathAndFilename = packagePath + filename;
			URL url = ClassExtensions.getResource(clazz, filename);

			if (url != null)
			{
				absoluteFilename = url.getFile();
			}
			else
			{
				missedFiles.add("File with filename '" + filename + "' does not exists.");
			}

			if (null != absoluteFilename)
			{
				propertiesFile = new File(absoluteFilename);
			}

			if ((null != propertiesFile) && propertiesFile.exists())
			{
				properties = PropertiesExtensions.loadProperties(pathAndFilename);
			}
			else
			{
				propertiesName = new StringBuilder();
				if (null != locale)
				{
					propertiesName.append(clazz.getSimpleName());
					language = locale.getLanguage();
					if ((null != language) && !language.isEmpty())
					{
						propertiesName.append("_").append(language);
					}
					propertiesName.append(FileExtension.PROPERTIES.getExtension());
					filename = propertiesName.toString().trim();
					pathAndFilename = packagePath + filename;
					url = ClassExtensions.getResource(clazz, filename);

					if (url != null)
					{
						absoluteFilename = url.getFile();
					}
					else
					{
						missedFiles.add("File with filename '" + filename + "' does not exists.");
					}
					if (null != absoluteFilename)
					{
						propertiesFile = new File(absoluteFilename);
					}
					if ((null != propertiesFile) && propertiesFile.exists())
					{
						properties = PropertiesExtensions.loadProperties(pathAndFilename);
					}
				}
			}
		}

		if (null == properties)
		{
			propertiesName = new StringBuilder();
			propertiesName.append(clazz.getSimpleName()).append(
				FileExtension.PROPERTIES.getExtension());
			filename = propertiesName.toString().trim();
			pathAndFilename = packagePath + filename;
			final URL url = ClassExtensions.getResource(clazz, filename);

			if (url != null)
			{
				absoluteFilename = url.getFile();
			}
			else
			{
				properties = PropertiesExtensions.loadProperties(pathAndFilename);
				missedFiles.add("File with filename '" + filename + "' does not exists.");
			}

			if (null != absoluteFilename)
			{
				propertiesFile = new File(absoluteFilename);
			}
			if ((null != propertiesFile) && propertiesFile.exists())
			{
				properties = PropertiesExtensions.loadProperties(pathAndFilename);
			}
		}
		if (properties == null)
		{
			for (final String string : missedFiles)
			{
				LOGGER.info(string);
			}
		}

		return properties;
	}

	/**
	 * Resolves all the available languages for the given resource bundle name in the given bundle
	 * package.
	 * Note the default resource bundle is excluded.
	 *
	 * @param bundlepackage
	 *            The package that contains the properties files.
	 * @param bundlename
	 *            The name of the resource bundle.
	 * @return a Set of String objects with the available languages excluding the default.
	 */
	public static Set<String> resolveAvailableLanguages(final String bundlepackage,
		final String bundlename)
	{
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final File root = new File(loader.getResource(bundlepackage.replace('.', '/')).getFile());
		final File[] files = root.listFiles(new PropertiesResourceBundleFilenameFilter(bundlename));

		final Set<String> languages = new TreeSet<>();
		for (final File file : files)
		{
			final String language = file.getName()
				.replaceAll("^" + bundlename + "(_)?|\\.properties$", "");
			if ((language != null) && !language.isEmpty())
			{
				languages.add(language);
			}
		}
		return languages;
	}


	/**
	 * Converts the given xml file to the given properties file.
	 *
	 * @param properties
	 *            the properties file. The xml file does not have to exist.
	 * @param xml
	 *            the xml file with the properties to convert.
	 * @param comment
	 *            the comment
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void toProperties(final File properties, final File xml, final String comment)
		throws FileNotFoundException, IOException
	{
		toProperties(new FileOutputStream(properties), new FileInputStream(xml), comment);
	}

	/**
	 * Converts the given xml InputStream to the given properties OutputStream.
	 *
	 * @param properties
	 *            the properties file. The xml file does not have to exist.
	 * @param xml
	 *            the xml file with the properties to convert.
	 * @param comment
	 *            the comment
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void toProperties(final OutputStream properties, final InputStream xml,
		final String comment) throws FileNotFoundException, IOException
	{
		final Properties prop = new Properties();
		prop.loadFromXML(xml);
		prop.store(properties, comment);
	}

	/**
	 * Converts the given properties file to the given xml file.
	 *
	 * @param properties
	 *            the properties file.
	 * @param xml
	 *            the xml file to write in. The xml file does not have to exist.
	 * @param comment
	 *            the comment
	 * @param encoding
	 *            the encoding for the xml file.
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void toXml(final File properties, final File xml, final String comment,
		final String encoding) throws FileNotFoundException, IOException
	{
		toXml(new FileInputStream(properties), new FileOutputStream(xml), comment, encoding);
	}

	/**
	 * Converts the given properties InputStream to the given xml OutputStream.
	 *
	 * @param properties
	 *            the properties InputStream.
	 * @param xml
	 *            the xml OutputStream to write in.
	 * @param comment
	 *            the comment
	 * @param encoding
	 *            the encoding for the xml file.
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void toXml(final InputStream properties, final OutputStream xml,
		final String comment, final String encoding) throws FileNotFoundException, IOException
	{
		final Properties prop = new Properties();
		prop.load(properties);
		prop.storeToXML(xml, comment, encoding);
	}

	/**
	 * Private constructor.
	 */
	private PropertiesExtensions()
	{
	}

	/**
	 * Gets the project name from the 'project.properties'.
	 * In this properties file is only a reference of the artifactId from the pom.
	 *
	 * @return the project name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getProjectName() throws IOException {
		final Properties projectProperties = PropertiesExtensions.loadProperties("project.properties");
		if(projectProperties != null) {
			final String projectName = projectProperties.getProperty("artifactId");
			if(projectName == null) {
				throw new RuntimeException("No properties key 'artifactId' found in the properties file project.properties exist.");
			}
			return projectName;
		} else {
			throw new RuntimeException("No properties file project.properties exist.");
		}
	}

}
