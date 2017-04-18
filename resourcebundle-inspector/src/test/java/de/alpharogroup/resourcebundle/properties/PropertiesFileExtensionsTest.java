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
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

public class PropertiesFileExtensionsTest
{

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
		AssertJUnit.assertTrue(fileMap.containsKey(expectedPropertiesFile1));
		AssertJUnit.assertTrue(fileMap.containsKey(expectedPropertiesFile2));
	}

	@Test(enabled = true)
	public void testRemoveComments() throws IOException
	{
		final File srcTestResourceDir = PathFinder.getSrcTestResourcesDir();
		final File testDir = PathFinder.getRelativePath(srcTestResourceDir, "resources",
			"properties");
		final File propertiesFile = PathFinder.getRelativePath(testDir, "test.properties");
		final List<String> lines = PropertiesFileExtensions.removeComments(propertiesFile);
		AssertJUnit.assertTrue(lines.size() == 5);
	}

}
