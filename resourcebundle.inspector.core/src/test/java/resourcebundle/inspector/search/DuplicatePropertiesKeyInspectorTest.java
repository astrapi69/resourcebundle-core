package resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.alpharogroup.file.search.PathFinder;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import resourcebundle.inspector.core.KeyValueLists;


public class DuplicatePropertiesKeyInspectorTest {

	@Test
	public void testGetResult() throws IOException {
		File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		File propertiesFile = new File(dir, propertiesFilename);
		KeyValueLists keyValueLists = new DuplicatePropertiesKeyInspector(propertiesFile).getResult();
		String duplicateKey = "testkey1";
		AssertJUnit.assertTrue("Map should contains key 'testkey1'.", keyValueLists.getDuplicateMap().containsKey(duplicateKey));
		AssertJUnit.assertTrue("Count of duplicate key should be 2.", keyValueLists.getDuplicateMap().get(duplicateKey).equals(2));
		List<String> values = keyValueLists.getDuplicateValueMap().get(duplicateKey);
		AssertJUnit.assertTrue("Size of value list should be 2.", values.size() == 2);
		AssertJUnit.assertTrue("Value list should contain value 'testvalue2'.", values.get(0).equals("testvalue2"));
		AssertJUnit.assertTrue("Value list should contain value 'testvalue3'.", values.get(1).equals("testvalue3"));		

	}

}
