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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.file.create.CreateFileExtensions;
import de.alpharogroup.file.exceptions.FileIsADirectoryException;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.file.write.WriteFileQuietlyExtensions;
import de.alpharogroup.lang.ClassExtensions;

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
	@Test
	public void testFindPropertiesFilesWithInvalidCharacters() throws IOException
	{
		int expected;
		int actual;

		final File dir = PathFinder.getSrcTestResourcesDir();
		final Collection<File> r = PropertiesNormalizer
			.findPropertiesFilesWithInvalidCharacters(dir);
		actual = r.size();
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
	@Test(enabled = false)
	public void testNormalizeProperties()
		throws URISyntaxException, IOException, FileIsADirectoryException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "foo-write.properties";
		final File prpFile = new File(dir, propertiesFilename);
		CreateFileExtensions.newFile(prpFile);
		WriteFileQuietlyExtensions.writeLinesToFile(ListFactory.newArrayList("com=öäüß"), prpFile,
			"UTF-8");
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		PropertiesNormalizer.normalizeProperties(propertiesFile.getAbsolutePath());
		prpFile.delete();
	}

}