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
package de.alpharogroup.resourcebundle.inspector.validator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Properties;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.file.create.CreateFileExtensions;
import de.alpharogroup.file.exceptions.FileIsADirectoryException;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.file.write.WriteFileExtensions;
import de.alpharogroup.resourcebundle.properties.PropertiesFileExtensions;

/**
 * The unit test class for the class {@link PropertiesNormalizer}.
 */
public class PropertiesNormalizerTest
{

	/**
	 * Test method for {@link PropertiesNormalizer#findPropertiesFilesWithInvalidCharacters(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = false)  // TODO fixit...
	public void testFindPropertiesFilesWithInvalidCharacters() throws IOException
	{
		int expected;
		int actual;
		File dir;
		Collection<File> foundFiles;

		dir = PathFinder.getSrcTestResourcesDir();
		foundFiles = PropertiesNormalizer.findPropertiesFilesWithInvalidCharacters(dir);
		actual = foundFiles.size();
		expected = 2;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link PropertiesNormalizer#normalizeProperties(String)}.
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws FileIsADirectoryException
	 *             the file is A directory exception
	 */
	@Test(enabled = false) // TODO fixit...
	public void testNormalizeProperties()
		throws URISyntaxException, IOException, FileIsADirectoryException
	{
		String key;
		String value;
		File dir;
		String propertiesFilename;
		String propertiesFilenameBak;
		File prpFile;
		File prpFileBak;
		Properties properties;

		dir = PathFinder.getSrcTestResourcesDir();
		propertiesFilename = "foo-write.properties";
		propertiesFilenameBak = propertiesFilename + ".bak";
		prpFile = new File(dir, propertiesFilename);
		prpFileBak = new File(dir, propertiesFilenameBak);
		CreateFileExtensions.newFile(prpFile);
		key = "com";
		value = "öäüß";
		WriteFileExtensions.writeLinesToFile(ListFactory.newArrayList(key + "=" + value), prpFile,
			"ISO-8859-1");
		PropertiesNormalizer.normalizeProperties(prpFile.getAbsolutePath());
		properties = PropertiesFileExtensions.loadProperties(prpFile);
		assertTrue(properties.containsKey(key));
		assertTrue(properties.get(key).equals(value));
		prpFile.deleteOnExit();
		prpFileBak.deleteOnExit();
	}

	/**
	 * Test method for {@link PropertiesNormalizer}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PropertiesNormalizer.class);
	}

}