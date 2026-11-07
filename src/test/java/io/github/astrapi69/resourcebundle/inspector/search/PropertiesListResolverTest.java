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
package io.github.astrapi69.resourcebundle.inspector.search;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.testng.annotations.Test;

import io.github.astrapi69.collection.pair.KeyValuePair;

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
		final File expectedDir = new File(".");
		final Locale expectedLocale = Locale.ENGLISH;
		final PropertiesListResolver propertiesListResolver = new PropertiesListResolver(
			expectedDir, expectedLocale);
		assertNotNull(propertiesListResolver);
		propertiesListResolver.resolve();
		final File actualDir = propertiesListResolver.getRootDir();
		assertEquals(expectedDir, actualDir);
		final Locale actualLocale = propertiesListResolver.getDefaultLocale();
		assertEquals(expectedLocale, actualLocale);
	}

	/**
	 * Test method for {@link PropertiesListResolver#resolve()}: each properties file of the tree,
	 * also in a subdirectory, comes with the locale of its name, the default locale for a file
	 * without one, and a file of another kind is left out
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testResolve() throws IOException
	{
		final Path directory = Files.createTempDirectory("properties-list");
		final Path messages = Files.writeString(directory.resolve("messages.properties"),
			"greeting=hello\n", StandardCharsets.ISO_8859_1);
		final Path messagesDe = Files.writeString(directory.resolve("messages_de.properties"),
			"greeting=hallo\n", StandardCharsets.ISO_8859_1);
		final Path subdirectory = Files.createDirectory(directory.resolve("sub"));
		final Path messagesDeDe = Files.writeString(
			subdirectory.resolve("messages_de_DE.properties"), "greeting=servus\n",
			StandardCharsets.ISO_8859_1);
		final Path readme = Files.writeString(directory.resolve("readme.txt"), "no bundle\n",
			StandardCharsets.ISO_8859_1);
		try
		{
			final PropertiesListResolver resolver = new PropertiesListResolver(directory.toFile(),
				Locale.ENGLISH);
			resolver.resolve();
			final Map<File, Locale> resolved = new HashMap<>();
			for (final KeyValuePair<File, Locale> entry : resolver.getPropertiesList())
			{
				resolved.put(entry.getKey(), entry.getValue());
			}
			assertEquals(3, resolver.getPropertiesList().size());
			assertEquals(Map.of(messages.toFile(), Locale.ENGLISH, messagesDe.toFile(),
				Locale.GERMAN, messagesDeDe.toFile(), Locale.GERMANY), resolved);
		}
		finally
		{
			for (final Path path : List.of(readme, messagesDeDe, subdirectory, messagesDe, messages,
				directory))
			{
				Files.delete(path);
			}
		}
	}

	/**
	 * Test method for the constructor of {@link PropertiesListResolver} with given file and not
	 * directory
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testPropertiesListResolverNotDirectory() throws IOException
	{
		final File expectedDir = new File("pom.xml");
		final Locale expectedLocale = Locale.ENGLISH;
		new PropertiesListResolver(expectedDir, expectedLocale);
	}

}
