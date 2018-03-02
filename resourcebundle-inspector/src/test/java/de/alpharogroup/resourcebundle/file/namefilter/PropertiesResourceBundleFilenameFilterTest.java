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
 * The class {@link PropertiesResourceBundleFilenameFilter}.
 */
public class PropertiesResourceBundleFilenameFilterTest {

	/**
	 * Test for method
	 * {@link PropertiesResourceBundleFilenameFilter#accept(java.io.File, String)}.
	 */
	@Test
	public void testAccept() {
		boolean expected;
		boolean actual;

		PropertiesResourceBundleFilenameFilter filenameFilter = new PropertiesResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "resources_en.properties");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "resources_de.properties");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "resources_de_DE.properties");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "foo_de.properties");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test for method
	 * {@link PropertiesResourceBundleFilenameFilter#getBundlename()}.
	 */
	@Test
	public void testGetBundlename() {
		String actual;
		String expected;

		PropertiesResourceBundleFilenameFilter filenameFilter = new PropertiesResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.getBundlename();
		expected = "resources";
		assertEquals(expected, actual);
	}

	/**
	 * Test the constructor of {@link PropertiesResourceBundleFilenameFilter}.
	 */
	@Test
	public void testPropertiesResourceBundleFilenameFilter() {
		PropertiesResourceBundleFilenameFilter filenameFilter = new PropertiesResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);
	}

}
