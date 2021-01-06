/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import lombok.experimental.UtilityClass;

import org.apache.commons.io.FilenameUtils;

import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.file.copy.CopyFileExtensions;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.FileSearchExtensions;
import de.alpharogroup.io.file.FileExtension;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.lang.PackageExtensions;
import de.alpharogroup.resourcebundle.file.namefilter.PropertiesResourceBundleFilenameFilter;

/**
 * The class {@link PropertiesFileExtensions} provides methods for load properties file
 */
@UtilityClass
public final class PropertiesFileExtensions
{
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
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties getLocalPropertiesFromClass(final Class<?> componentClass,
		final Class<?> defaultClass, final Locale locale) throws IOException
	{
		// Try to find the properties file and the resource
		Properties properties = null;
		if (componentClass != null)
		{
			properties = PropertiesFileExtensions.loadPropertiesFromClassObject(componentClass,
				locale);
		}
		else
		{
			properties = PropertiesFileExtensions.loadPropertiesFromClassObject(defaultClass,
				locale);
		}
		return properties;
	}

	/**
	 * Gets the project name from the 'project.properties'. In this properties file is only a
	 * reference of the artifactId from the pom.
	 *
	 * @return the project name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getProjectName() throws IOException
	{
		final Properties projectProperties = PropertiesFileExtensions
			.loadProperties("project.properties");
		if (projectProperties != null)
		{
			final String projectName = projectProperties.getProperty("artifactId");
			if (projectName == null)
			{
				throw new RuntimeException(
					"No properties key 'artifactId' found in the properties file project.properties exist.");
			}
			return projectName;
		}
		else
		{
			throw new RuntimeException("No properties file project.properties exist.");
		}
	}

	/**
	 * Gets the redundant keys in properties files from the given directory. The search is recursive
	 * and finds all properties files. The result is a map with key the properties file and the
	 * found redundant keys as a map.
	 *
	 * @param dirToSearch
	 *            the dir to search
	 * @return the redundant keys
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Map<File, Map<String, List<String>>> getRedundantKeys(final File dirToSearch)
		throws IOException
	{
		final List<File> foundFiles = FileSearchExtensions.findAllFiles(dirToSearch,
			FileSearchExtensions.getSearchFilePattern("properties"));

		final Map<String, List<String>> linesMap = new LinkedHashMap<>();
		final Map<File, Map<String, List<String>>> fileMap = new LinkedHashMap<>();
		for (final File file : foundFiles)
		{
			final List<String> lines = PropertiesFileExtensions.removeComments(file);
			final Properties p = PropertiesFileExtensions.loadProperties(file);

			for (final Map.Entry<Object, Object> entry : p.entrySet())
			{
				final String key = ((String)entry.getKey()).trim();
				for (final String line : lines)
				{
					if (line.startsWith(key))
					{
						final char nextChar = line.charAt(key.length());
						// char[] anc = {nextChar};
						// String nc = new String(anc);
						if (nextChar == '.')
						{
							continue;
						}
						else if (nextChar == '=' || nextChar == ':' || nextChar == ' ')
						{
							if (!linesMap.containsKey(key))
							{
								final List<String> dl = new ArrayList<>();
								dl.add(line);
								linesMap.put(key, dl);
							}
							else
							{
								final List<String> dl = linesMap.get(key);
								dl.add(line);
								linesMap.put(key, dl);
							}
						}
						else
						{
							throw new RuntimeException("nextChar is '" + nextChar + "'");
						}
					}
				}
			}

			final Map<String, List<String>> duplicateKeys = new LinkedHashMap<>();
			for (final Map.Entry<String, List<String>> entry : linesMap.entrySet())
			{
				if (1 < entry.getValue().size())
				{
					duplicateKeys.put(entry.getKey(), entry.getValue());
				}
			}
			if (0 < duplicateKeys.size())
			{
				fileMap.put(file, duplicateKeys);
			}
		}
		return fileMap;
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
		return PropertiesExtensions.loadProperties(propertiesFile);
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
	 * Load {@link Properties} object from the given arguments.
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param object
	 *            the object for get the package path
	 * @param propertiesFilename
	 *            the properties filename
	 * @return the loaded {@link Properties} or null if the loading process failed.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> Properties loadProperties(final T object, final String propertiesFilename)
		throws IOException
	{
		Properties properties = null;
		final String packagePath = PackageExtensions.getPackagePathWithSlash(object);
		final String propertiespath = packagePath + propertiesFilename;
		properties = PropertiesFileExtensions.loadProperties(object.getClass(),
			propertiespath) != null
				? PropertiesFileExtensions.loadProperties(object.getClass(), propertiespath)
				: PropertiesFileExtensions.getLocalPropertiesFromClass(object.getClass(),
					object.getClass(), null);
		return properties;
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
	public static Properties loadPropertiesFromClassObject(final Class<?> clazz,
		final Locale locale) throws IOException
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
				properties = PropertiesFileExtensions.loadProperties(pathAndFilename);
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
						properties = PropertiesFileExtensions.loadProperties(pathAndFilename);
					}
				}
			}
		}

		if (null == properties)
		{
			propertiesName = new StringBuilder();
			propertiesName.append(clazz.getSimpleName())
				.append(FileExtension.PROPERTIES.getExtension());
			filename = propertiesName.toString().trim();
			pathAndFilename = packagePath + filename;
			final URL url = ClassExtensions.getResource(clazz, filename);

			if (url != null)
			{
				absoluteFilename = url.getFile();
			}
			else
			{
				properties = PropertiesFileExtensions.loadProperties(pathAndFilename);
				missedFiles.add("File with filename '" + filename + "' does not exists.");
			}

			if (null != absoluteFilename)
			{
				propertiesFile = new File(absoluteFilename);
			}
			if ((null != propertiesFile) && propertiesFile.exists())
			{
				properties = PropertiesFileExtensions.loadProperties(pathAndFilename);
			}
		}
		return properties;
	}

	/**
	 * Creates a backup file from the given properties file.
	 *
	 * @param file
	 *            the file
	 * @return the backup file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File newBackupOf(final File file) throws IOException
	{
		return CopyFileExtensions.newBackupOf(file, Charset.forName("ISO-8859-1"),
			Charset.forName("UTF-8"));
	}

	/**
	 * Removes the comments from the given properties file.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<String> removeComments(final File propertiesFile) throws IOException
	{
		if (propertiesFile != null && !propertiesFile.getName()
			.matches(FileSearchExtensions.getSearchFilePattern("properties")))
		{
			throw new IllegalArgumentException("The given file is not an properties file.");
		}
		final List<String> lines = ReadFileExtensions.readLinesInList(propertiesFile);
		for (final Iterator<String> itr = lines.iterator(); itr.hasNext();)
		{
			final String line = itr.next();
			if (line.startsWith("#") || line.trim().length() == 0)
			{
				itr.remove();
			}
		}
		return lines;
	}

	/**
	 * Resolves all the available languages for the given resource bundle name in the given bundle
	 * package. Note the default resource bundle is excluded.
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

}
