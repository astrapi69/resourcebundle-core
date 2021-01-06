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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.io.file.FileExtension;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.lang.PackageExtensions;

/**
 * The unit test class for the class {@link PropertiesFileExtensions}
 */
public class PropertiesFileExtensionsTest
{

	/**
	 * Test method for
	 * {@link PropertiesFileExtensions#getLocalPropertiesFromClass(Class, Class, Locale)}
	 *
	 * @throws Exception
	 *             is thrown if any error occurs on the execution
	 */
	@Test
	public void testGetLocalPropertiesFromClass() throws Exception
	{
		Properties propertiesFromClass = PropertiesFileExtensions.getLocalPropertiesFromClass(
			PropertiesFileExtensionsTest.class, PropertiesFileExtensionsTest.class, Locale.ENGLISH);
		assertNotNull(propertiesFromClass);
		propertiesFromClass = PropertiesFileExtensions.getLocalPropertiesFromClass(null,
			PropertiesFileExtensionsTest.class, Locale.ENGLISH);
		assertNotNull(propertiesFromClass);
	}

	/**
	 * Test method for {@link PropertiesFileExtensions#getProjectName()}
	 */
	@Test
	public void testGetProjectName() throws IOException
	{
		final String projectName = PropertiesFileExtensions.getProjectName();
		assertNotNull(projectName);
	}

	@Test(enabled = true)
	public void testGetRedundantKeys() throws IOException
	{
		final File srcTestResourceDir = PathFinder.getSrcTestResourcesDir();
		final File testDir = PathFinder.getRelativePath(srcTestResourceDir, "resources",
			"properties");
		final Map<File, Map<String, List<String>>> fileMap = PropertiesFileExtensions
			.getRedundantKeys(testDir);
		for (final Map.Entry<File, Map<String, List<String>>> entry : fileMap.entrySet())
		{
			final File file = entry.getKey();
			System.out.println(file);
		}
		final File expectedPropertiesFile1 = PathFinder.getRelativePath(testDir, "test.properties");
		final File expectedPropertiesFile2 = PathFinder.getRelativePath(testDir, "foo",
			"resources.properties");
		assertTrue(fileMap.containsKey(expectedPropertiesFile1));
		assertTrue(fileMap.containsKey(expectedPropertiesFile2));
	}

	/**
	 * Test method for {@link PropertiesFileExtensions#loadProperties(Class, String, String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testLoadPropertiesClassOfQStringString() throws IOException
	{
		Properties actual;
		Class<?> clazz;
		String packagePath;
		String fileName;
		clazz = ClassExtensions.class;
		packagePath = "de/alpharogroup/lang/";
		fileName = "resources.properties";
		actual = PropertiesFileExtensions.loadProperties(clazz, packagePath, fileName);
		assertNotNull(actual);
	}

	@Test(enabled = true)
	public void testLoadPropertiesFromClassObject() throws IOException
	{
		final Locale en = Locale.ENGLISH;
		Properties properties = PropertiesFileExtensions
			.loadPropertiesFromClassObject(this.getClass(), en);
		assertTrue("", properties.get("test").equals("foo"));
		properties = PropertiesFileExtensions.loadPropertiesFromClassObject(this.getClass(), null);
		assertTrue("", properties.get("test").equals("bar"));
	}

	@Test(enabled = true)
	public void testLoadPropertiesObjectPropertiesFilename() throws IOException
	{
		final String propertiesFilename = ClassExtensions.getSimpleName(getClass()) + ".properties";
		final Properties prop = PropertiesFileExtensions.loadProperties(this, propertiesFilename);
		final boolean result = null != prop;
		assertTrue("", result);
	}

	@Test(enabled = true)
	public void testLoadPropertiesPackagePath() throws IOException
	{
		final String propertiesFilename = "resources.properties";
		final String pathFromObject = PackageExtensions.getPackagePathWithSlash(this);
		final String path = pathFromObject + propertiesFilename;

		final Properties prop = PropertiesFileExtensions.loadProperties(path);
		final boolean result = null != prop;
		assertTrue("", result);
	}

	@Test(enabled = true)
	public void testLoadPropertiesPackagePathPropertiesFilename() throws IOException
	{
		String packagePath = "de/alpharogroup/lang/";
		String propertiesFilename = "resources.properties";
		Properties prop = PropertiesFileExtensions.loadProperties(packagePath, propertiesFilename);
		boolean result = null != prop;
		assertTrue("", result);

		packagePath = "/de/alpharogroup/lang//";
		propertiesFilename = "//resources.properties";
		prop = PropertiesFileExtensions.loadProperties(packagePath, propertiesFilename);
		result = null != prop;
		assertTrue("", result);

	}

	@Test(enabled = true)
	public void testLoadPropertiesPropertiesFilename() throws IOException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";
		final Properties prop = PropertiesFileExtensions.loadProperties(propertiesFilename);
		final boolean result = null != prop;
		assertTrue("", result);
	}

	/**
	 * Test method for {@link PropertiesFileExtensions#newBackupOf(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNewBackupOf() throws IOException
	{
		File actual;
		File expected;
		final File srcTestResourceDir = PathFinder.getSrcTestResourcesDir();
		final File testDir = PathFinder.getRelativePath(srcTestResourceDir, "resources",
			"properties");
		final File propertiesFile = PathFinder.getRelativePath(testDir, "test.properties");
		actual = PropertiesFileExtensions.newBackupOf(propertiesFile);
		expected = new File(propertiesFile.getParentFile(),
			"test.properties" + FileExtension.BACKUP.getExtension());
		assertEquals(expected, actual);
		expected.deleteOnExit();
	}

	/**
	 * Test method for {@link PropertiesFileExtensions#removeComments(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testRemoveComments() throws IOException
	{
		final File srcTestResourceDir = PathFinder.getSrcTestResourcesDir();
		final File testDir = PathFinder.getRelativePath(srcTestResourceDir, "resources",
			"properties");
		final File propertiesFile = PathFinder.getRelativePath(testDir, "test.properties");
		final List<String> lines = PropertiesFileExtensions.removeComments(propertiesFile);
		assertTrue(lines.size() == 5);
	}

	/**
	 * Test method for {@link PropertiesFileExtensions#resolveAvailableLanguages(String, String)}
	 */
	@Test
	public void testResolveAvailableLanguages()
	{
		Set<String> actual;
		Set<String> expected;
		actual = PropertiesFileExtensions.resolveAvailableLanguages("de.alpharogroup.lang",
			"resources");
		assertNotNull(actual);
		expected = SetFactory.newTreeSet("de", "de_DE", "en");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesFileExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PropertiesFileExtensions.class);
	}

}
