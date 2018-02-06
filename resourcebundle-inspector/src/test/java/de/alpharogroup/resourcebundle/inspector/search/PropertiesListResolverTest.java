package de.alpharogroup.resourcebundle.inspector.search;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.testng.annotations.Test;

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
		File expectedDir = new File(".");
		Locale expectedLocale = Locale.ENGLISH;
		PropertiesListResolver propertiesListResolver = new PropertiesListResolver(expectedDir, expectedLocale);
		assertNotNull(propertiesListResolver);
		propertiesListResolver.resolve();
		File actualDir = propertiesListResolver.getRootDir();
		assertEquals( expectedDir, actualDir);
		Locale actualLocale = propertiesListResolver.getDefaultLocale();
		assertEquals(expectedLocale, actualLocale);
	}

	/**
	 * Test method for the constructor of {@link PropertiesListResolver} with given file and not directory
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testPropertiesListResolverNotDirectory() throws IOException
	{
		File expectedDir = new File("pom.xml");
		Locale expectedLocale = Locale.ENGLISH;
		PropertiesListResolver propertiesListResolver = new PropertiesListResolver(expectedDir, expectedLocale);

	}


}
