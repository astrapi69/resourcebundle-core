package de.alpharogroup.resourcebundle.locale;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.lang.ClassExtensions;

public class LocaleResolverTest {

	@Test(enabled = true)
	public void testIsISOCountryCode() {

		final String toSmall = "D";
		final String de = "de";
		final String doesNotExist = "AB";
		
		AssertJUnit.assertFalse("", LocaleResolver.isISOCountryCode(toSmall));

		AssertJUnit.assertFalse("", LocaleResolver.isISOCountryCode(doesNotExist));

		AssertJUnit.assertTrue("", LocaleResolver.isISOCountryCode(de));
	}

	@Test(enabled = false)
	public void testResolveLocaleFile() {
	}

	@Test(enabled = true)
	public void testResolveLocaleString() {
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

	@Test(enabled = false)
	public void testResolveLocaleCodeFile() {
	}

	@Test(enabled = false)
	public void testResolveLocaleCodeString() {
	}

	@Test(enabled = false)
	public void testResolveLocales() {
	}
	

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
