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
package de.alpharogroup.resourcebundle.file.namefilter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

/**
 * The class {@link PropertiesXmlResourceBundleFilenameFilter}.
 */
public class PropertiesXmlResourceBundleFilenameFilterTest
{
	/**
	 * Test for method
	 * {@link PropertiesXmlResourceBundleFilenameFilter#accept(java.io.File, String)}.
	 */
	@Test
	public void testAccept()
	{
		boolean expected;
		boolean actual;

		PropertiesXmlResourceBundleFilenameFilter filenameFilter = new PropertiesXmlResourceBundleFilenameFilter(
			"resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(),
			"resources_en.properties.xml");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(),
			"resources_de.properties.xml");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(),
			"resources_de_DE.properties.xml");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(),
			"foo_de.properties.xml");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test for method {@link PropertiesXmlResourceBundleFilenameFilter#getBundlename()}.
	 */
	@Test
	public void testGetBundlename()
	{
		String actual;
		String expected;

		PropertiesXmlResourceBundleFilenameFilter filenameFilter = new PropertiesXmlResourceBundleFilenameFilter(
			"resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.getBundlename();
		expected = "resources";
		assertEquals(expected, actual);
	}

	/**
	 * Test the constructor of {@link PropertiesXmlResourceBundleFilenameFilter}.
	 */
	@Test
	public void testPropertiesXmlResourceBundleFilenameFilter()
	{
		PropertiesXmlResourceBundleFilenameFilter filenameFilter = new PropertiesXmlResourceBundleFilenameFilter(
			"resources");
		assertNotNull(filenameFilter);
	}

}
