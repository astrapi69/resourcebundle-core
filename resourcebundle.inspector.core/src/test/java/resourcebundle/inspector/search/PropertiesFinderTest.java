package resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import de.alpharogroup.file.search.PathFinder;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class PropertiesFinderTest {

	@Test
	public void testFind() throws IOException {
		File rootDir = PathFinder.getSrcTestResourcesDir();
		PropertiesFinder finder = new PropertiesFinder(rootDir);
		finder.find();
		Map<File, String> foundedfiles = finder.getPropertiesToLocale();
		File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		File propertiesFile = new File(dir, propertiesFilename);
		String actual = foundedfiles.get(propertiesFile);
		String expected = "default";
		AssertJUnit.assertTrue(actual.equals(expected));
	}

}
