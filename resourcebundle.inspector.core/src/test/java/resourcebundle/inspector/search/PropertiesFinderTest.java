package resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

public class PropertiesFinderTest
{

	@Test
	public void testFind() throws IOException
	{
		final File rootDir = PathFinder.getSrcTestResourcesDir();
		final PropertiesFinder finder = new PropertiesFinder(rootDir);
		finder.find();
		final Map<File, String> foundedfiles = finder.getPropertiesToLocale();
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		final File propertiesFile = new File(dir, propertiesFilename);
		final String actual = foundedfiles.get(propertiesFile);
		final String expected = "default";
		AssertJUnit.assertTrue(actual.equals(expected));
	}

}
