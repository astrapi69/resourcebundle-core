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
package de.alpharogroup.resourcebundle.inspector.search;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.io.StreamExtensions;
import de.alpharogroup.resourcebundle.inspector.core.KeyValueLists;

/**
 * The class {@link DuplicatePropertiesKeyInspectorTest} provides unit tests for the class
 * {@link DuplicatePropertiesKeyInspector}.
 */
public class DuplicatePropertiesKeyInspectorTest
{

	@Test
	public void testFindRedundantValues()
	{
		final Properties properties = new Properties();
		properties.setProperty("com", "bar");
		properties.setProperty("bar", "foo");
		properties.setProperty("foo", "foo");
		final Map<String, List<String>> redundantValues = DuplicatePropertiesKeyInspector.findRedundantValues(properties);
		assertEquals(redundantValues.size(), 1);
	}

	@Test
	public void testDuplicatePropertiesKeyInspectorFile()  throws IOException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		final File propertiesFile = new File(dir, propertiesFilename);
		final DuplicatePropertiesKeyInspector duplicatePropertiesKeyInspector = new DuplicatePropertiesKeyInspector(propertiesFile);
		assertNotNull(duplicatePropertiesKeyInspector);
	}

	@Test
	public void testDuplicatePropertiesKeyInspectorInputStream() throws IOException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		final File propertiesFile = new File(dir, propertiesFilename);
		final DuplicatePropertiesKeyInspector duplicatePropertiesKeyInspector = new DuplicatePropertiesKeyInspector(StreamExtensions.getInputStream(propertiesFile));
		assertNotNull(duplicatePropertiesKeyInspector);
	}

	/**
	 * Test method for {@link DuplicatePropertiesKeyInspector#getResult()}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetResult() throws IOException
	{
		final File dir = PathFinder.getSrcTestResourcesDir();
		final String propertiesFilename = "resources.properties";
		final File propertiesFile = new File(dir, propertiesFilename);
		final KeyValueLists keyValueLists = new DuplicatePropertiesKeyInspector(propertiesFile)
			.getResult();
		final String duplicateKey = "testkey1";
		AssertJUnit.assertTrue("Map should contains key 'testkey1'.",
			keyValueLists.getDuplicateMap().containsKey(duplicateKey));
		AssertJUnit.assertTrue("Count of duplicate key should be 2.",
			keyValueLists.getDuplicateMap().get(duplicateKey).equals(2));
		final List<String> values = keyValueLists.getDuplicateValueMap().get(duplicateKey);
		AssertJUnit.assertTrue("Size of value list should be 2.", values.size() == 2);
		AssertJUnit.assertTrue("Value list should contain value 'testvalue2'.",
			values.get(0).equals("testvalue2"));
		AssertJUnit.assertTrue("Value list should contain value 'testvalue3'.",
			values.get(1).equals("testvalue3"));

	}




}
