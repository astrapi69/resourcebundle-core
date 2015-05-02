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

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.lang.PropertiesUtils;
import de.alpharogroup.locale.LocaleUtils;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import resourcebundle.inspector.search.PropertiesDirectoryWalker;

import com.neovisionaries.i18n.LocaleCode;

public class UsedKeysSearchFilterTest {

	@Test
	public void testExecute() throws IOException {
		UnusedKeysSearchResult expected = new UnusedKeysSearchResult();
		Set<String> unusedKeys = new HashSet<String>();
		unusedKeys.add("com.example.gui.window.buttons.ok");
		unusedKeys.add("com.example.gui");
		unusedKeys.add("com.example.gui.window.buttons.cancel");
		unusedKeys.add("com.example.gui.window.title");
		expected.setUnusedKeys(unusedKeys);
		KeySearchModel model = newKeySearchModel();
		UsedKeysSearchFilter command = new UsedKeysSearchFilter();
		UsedKeysSearchResult actual = command.process(model);
		UnusedKeysSearchFilter processor = new UnusedKeysSearchFilter();
		UnusedKeysSearchResult res = processor.process(actual);
		AssertJUnit.assertTrue(expected.getUnusedKeys().size() == res
				.getUnusedKeys().size());
		for (String key : res.getUnusedKeys()) {
			AssertJUnit.assertTrue(expected.getUnusedKeys().contains(key));
		}
	}

	protected KeySearchModel newKeySearchModel() {
		KeySearchModel model = new KeySearchModel();
		Properties properties = new Properties();
		// We set the properties that are supposed readed from a properties file.
		properties.put("com.example.gui.window.title", "Hello, there!");
		properties.put("com.example.gui.window.buttons.ok", "OK");
		properties.put("com.example.gui.window.buttons.cancel", "Cancel");
		properties.put("com.example.gui", "Cancel");
		model.setBase(properties);
		// Set the project directory as search path...
		File projectdir = PathFinder.getProjectDirectory();
		model.setSearchDir(projectdir);
		// We want to search only java files so we set the extension...
		String[] fileExtensions = { ".java" };
		model.setFileExtensions(fileExtensions);
		// We can set the files that shell be excuded from the search...
		Set<File> exclude = new HashSet<File>();
		File ex = new File(PathFinder.getSrcTestJavaDir(),
				"/resourcebundle/inspector/search/processor/UsedKeysSearchFilterTest.java");
		System.out.println(ex.exists());
		exclude.add(ex);
		model.setExclude(exclude);

		return model;
	}
	
	@Test(enabled=true)
	public void getUsedKeys() throws IOException {
		final Map<File, Locale> foundMap = new HashMap<File, Locale>();
		File rootDir = PathFinder.getSrcMainJavaDir();
		PropertiesDirectoryWalker walker = new PropertiesDirectoryWalker() {
			@Override
			protected void handleFile(File file, int depth,
					Collection<File> results) throws IOException {
				String localeCode = LocaleUtils.getLocaleCode(file);
				if(localeCode.equals("default")){
					foundMap.put(file, Locale.GERMAN);
				} else {
					Locale locale = LocaleCode.getByCode(localeCode, true).toLocale();
					foundMap.put(file, locale);					
				}
			}
		};
		walker.start(rootDir);
		for (File propertiesFile : foundMap.keySet()) {
			Properties properties = PropertiesUtils.loadProperties(propertiesFile);
			
			KeySearchModel model = newKeySearchModel(properties, 
					rootDir, 
					new HashSet<File>(), 
					foundMap.get(propertiesFile), 
					".java", ".html");
			UsedKeysSearchFilter command = new UsedKeysSearchFilter();
			UsedKeysSearchResult actual = command.process(model);		
			UnusedKeysSearchFilter processor = new UnusedKeysSearchFilter();
			UnusedKeysSearchResult res = processor.process(actual);
			System.out.println(res.getUnusedKeys());
		}
	}

	protected KeySearchModel newKeySearchModel(Properties properties,
			File searchDir, Set<File> exclude, Locale locale,
			String... fileExtensions) {
		KeySearchModel model = new KeySearchModel();
		model.setBase(properties);
		model.setSearchDir(searchDir);
		model.setExclude(exclude);
		model.setFileExtensions(fileExtensions);
		model.setLocale(locale);
		return model;
	}

}
