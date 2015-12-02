package de.alpharogroup.resourcebundle.properties;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

public class PropertiesFileExtensionsTest 
{

	@Test(enabled = true)
	public void testGetRedundantKeys() throws IOException
	{
		final File srcTestResourceDir = PathFinder.getSrcTestResourcesDir();
		final File testDir = PathFinder.getRelativePath(srcTestResourceDir, "resources",
			"properties");
		final Map<File, Map<String, List<String>>> fileMap = PropertiesFileExtensions
			.getRedundantKeys(testDir);
		for (final Map.Entry<File, Map<String, List<String>>> entry : fileMap.entrySet())
		{
			final File file = entry.getKey();
			System.out.println(file);
		}
		final File expectedPropertiesFile1 = PathFinder.getRelativePath(testDir, "test.properties");
		final File expectedPropertiesFile2 = PathFinder.getRelativePath(testDir, "foo",
			"resources.properties");
		AssertJUnit.assertTrue(fileMap.containsKey(expectedPropertiesFile1));
		AssertJUnit.assertTrue(fileMap.containsKey(expectedPropertiesFile2));
	}

	@Test(enabled = true)
	public void testRemoveComments() throws IOException
	{
		final File srcTestResourceDir = PathFinder.getSrcTestResourcesDir();
		final File testDir = PathFinder.getRelativePath(srcTestResourceDir, "resources",
			"properties");
		final File propertiesFile = PathFinder.getRelativePath(testDir, "test.properties");
		final List<String> lines = PropertiesFileExtensions.removeComments(propertiesFile);
		AssertJUnit.assertTrue(lines.size() == 5);
	}

}
