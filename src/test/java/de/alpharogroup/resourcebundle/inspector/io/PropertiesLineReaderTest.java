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
package de.alpharogroup.resourcebundle.inspector.io;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * The class {@link PropertiesLineReader}.
 */
public class PropertiesLineReaderTest
{

	/**
	 * Test method for {@link PropertiesLineReader#getLineBuffer()}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetLineBuffer() throws IOException
	{
		final URL resource = getClass().getClassLoader().getResource("resources.properties");

		final PropertiesLineReader lineReader = new PropertiesLineReader(resource.openStream());
		assertNotNull(lineReader);
		final char[] lineBuffer = lineReader.getLineBuffer();
		assertTrue(lineBuffer.length == 1024);
	}

	/**
	 * Test the constructor of {@link PropertiesLineReader} with {@linkplain InputStream}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testPropertiesLineReaderInputStream() throws IOException
	{
		final URL resource = getClass().getClassLoader().getResource("resources.properties");

		final PropertiesLineReader lineReader = new PropertiesLineReader(resource.openStream());
		assertNotNull(lineReader);
	}

	/**
	 * Test the constructor of {@link PropertiesLineReader} with {@linkplain Reader}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testPropertiesLineReaderReader() throws IOException
	{
		final URL resource = getClass().getClassLoader().getResource("resources.properties");

		final PropertiesLineReader lineReader = new PropertiesLineReader(
			new InputStreamReader(resource.openStream()));
		assertNotNull(lineReader);
	}

	/**
	 * Test method for {@link PropertiesLineReader#readLine()}
	 *
	 * @throws IOException
	 */
	@Test
	public void testReadLine() throws IOException
	{
		int expected;
		int actual;

		final URL resource = getClass().getClassLoader().getResource("resources.properties");

		final PropertiesLineReader lineReader = new PropertiesLineReader(resource.openStream());
		assertNotNull(lineReader);

		actual = lineReader.readLine();
		expected = 16;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesLineReader}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PropertiesLineReader.class);
	}

}
