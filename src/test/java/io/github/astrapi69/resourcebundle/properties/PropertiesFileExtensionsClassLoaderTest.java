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
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.resourcebundle.properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Properties;
import java.util.stream.Stream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The unit test class for the class loader fallback of
 * {@link PropertiesFileExtensions#loadProperties(Class, String)} and
 * {@link PropertiesFileExtensions#loadProperties(Class, String, String)} (#38): the properties file
 * lies only where a class loader of its own sees it, next to {@link ClassLoaderMarker}, which that
 * class loader loads once more, so the class loader of the current thread does not find the file
 */
public class PropertiesFileExtensionsClassLoaderTest
{

	/** The package path of {@link ClassLoaderMarker}, with a slash at its end */
	private static final String PACKAGE_PATH = "io/github/astrapi69/resourcebundle/properties/";

	/** The name of the properties file that only the class loader of its own sees */
	private static final String FILE_NAME = "only-in-this-class-loader.properties";

	/** The directory that the class loader of its own reads */
	private Path directory;

	/** The class loader of its own */
	private URLClassLoader classLoader;

	/** {@link ClassLoaderMarker} as the class loader of its own loads it */
	private Class<?> markerClass;

	/**
	 * A call of one of the two methods with the class of the class loader of its own
	 */
	@FunctionalInterface
	interface Load
	{
		/**
		 * Loads the properties with the given class
		 *
		 * @param clazz
		 *            the class
		 * @return the properties or null
		 * @throws IOException
		 *             Signals that an I/O exception has occurred
		 */
		Properties load(Class<?> clazz) throws IOException;
	}

	/**
	 * Writes the class file of {@link ClassLoaderMarker} and the properties file into a new
	 * temporary directory and loads the class through a class loader of its own, whose parent is
	 * the platform class loader, so it does not ask the class loader of the tests
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws ClassNotFoundException
	 *             if the class loader of its own does not find the class
	 */
	@BeforeMethod
	public void setUp() throws IOException, ClassNotFoundException
	{
		directory = Files.createTempDirectory("class-loader-fallback");
		final Path packageDirectory = Files.createDirectories(directory.resolve(PACKAGE_PATH));
		try (InputStream classFile = ClassLoaderMarker.class
			.getResourceAsStream("ClassLoaderMarker.class"))
		{
			Files.copy(classFile, packageDirectory.resolve("ClassLoaderMarker.class"));
		}
		Files.writeString(packageDirectory.resolve(FILE_NAME), "found=next to the class\n",
			StandardCharsets.ISO_8859_1);
		classLoader = new URLClassLoader(new URL[] { directory.toUri().toURL() },
			ClassLoader.getPlatformClassLoader());
		markerClass = classLoader.loadClass(ClassLoaderMarker.class.getName());
		assertNotSame(markerClass, ClassLoaderMarker.class);
		assertNull(
			Thread.currentThread().getContextClassLoader().getResource(PACKAGE_PATH + FILE_NAME));
	}

	/**
	 * Closes the class loader of its own and deletes the temporary directory
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@AfterMethod
	public void tearDown() throws IOException
	{
		classLoader.close();
		try (Stream<Path> paths = Files.walk(directory))
		{
			for (final Path path : paths.sorted(Comparator.reverseOrder()).toList())
			{
				Files.delete(path);
			}
		}
	}

	/**
	 * Provides the ways to name the properties file
	 *
	 * @return the cases, each with its name and the call
	 */
	@DataProvider
	public Object[][] loads()
	{
		return new Object[][] {
				{ "the file name, relative to the package of the class",
						(Load)clazz -> PropertiesFileExtensions.loadProperties(clazz, FILE_NAME) },
				{ "the absolute path with a leading slash",
						(Load)clazz -> PropertiesFileExtensions.loadProperties(clazz,
							"/" + PACKAGE_PATH + FILE_NAME) },
				{ "the absolute package path and the file name",
						(Load)clazz -> PropertiesFileExtensions.loadProperties(clazz,
							"/" + PACKAGE_PATH, FILE_NAME) } };
	}

	/**
	 * Test method for {@link PropertiesFileExtensions#loadProperties(Class, String)} and
	 * {@link PropertiesFileExtensions#loadProperties(Class, String, String)} with a file only the
	 * class loader of the given class sees
	 *
	 * @param name
	 *            the name of the case
	 * @param load
	 *            the call
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test(dataProvider = "loads")
	public void testLoadPropertiesThroughTheClassLoaderOfTheClass(final String name,
		final Load load) throws IOException
	{
		final Properties properties = load.load(markerClass);
		assertNotNull(properties, name);
		assertEquals(properties.getProperty("found"), "next to the class", name);
	}

}
