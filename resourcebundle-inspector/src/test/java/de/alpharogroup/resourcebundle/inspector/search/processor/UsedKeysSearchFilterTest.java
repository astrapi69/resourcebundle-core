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
package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.neovisionaries.i18n.LocaleCode;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.resourcebundle.inspector.search.PropertiesDirectoryWalker;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesFileExtensions;

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
		final PropertiesDirectoryWalker walker = new PropertiesDirectoryWalker()
		{
			@Override
			protected void handleFile(final File file, final int depth,
				final Collection<File> results) throws IOException
			{
				final String localeCode = LocaleResolver.resolveLocaleCode(file);
				if (localeCode.equals("default"))
				{
					foundMap.put(file, Locale.GERMAN);
				}
				else
				{
					final Locale locale = LocaleCode.getByCode(localeCode, true).toLocale();
					foundMap.put(file, locale);
				}
			}
		};
		walker.start(rootDir);
		for (final File propertiesFile : foundMap.keySet())
		{
			final Properties properties = PropertiesFileExtensions.loadProperties(propertiesFile);

			final KeySearchBean model = newKeySearchBean(properties, rootDir, new HashSet<File>(),
				foundMap.get(propertiesFile), ".java", ".html");
			final UsedKeysSearchFilter command = new UsedKeysSearchFilter();
			final UsedKeysSearchResult actual = command.process(model);
			final UnusedKeysSearchFilter processor = new UnusedKeysSearchFilter();
			final UnusedKeysSearchResult res = processor.process(actual);
			System.out.println(res.getUnusedKeys());
		}
	}

	/**
	 * Factory method to create a new {@link KeySearchBean} with the given arguments.
	 *
	 * @param properties
	 *            the properties
	 * @param searchDir
	 *            the search dir
	 * @param exclude
	 *            the exclude
	 * @param locale
	 *            the locale
	 * @param fileExtensions
	 *            the file extensions
	 * @return the new {@link KeySearchBean}.
	 */
	protected KeySearchBean newKeySearchBean(final Properties properties, final File searchDir,
		final Set<File> exclude, final Locale locale, final String... fileExtensions)
	{
		final KeySearchBean model = KeySearchBean.builder().base(properties).searchDir(searchDir)
			.exclude(exclude).locale(locale).fileExtensions(fileExtensions).build();
		return model;
	}

	/**
	 * Factory method to create a new {@link KeySearchBean}.
	 *
	 * @return the new {@link KeySearchBean}.
	 */
	protected KeySearchBean newKeySearchModel()
	{
		final Properties properties = new Properties();
		// We set the properties that are supposed read from a properties
		// file.
		properties.put("com.example.gui.window.title", "Hello, there!");
		properties.put("com.example.gui.window.buttons.ok", "OK");
		properties.put("com.example.gui.window.buttons.cancel", "Cancel");
		properties.put("com.example.gui", "Cancel");

		// Set the project directory as search path...
		final File projectdir = PathFinder.getProjectDirectory();

		// We want to search only java files so we set the extension...
		final String[] fileExtensions = { ".java" };

		// We can set the files that shell be excuded from the search...
		// these are unit tests that we do not want to include...
		final Set<File> exclude = new HashSet<>();
		final File ex1 = new File(PathFinder.getSrcTestJavaDir(),
			"/de/alpharogroup/resourcebundle/inspector/search/processor/UsedKeysSearchFilterTest.java");
		final File ex2 = new File(PathFinder.getSrcTestJavaDir(),
			"/de/alpharogroup/resourcebundle/locale/ResourceBundleExtensionsTest.java");
		System.out.println(ex1.exists());
		System.out.println(ex2.exists());
		exclude.add(ex1);
		exclude.add(ex2);
		// create the search bean...
		final KeySearchBean searchBean = newKeySearchBean(properties, projectdir, exclude, null,
			fileExtensions);

		return searchBean;
	}

	/**
	 * Test method for {@link UnusedKeysSearchFilter#process(UsedKeysSearchResult)}
	 */
	@Test
	public void testExecute() throws IOException
	{
		final UnusedKeysSearchResult expected = new UnusedKeysSearchResult();
		final Set<String> unusedKeys = new HashSet<>();
		unusedKeys.add("com.example.gui");
		expected.setUnusedKeys(unusedKeys);
		final KeySearchBean model = newKeySearchModel();
		final UsedKeysSearchFilter command = new UsedKeysSearchFilter();
		final UsedKeysSearchResult actual = command.process(model);
		final UnusedKeysSearchFilter processor = new UnusedKeysSearchFilter();
		final UnusedKeysSearchResult res = processor.process(actual);
		AssertJUnit.assertTrue(expected.getUnusedKeys().size() == res.getUnusedKeys().size());
		for (final String key : res.getUnusedKeys())
		{
			AssertJUnit.assertTrue(expected.getUnusedKeys().contains(key));
		}
	}

}
