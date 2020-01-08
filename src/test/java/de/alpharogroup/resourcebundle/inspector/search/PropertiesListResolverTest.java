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
package de.alpharogroup.resourcebundle.inspector.search;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * The class {@link PropertiesListResolver}.
 */
public class PropertiesListResolverTest
{

	/**
	 * Test method for the constructor of {@link PropertiesListResolver}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testPropertiesListResolver() throws IOException
	{
		final File expectedDir = new File(".");
		final Locale expectedLocale = Locale.ENGLISH;
		final PropertiesListResolver propertiesListResolver = new PropertiesListResolver(
			expectedDir, expectedLocale);
		assertNotNull(propertiesListResolver);
		propertiesListResolver.resolve();
		final File actualDir = propertiesListResolver.getRootDir();
		assertEquals(expectedDir, actualDir);
		final Locale actualLocale = propertiesListResolver.getDefaultLocale();
		assertEquals(expectedLocale, actualLocale);
	}

	/**
	 * Test method for the constructor of {@link PropertiesListResolver} with given file and not
	 * directory
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testPropertiesListResolverNotDirectory() throws IOException
	{
		final File expectedDir = new File("pom.xml");
		final Locale expectedLocale = Locale.ENGLISH;
		new PropertiesListResolver(expectedDir, expectedLocale);
	}

}
