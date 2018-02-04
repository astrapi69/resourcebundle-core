package de.alpharogroup.resourcebundle.inspector.search;

import static org.testng.Assert.assertNotNull;

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
		PropertiesListResolver propertiesListResolver = new PropertiesListResolver(new File("."), Locale.ENGLISH);
		assertNotNull(propertiesListResolver);
		propertiesListResolver.resolve();
	}


}
