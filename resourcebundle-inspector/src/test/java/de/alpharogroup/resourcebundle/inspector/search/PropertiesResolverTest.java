package de.alpharogroup.resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

/**
 * The class {@link PropertiesResolverTest} provides unit tests for the class {@link PropertiesResolver}.
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
	}

}
