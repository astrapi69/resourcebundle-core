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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.testng.annotations.Test;

import de.alpharogroup.file.exceptions.FileIsADirectoryException;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.lang.ClassExtensions;


public class PropertiesNormalizerTest
{

	@Test
	public void testConvertFiles() throws IOException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final Collection<File> r = PropertiesNormalizer
			.findPropertiesFilesWithInvalidCharacters(dir);
		System.out.println(r);
	}

	@Test
	public void testFindPropertiesFilesWithInvalidCharacters() throws IOException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final Collection<File> r = PropertiesNormalizer
			.findPropertiesFilesWithInvalidCharacters(dir);
		System.out.println(r);
	}

	@Test
	public void testNormalizeProperties()
		throws URISyntaxException, IOException, FileIsADirectoryException
	{

		final String propertiesFilename = "resources.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		PropertiesNormalizer.normalizeProperties(propertiesFile.getAbsolutePath());
	}


}