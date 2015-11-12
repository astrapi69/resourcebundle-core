package de.alpharogroup.resourcebundle.locale;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.lang.ClassExtensions;

public class LocaleResolverTest {

	/**
	 * Test method for {@link LocaleResolver#isISOCountryCode(String)}
	 */
	@Test(enabled = true)
	public void testIsISOCountryCode() {

		final String toSmall = "D";
		final String de = "de";
		final String doesNotExist = "AB";
		
		AssertJUnit.assertFalse("", LocaleResolver.isISOCountryCode(toSmall));

		AssertJUnit.assertFalse("", LocaleResolver.isISOCountryCode(doesNotExist));

		AssertJUnit.assertTrue("", LocaleResolver.isISOCountryCode(de));
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocale(File)}.
	 *
	 * @throws URISyntaxException the URI syntax exception
	 */
	@Test(enabled = true)
	public void testResolveLocaleFile() throws URISyntaxException {
		final String propertiesFilename = "de/alpharogroup/lang/resources_de.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		String code = "de";
		Locale expected = new Locale(code);
		Locale actual = LocaleResolver.resolveLocale(propertiesFile);
		AssertJUnit.assertNotNull(actual);
		AssertJUnit.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link LocaleResolver#resolveLocale(String)}
	 */
	@Test(enabled = true)
	public void testResolveLocaleString() {
		String code = null;
		Locale actual = LocaleResolver.resolveLocale(code);
		code = "";
		actual = LocaleResolver.resolveLocale(code);
		AssertJUnit.assertEquals(Locale.getDefault(), actual);
		code = "de";
		actual = LocaleResolver.resolveLocale(code);
		Locale expected = new Locale(code);
		AssertJUnit.assertEquals(expected, actual);
		code = "de_DE";
		actual = LocaleResolver.resolveLocale(code);
		expected = new Locale("de", "DE");
		AssertJUnit.assertEquals(expected, actual);
		code = "de_DE_platt";
		actual = LocaleResolver.resolveLocale(code);
		expected = new Locale("de", "DE", "platt");
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocaleCode(File)}
	 * 
	 * @throws URISyntaxException the URI syntax exception
	 */
	@Test(enabled = true)
	public void testResolveLocaleCodeFile() throws URISyntaxException {
		final String propertiesFilename = "de/alpharogroup/lang/resources_de.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		String expected = "de";
		String actual = LocaleResolver.resolveLocaleCode(propertiesFile);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocaleCode(String)}
	 */
	@Test(enabled = true)
	public void testResolveLocaleCodeString() {
		String code = null;
		Locale actual = LocaleResolver.resolveLocaleCode(code);
		code = "";
		actual = LocaleResolver.resolveLocaleCode(code);
		AssertJUnit.assertNull(actual);
		code = "de";
		actual = LocaleResolver.resolveLocaleCode(code);
		Locale expected = new Locale(code);
		AssertJUnit.assertEquals(expected, actual);
		code = "de_DE";
		actual = LocaleResolver.resolveLocaleCode(code);
		expected = new Locale("de", "DE");
		AssertJUnit.assertEquals(expected, actual);
		code = "de_DE_platt";
		actual = LocaleResolver.resolveLocaleCode(code);
		expected = new Locale("de", "DE", "platt");
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocales(String, String)}
	 */
	@Test(enabled = true)
	public void testResolveLocales() {
		final String bundlepackage = "de/alpharogroup/lang";
		final String bundlename = "resources";
		Map<File, Locale> fileToLocaleMap = LocaleResolver.resolveLocales(bundlepackage, bundlename);

		AssertJUnit.assertTrue(fileToLocaleMap.containsValue(Locale.GERMAN));
		AssertJUnit.assertTrue(fileToLocaleMap.containsValue(Locale.GERMANY));
		AssertJUnit.assertTrue(fileToLocaleMap.containsValue(Locale.ENGLISH));
	}

	/**
	 * Test method for {@link LocaleResolver#resolveAvailableLanguages(String, String)}
	 */
	@Test(enabled = true)
	public void testResolveAvailableLanguages()
	{
		final String bundlepackage = "de/alpharogroup/lang";
		final String bundlename = "resources";
		final Set<String> availableLanguages = LocaleResolver
			.resolveAvailableLanguages(bundlepackage,
			bundlename);
		AssertJUnit.assertTrue(availableLanguages.contains("de"));
		AssertJUnit.assertTrue(availableLanguages.contains("de_DE"));
		AssertJUnit.assertTrue(availableLanguages.contains("en"));
		AssertJUnit.assertTrue(availableLanguages.contains("default"));
	}

	/**
	 * Test method for {@link LocaleResolver#resolveBundlename(File)}
	 * 
	 * @throws URISyntaxException the URI syntax exception
	 */
	@Test(enabled = true)
	public void testResolveBundlename() throws URISyntaxException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources_de.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		final String expected = "resources";
		final String actual = LocaleResolver.resolveBundlename(propertiesFile);
		AssertJUnit.assertTrue(expected.equals(actual));
	}

}
