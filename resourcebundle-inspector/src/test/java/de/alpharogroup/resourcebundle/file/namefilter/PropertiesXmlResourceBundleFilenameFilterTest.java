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
	 * Test the constructor of {@link PropertiesXmlResourceBundleFilenameFilter}.
	 */
	@Test
	public void testPropertiesXmlResourceBundleFilenameFilter()
	{
		PropertiesXmlResourceBundleFilenameFilter filenameFilter = new PropertiesXmlResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);
	}

	/**
	 * Test for method {@link PropertiesXmlResourceBundleFilenameFilter#accept(java.io.File, String)}.
	 */
	@Test
	public void testAccept()
	{
		boolean expected;
		boolean actual;

		PropertiesXmlResourceBundleFilenameFilter filenameFilter = new PropertiesXmlResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "resources_en.properties.xml");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "resources_de.properties.xml");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "resources_de_DE.properties.xml");
		expected = true;
		assertEquals(expected, actual);

		actual = filenameFilter.accept(PathFinder.getSrcTestResourcesDir(), "foo_de.properties.xml");
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

		PropertiesXmlResourceBundleFilenameFilter filenameFilter = new PropertiesXmlResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.getBundlename();
		expected = "resources";
		assertEquals(expected, actual);
	}

}
