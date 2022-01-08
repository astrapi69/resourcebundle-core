/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
package io.github.astrapi69.resourcebundle.properties;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import java.util.List;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.collections.CollectionExtensions;
import io.github.astrapi69.collections.array.ArrayFactory;
import io.github.astrapi69.collections.list.ListFactory;

/**
 * The unit test class for the class {@link PropertiesKeyExtensions}
 */
public class PropertiesKeyExtensionsTest
{

	/**
	 * Test method for {@link PropertiesKeyExtensions#getKeyParts(String, PropertiesKeySeperator)}
	 */
	@Test
	public void testGetKeyPartsWithSeperator()
	{
		String[] actual;
		String[] expected;
		String propertiesKey;
		PropertiesKeySeperator seperator;
		// new scenario...
		propertiesKey = "foo.bar.one.five.zero";
		seperator = PropertiesKeySeperator.DOT;
		actual = PropertiesKeyExtensions.getKeyParts(propertiesKey, seperator);
		expected = ArrayFactory.newArray("foo", "bar", "one", "five", "zero");
		assertArrayEquals(actual, expected);
		// new scenario...
		propertiesKey = "";
		actual = PropertiesKeyExtensions.getKeyParts(propertiesKey, seperator);
		expected = ArrayFactory.newArray("");
		assertArrayEquals(actual, expected);
		// new scenario...
		propertiesKey = "foo_bar_one_five_zero";
		seperator = PropertiesKeySeperator.UNDERSCORE;
		actual = PropertiesKeyExtensions.getKeyParts(propertiesKey, seperator);
		expected = ArrayFactory.newArray("foo", "bar", "one", "five", "zero");
		assertArrayEquals(actual, expected);
	}

	/**
	 * Test method for
	 * {@link PropertiesKeyExtensions#getParentPropertiesKey(String, PropertiesKeySeperator)}
	 */
	@Test
	public void testGetParentPropertiesKeyWithSeperator()
	{
		String actual;
		String expected;
		String propertiesKey;
		PropertiesKeySeperator seperator;
		// new scenario...
		propertiesKey = "foo.bar.one.five.zero";
		seperator = PropertiesKeySeperator.DOT;
		actual = PropertiesKeyExtensions.getParentPropertiesKey(propertiesKey, seperator);
		expected = "foo.bar.one.five";
		assertEquals(expected, actual);
		// new scenario...
		propertiesKey = "foo_bar_one_five_zero";
		seperator = PropertiesKeySeperator.UNDERSCORE;
		actual = PropertiesKeyExtensions.getParentPropertiesKey(propertiesKey, seperator);
		expected = "foo_bar_one_five";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#getKeyParts(String)}
	 */
	@Test
	public void testGetKeyParts()
	{
		String[] actual;
		String[] expected;
		String propertiesKey;
		// new scenario...
		propertiesKey = "foo.bar.one.five.zero";
		actual = PropertiesKeyExtensions.getKeyParts(propertiesKey);
		expected = ArrayFactory.newArray("foo", "bar", "one", "five", "zero");
		assertArrayEquals(actual, expected);
	}

	/**
	 * Test method for
	 * {@link PropertiesKeyExtensions#getKeyPartsAsList(String, PropertiesKeySeperator)}
	 */
	@Test
	public void testGetKeyPartsAsListWithSeperator()
	{
		List<String> actual;
		List<String> expected;
		String propertiesKey;
		// new scenario...
		propertiesKey = "foo.bar.one.five.zero";
		actual = PropertiesKeyExtensions.getKeyPartsAsList(propertiesKey,
			PropertiesKeySeperator.DOT);
		expected = ListFactory.newArrayList("foo", "bar", "one", "five", "zero");
		assertTrue(CollectionExtensions.isEqualCollection(actual, expected));
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#getKeyPartsAsList(String)}
	 */
	@Test
	public void testGetKeyPartsAsList()
	{
		List<String> actual;
		List<String> expected;
		String propertiesKey;
		// new scenario...
		propertiesKey = "foo.bar.one.five.zero";
		actual = PropertiesKeyExtensions.getKeyPartsAsList(propertiesKey);
		expected = ListFactory.newArrayList("foo", "bar", "one", "five", "zero");
		assertTrue(CollectionExtensions.isEqualCollection(actual, expected));
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#getParentPropertiesKey(String)}
	 */
	@Test
	public void testGetParentPropertiesKey()
	{
		String actual;
		String expected;
		String propertiesKey;
		// new scenario...
		propertiesKey = "foo.bar.one.five.zero";
		actual = PropertiesKeyExtensions.getParentPropertiesKey(propertiesKey);
		expected = "foo.bar.one.five";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#concatenate(String[], PropertiesKeySeperator)}
	 */
	@Test
	public void testConcatenateWithSeperator()
	{

		String actual;
		String expected;
		String[] keyParts;
		PropertiesKeySeperator seperator;
		// new scenario...
		seperator = PropertiesKeySeperator.DOT;
		keyParts = ArrayFactory.newArray("foo", "bar", "one", "five", "zero");
		actual = PropertiesKeyExtensions.concatenate(keyParts, seperator);
		expected = "foo.bar.one.five.zero";
		assertEquals(expected, actual);
		// new scenario...
		seperator = PropertiesKeySeperator.UNDERSCORE;
		keyParts = ArrayFactory.newArray("foo", "bar", "one", "five", "zero");
		actual = PropertiesKeyExtensions.concatenate(keyParts, seperator);
		expected = "foo_bar_one_five_zero";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#concatenate(List, PropertiesKeySeperator)}
	 */
	@Test
	public void testConcatenateWithSeperatorWithList()
	{

		String actual;
		String expected;
		List<String> keyParts;
		PropertiesKeySeperator seperator;
		// new scenario...
		seperator = PropertiesKeySeperator.DOT;
		keyParts = ListFactory.newArrayList("foo", "bar", "one", "five", "zero");
		actual = PropertiesKeyExtensions.concatenate(keyParts, seperator);
		expected = "foo.bar.one.five.zero";
		assertEquals(expected, actual);
		// new scenario...
		seperator = PropertiesKeySeperator.UNDERSCORE;
		keyParts = ListFactory.newArrayList("foo", "bar", "one", "five", "zero");
		actual = PropertiesKeyExtensions.concatenate(keyParts, seperator);
		expected = "foo_bar_one_five_zero";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#concatenate(String[])}
	 */
	@Test
	public void testConcatenate()
	{

		String actual;
		String expected;
		String[] keyParts;
		// new scenario...
		keyParts = ArrayFactory.newArray("foo", "bar", "one", "five", "zero");
		actual = PropertiesKeyExtensions.concatenate(keyParts);
		expected = "foo.bar.one.five.zero";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions#concatenate(List)}
	 */
	@Test
	public void testConcatenateList()
	{

		String actual;
		String expected;
		List<String> keyParts;
		// new scenario...
		keyParts = ListFactory.newArrayList("foo", "bar", "one", "five", "zero");
		actual = PropertiesKeyExtensions.concatenate(keyParts);
		expected = "foo.bar.one.five.zero";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesKeyExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PropertiesKeyExtensions.class);
	}
}
