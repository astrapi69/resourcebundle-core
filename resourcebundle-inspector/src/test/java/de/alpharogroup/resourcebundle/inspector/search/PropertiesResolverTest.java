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
package de.alpharogroup.resourcebundle.inspector.search;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

/**
 * The class {@link PropertiesResolverTest} provides unit tests for the class
 * {@link PropertiesResolver}.
 */
public class PropertiesResolverTest
{

	/**
	 * Test method for {@link PropertiesResolver#resolve()}
	 */
	@Test
	public void testFind() throws IOException
	{
		final File rootDir = PathFinder.getSrcTestResourcesDir();
		final PropertiesResolver finder = new PropertiesResolver(rootDir);
		finder.resolve();
		final Map<File, String> foundedfiles = finder.getPropertiesToLocale();
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		final File propertiesFile = new File(dir, propertiesFilename);
		final String actual = foundedfiles.get(propertiesFile);
		final String expected = "default";
		AssertJUnit.assertTrue(actual.equals(expected));
		final File root = finder.getRootDir();
		assertEquals(rootDir, root);
	}

	/**
	 * Test method for {@link PropertiesResolver#resolve()}
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testPropertiesResolver() throws IOException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		final File propertiesFile = new File(dir, propertiesFilename);
		final PropertiesResolver finder = new PropertiesResolver(propertiesFile);
	}

	/**
	 * Test method for {@link PropertiesResolver#resolve()}
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testPropertiesResolverNull() throws IOException
	{
		final PropertiesResolver finder = new PropertiesResolver(null);
	}

}
