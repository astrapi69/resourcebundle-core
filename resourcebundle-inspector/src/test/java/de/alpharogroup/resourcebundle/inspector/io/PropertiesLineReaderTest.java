package de.alpharogroup.resourcebundle.inspector.io;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.testng.annotations.Test;

/**
 * The class {@link PropertiesLineReader}.
 */
public class PropertiesLineReaderTest
{

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

		PropertiesLineReader lineReader = new PropertiesLineReader(resource.openStream());
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

		PropertiesLineReader lineReader = new PropertiesLineReader(
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

		PropertiesLineReader lineReader = new PropertiesLineReader(resource.openStream());
		assertNotNull(lineReader);

		actual = lineReader.readLine();
		expected = 16;
		assertEquals(expected, actual);
	}

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

		PropertiesLineReader lineReader = new PropertiesLineReader(resource.openStream());
		assertNotNull(lineReader);
		char[] lineBuffer = lineReader.getLineBuffer();
		assertTrue(lineBuffer.length == 1024);
	}

}
