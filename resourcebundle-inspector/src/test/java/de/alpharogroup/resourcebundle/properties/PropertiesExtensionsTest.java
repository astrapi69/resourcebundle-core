/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.collections.SortedProperties;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.lang.PackageExtensions;
import de.alpharogroup.lang.model.AnnotationModel;
import de.alpharogroup.lang.model.ClassModel;
import de.alpharogroup.lang.model.MethodModel;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;

/**
 * Test class for the class {@link de.alpharogroup.lang.PropertiesExtensions}.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class PropertiesExtensionsTest
{

	private void getClassModels(final Map<String, List<String>> matchedPrefixes,
		final String localeAnnotationName, final Map<String, ClassModel> interfaces,
		final Properties ruProperties)
	{
		for (final Map.Entry<String, List<String>> entry : matchedPrefixes.entrySet())
		{
			final String key = entry.getKey();
			final List<String> propertyKeyValues = entry.getValue();
			ClassModel classModel = null;
			Map<String, MethodModel> methods = null;
			final int lastIndex = key.lastIndexOf(".");
			String packageName = "";
			String interfaceName = null;
			String methodName = "";
			MethodModel method = null;
			if (0 < lastIndex)
			{
				interfaceName = key.substring(lastIndex + 1, key.length());
				if (interfaces.containsKey(interfaceName))
				{
					classModel = interfaces.get(interfaceName);
					methods = classModel.getMethods();
					for (final String value : propertyKeyValues)
					{
						methodName = value.substring(key.length() + 1, value.length());
						method = methods.get(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(PropertiesExtensions.getPropertyParameters(propertyValue));
					}
				}
				else
				{
					classModel = new ClassModel();
					classModel.setClassName(interfaceName);
					methods = new LinkedHashMap<>();
					classModel.setMethods(methods);
					packageName = key.substring(0, lastIndex);
					classModel.setPackageName(packageName);
					for (final String value : propertyKeyValues)
					{
						methodName = value.substring(key.length() + 1, value.length());
						method = new MethodModel();
						method.setMethodAnnotations(new ArrayList<AnnotationModel>());
						methods.put(methodName, method);
						method.setMethodName(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(PropertiesExtensions.getPropertyParameters(propertyValue));
					}
					interfaces.put(interfaceName, classModel);
				}

			}
			else
			{
				interfaceName = key;
				if (interfaces.containsKey(interfaceName))
				{
					classModel = interfaces.get(interfaceName);
					methods = classModel.getMethods();
					for (final String value : propertyKeyValues)
					{
						methodName = value;
						method = methods.get(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(PropertiesExtensions.getPropertyParameters(propertyValue));
					}
				}
				else
				{
					interfaceName = key;
					classModel = new ClassModel();
					methods = new LinkedHashMap<>();
					classModel.setMethods(methods);
					classModel.setClassName(interfaceName);
					for (final String value : propertyKeyValues)
					{
						methodName = value;
						method = new MethodModel();
						method.setMethodAnnotations(new ArrayList<AnnotationModel>());
						methods.put(methodName, method);
						method.setMethodName(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(PropertiesExtensions.getPropertyParameters(propertyValue));
					}
					interfaces.put(interfaceName, classModel);
				}
			}
		}
	}

	// @Test
	public void test()
	{

		final Properties enProperties = new SortedProperties();

		enProperties.put("com", "Hello, {0} {1} {2}!");
		enProperties.put("com.example.gui.window.title", "Hello, {0}!");
		enProperties.put("com.example.gui.window.buttons.ok", "OK");
		enProperties.put("com.example.gui.window.buttons.cancel", "Cancel");

		Map<String, List<String>> matchedPrefixes = PropertiesExtensions
			.getMatchedPrefixLists(enProperties);
		String localeAnnotationName = "En";
		final Map<String, ClassModel> interfaces = new LinkedHashMap<>();


		getClassModels(matchedPrefixes, localeAnnotationName, interfaces, enProperties);

		final Properties ruProperties = new SortedProperties();

		ruProperties.put("com", "Привет, {0} {1} {2}!");
		ruProperties.put("com.example.gui.window.title", "Привет, {0}!");
		ruProperties.put("com.example.gui.window.buttons.ok", "Да");
		ruProperties.put("com.example.gui.window.buttons.cancel", "Отмена");

		matchedPrefixes = PropertiesExtensions.getMatchedPrefixLists(ruProperties);
		localeAnnotationName = "Ru";

		getClassModels(matchedPrefixes, localeAnnotationName, interfaces, ruProperties);
		System.out.println(interfaces);
	}

	@SuppressWarnings("serial")
	@Test(enabled = false)
	public void testFindRedundantValues() throws IOException
	{
		final Properties properties = new Properties();

		properties.put("com", "Hello, {0} {1} {2}!");
		properties.put("foo.redundant.value", "Hello, {0} {1} {2}!");
		properties.put("com.example.gui.window.title", "Hello, {0}!");
		properties.put("com.example.gui.window.buttons.ok", "OK");
		properties.put("foo.bar", "OK");
		properties.put("com.example.gui.window.buttons.cancel", "Cancel");

		final Map<String, List<String>> redundantValues = PropertiesExtensions
			.findRedundantValues(properties);
		AssertJUnit.assertEquals(redundantValues.get("Hello, {0} {1} {2}!"),
			new ArrayList<String>()
			{
				{
					add("com");
					add("foo.redundant.value");
				}
			});
		AssertJUnit.assertEquals(redundantValues.get("OK"), new ArrayList<String>()
		{
			{
				add("com.example.gui.window.buttons.ok");
				add("foo.bar");
			}
		});
	}

	@Test(enabled = true)
	public void testGetAvailableLanguages()
	{
		final String bundlepackage = "de/alpharogroup/lang";
		final String bundlename = "resources";
		final Set<String> availableLanguages = LocaleResolver.resolveAvailableLanguages(bundlepackage,
			bundlename);
		AssertJUnit.assertTrue(availableLanguages.contains("de"));
		AssertJUnit.assertTrue(availableLanguages.contains("de_DE"));
		AssertJUnit.assertTrue(availableLanguages.contains("en"));
	}

	@Test(enabled = false)
	public void testGetBundlename() throws URISyntaxException
	{

		final String propertiesFilename = "de/alpharogroup/lang/resources_de.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		final String expected = "resources";
		final String actual = LocaleResolver.resolveBundlename(propertiesFile);
		AssertJUnit.assertTrue(expected.equals(actual));
	}

	@Test(enabled = false)
	public void testGetLocales()
	{
		final String bundlepackage = "de/alpharogroup/lang";
		final String bundlename = "resources";
		final Map<File, Locale> availableLanguages = LocaleResolver.resolveLocales(bundlepackage,
			bundlename);
		AssertJUnit.assertTrue(availableLanguages.containsValue(new Locale("de")));
		AssertJUnit.assertTrue(availableLanguages.containsValue(new Locale("de", "DE")));
		AssertJUnit.assertTrue(availableLanguages.containsValue(new Locale("en")));
		AssertJUnit.assertTrue(availableLanguages.containsValue(Locale.getDefault()));
	}

	@Test(enabled = false)
	public void testGetLocalPropertiesFromClass()
	{
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = true)
	public void testLoadProperties() throws IOException
	{
		final String propertiesFilename = "resources.properties";
		final String pathFromObject = PackageExtensions.getPackagePathWithSlash(this);
		final String path = pathFromObject + propertiesFilename;

		final Properties prop = PropertiesExtensions.loadProperties(path);
		final boolean result = null != prop;
		AssertJUnit.assertTrue("", result);
	}

	@Test(enabled = true)
	public void testLoadProperties2() throws IOException
	{
		String packagePath = "de/alpharogroup/lang/";
		String propertiesFilename = "resources.properties";
		Properties prop = PropertiesExtensions.loadProperties(packagePath, propertiesFilename);
		boolean result = null != prop;
		AssertJUnit.assertTrue("", result);

		packagePath = "/de/alpharogroup/lang//";
		propertiesFilename = "//resources.properties";
		prop = PropertiesExtensions.loadProperties(packagePath, propertiesFilename);
		result = null != prop;
		AssertJUnit.assertTrue("", result);

	}


	@Test(enabled = true)
	public void testLoadProperties3() throws IOException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";
		final Properties prop = PropertiesExtensions.loadProperties(propertiesFilename);
		final boolean result = null != prop;
		AssertJUnit.assertTrue("", result);
	}


	@Test(enabled = true)
	public void testLoadPropertiesFromClassObject() throws IOException
	{
		final Locale en = Locale.ENGLISH;
		Properties properties = PropertiesExtensions.loadPropertiesFromClassObject(this.getClass(), en);
		AssertJUnit.assertTrue("", properties.get("test").equals("foo"));
		properties = PropertiesExtensions.loadPropertiesFromClassObject(this.getClass(), null);
		AssertJUnit.assertTrue("", properties.get("test").equals("bar"));
	}

	@Test(enabled = false)
	public void testToPropertiesFileFileString()
	{
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = false)
	public void testToPropertiesOutputStreamInputStreamString()
	{
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = false)
	public void testToXmlFileFileStringString() throws URISyntaxException, FileNotFoundException,
		IOException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		final File xmlFile = new File(propertiesFile.getParent(), "resources.properties.xml");
		PropertiesExtensions.toXml(propertiesFile, xmlFile, "", "UTF-8");
	}

	@Test(enabled = false)
	public void testToXmlInputStreamOutputStreamStringString()
	{
		throw new RuntimeException("Test not implemented");
	}

}
