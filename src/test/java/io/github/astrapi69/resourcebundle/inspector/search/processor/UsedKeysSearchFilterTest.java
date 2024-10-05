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
package io.github.astrapi69.resourcebundle.inspector.search.processor;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.annotations.Test;

import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.io.file.FileExtension;
import io.github.astrapi69.resourcebundle.locale.LocaleResolver;
import io.github.astrapi69.resourcebundle.properties.PropertiesFileExtensions;

/**
 * The class {@link UsedKeysSearchFilterTest} provides unit tests for the class
 * {@link UsedKeysSearchFilter}.
 */
public class UsedKeysSearchFilterTest
{

	/**
	 * Test method for {@link UsedKeysSearchFilter#process(KeySearchBean)}
	 */
	@Test(enabled = true)
	public void getUsedKeys() throws IOException
	{
		final Map<File, Locale> foundMap = new HashMap<>();
		final File rootDir = PathFinder.getSrcMainJavaDir();

		// Walk the directory and process files
		Files.walkFileTree(rootDir.toPath(), new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException
			{
				File asFile = file.toFile();
				if (asFile.getName().endsWith(FileExtension.PROPERTIES.getExtension()))
				{
					final String localeCode = LocaleResolver.resolveLocaleCode(asFile);
					if (localeCode.equals("default"))
					{
						foundMap.put(asFile, Locale.GERMAN);
					}
					else
					{
						final Locale locale = LocaleResolver.resolveLocale(localeCode);
						foundMap.put(asFile, locale);
					}
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
			{
				// Handle failure case if needed
				return FileVisitResult.CONTINUE;
			}
		});

		// Process the found properties files
		for (final File propertiesFile : foundMap.keySet())
		{
			final Properties properties = PropertiesFileExtensions.loadProperties(propertiesFile);

			final KeySearchBean model = KeySearchBean.newKeySearchBean(properties, rootDir,
				new HashSet<File>(), foundMap.get(propertiesFile), ".java", ".html");

			final UsedKeysSearchFilter command = new UsedKeysSearchFilter();
			final UsedKeysSearchResult actual = command.process(model);

			final UnusedKeysSearchFilter processor = new UnusedKeysSearchFilter();
			final UnusedKeysSearchResult res = processor.process(actual);

			System.out.println(res.getUnusedKeys());
		}
	}

	/**
	 * Factory method to create a new {@link KeySearchBean} for the unit tests
	 *
	 * @return the new {@link KeySearchBean}
	 */
	protected KeySearchBean newKeySearchBean()
	{
		final Properties properties = new Properties();
		// We set the properties that are supposed to read from a properties
		// file.
		properties.put("com.example.gui.window.title", "Hello, there!");
		properties.put("com.example.gui.window.buttons.ok", "OK");
		properties.put("com.example.gui.window.buttons.cancel", "Cancel");
		properties.put("com.example.gui", "Cancel");

		// Set the search path to the test java source folder...
		final File srcTestJava = PathFinder.getSrcTestJavaDir();

		// We want to search only java files so we set the extension for java files...
		final String[] fileExtensions = { ".java" };

		// We can set the files that shell be excuded from the search...
		// these are unit tests that we do not want to include...
		final Set<File> exclude = SetFactory.newHashSet();
		final File ex1 = new File(PathFinder.getSrcTestJavaDir(),
			"/io/github/astrapi69/resourcebundle/inspector/search/processor/UsedKeysSearchFilterTest.java");
		final File ex2 = new File(PathFinder.getSrcTestJavaDir(),
			"/io/github/astrapi69/resourcebundle/locale/ResourceBundleExtensionsTest.java");
		exclude.add(ex1);
		exclude.add(ex2);
		// create the search bean...
		final KeySearchBean searchBean = KeySearchBean.newKeySearchBean(properties, srcTestJava,
			exclude, null, fileExtensions);

		return searchBean;
	}

	/**
	 * Test method for {@link UnusedKeysSearchFilter#process(UsedKeysSearchResult)}
	 */
	@Test(enabled = true)
	public void testExecute() throws IOException
	{
		UnusedKeysSearchResult expected;
		UnusedKeysSearchResult actual;
		Set<String> unusedKeys;
		KeySearchBean model;
		UsedKeysSearchFilter command;
		UsedKeysSearchResult usedKeysSearchResult;
		UnusedKeysSearchFilter processor;

		expected = new UnusedKeysSearchResult();
		unusedKeys = SetFactory.newHashSet("com.example.gui", "com.example.gui.window.title",
			"com.example.gui.window.buttons.ok", "com.example.gui.window.buttons.cancel");
		expected.setUnusedKeys(unusedKeys);
		model = newKeySearchBean();
		command = new UsedKeysSearchFilter();
		usedKeysSearchResult = command.process(model);
		processor = new UnusedKeysSearchFilter();
		actual = processor.process(usedKeysSearchResult);
		assertTrue(expected.getUnusedKeys().size() == actual.getUnusedKeys().size());
		for (final String key : actual.getUnusedKeys())
		{
			assertTrue(expected.getUnusedKeys().contains(key));
		}
	}

}
