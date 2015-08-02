package resourcebundle.inspector.search.processor;

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

import resourcebundle.inspector.search.PropertiesDirectoryWalker;

import com.neovisionaries.i18n.LocaleCode;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.lang.PropertiesUtils;
import de.alpharogroup.locale.LocaleUtils;

public class UsedKeysSearchFilterTest
{

	@Test(enabled = true)
	public void getUsedKeys() throws IOException
	{
		final Map<File, Locale> foundMap = new HashMap<File, Locale>();
		final File rootDir = PathFinder.getSrcMainJavaDir();
		final PropertiesDirectoryWalker walker = new PropertiesDirectoryWalker()
		{
			@Override
			protected void handleFile(final File file, final int depth,
				final Collection<File> results) throws IOException
			{
				final String localeCode = LocaleUtils.getLocaleCode(file);
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
			final Properties properties = PropertiesUtils.loadProperties(propertiesFile);

			final KeySearchModel model = newKeySearchModel(properties, rootDir,
				new HashSet<File>(), foundMap.get(propertiesFile), ".java", ".html");
			final UsedKeysSearchFilter command = new UsedKeysSearchFilter();
			final UsedKeysSearchResult actual = command.process(model);
			final UnusedKeysSearchFilter processor = new UnusedKeysSearchFilter();
			final UnusedKeysSearchResult res = processor.process(actual);
			System.out.println(res.getUnusedKeys());
		}
	}

	protected KeySearchModel newKeySearchModel()
	{
		final KeySearchModel model = new KeySearchModel();
		final Properties properties = new Properties();
		// We set the properties that are supposed readed from a properties file.
		properties.put("com.example.gui.window.title", "Hello, there!");
		properties.put("com.example.gui.window.buttons.ok", "OK");
		properties.put("com.example.gui.window.buttons.cancel", "Cancel");
		properties.put("com.example.gui", "Cancel");
		model.setBase(properties);
		// Set the project directory as search path...
		final File projectdir = PathFinder.getProjectDirectory();
		model.setSearchDir(projectdir);
		// We want to search only java files so we set the extension...
		final String[] fileExtensions = { ".java" };
		model.setFileExtensions(fileExtensions);
		// We can set the files that shell be excuded from the search...
		final Set<File> exclude = new HashSet<File>();
		final File ex = new File(PathFinder.getSrcTestJavaDir(),
			"/resourcebundle/inspector/search/processor/UsedKeysSearchFilterTest.java");
		System.out.println(ex.exists());
		exclude.add(ex);
		model.setExclude(exclude);

		return model;
	}

	protected KeySearchModel newKeySearchModel(final Properties properties, final File searchDir,
		final Set<File> exclude, final Locale locale, final String... fileExtensions)
	{
		final KeySearchModel model = new KeySearchModel();
		model.setBase(properties);
		model.setSearchDir(searchDir);
		model.setExclude(exclude);
		model.setFileExtensions(fileExtensions);
		model.setLocale(locale);
		return model;
	}

	@Test
	public void testExecute() throws IOException
	{
		final UnusedKeysSearchResult expected = new UnusedKeysSearchResult();
		final Set<String> unusedKeys = new HashSet<String>();
		unusedKeys.add("com.example.gui.window.buttons.ok");
		unusedKeys.add("com.example.gui");
		unusedKeys.add("com.example.gui.window.buttons.cancel");
		unusedKeys.add("com.example.gui.window.title");
		expected.setUnusedKeys(unusedKeys);
		final KeySearchModel model = newKeySearchModel();
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
