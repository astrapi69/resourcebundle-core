package de.alpharogroup.resourcebundle.file.namefilter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

/**
 * The class {@link PropertiesResourceBundleFilenameFilter}.
 */
public class PropertiesResourceBundleFilenameFilterTest
{

	/**
	 * Test the constructor of {@link PropertiesResourceBundleFilenameFilter}.
	 */
	@Test
	public void testPropertiesResourceBundleFilenameFilter()
	{
		PropertiesResourceBundleFilenameFilter filenameFilter = new PropertiesResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);
	}

	/**
	 * Test for method {@link PropertiesResourceBundleFilenameFilter#accept(java.io.File, String)}.
	 */
	@Test
	public void testAccept()
	{
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
	 * Test for method {@link PropertiesResourceBundleFilenameFilter#getBundlename()}.
	 */
	@Test
	public void testGetBundlename()
	{
		String actual;
		String expected;

		PropertiesResourceBundleFilenameFilter filenameFilter = new PropertiesResourceBundleFilenameFilter("resources");
		assertNotNull(filenameFilter);

		actual = filenameFilter.getBundlename();
		expected = "resources";
		assertEquals(expected, actual);
	}

}
