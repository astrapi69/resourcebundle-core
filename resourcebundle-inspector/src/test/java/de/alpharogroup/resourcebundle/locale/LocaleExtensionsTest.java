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
package de.alpharogroup.resourcebundle.locale;

import static org.testng.AssertJUnit.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

/**
 * The unit test class {@link LocaleExtensionsTest} provides unit tests for the class
 * {@link LocaleExtensions}.
 */
@Slf4j
@ExtensionMethod({ LocaleExtensions.class })
public class LocaleExtensionsTest
{

	@Test
	public void testContains()
	{
		boolean condition = LocaleExtensions.contains(Locales.GREEK);
		assertTrue(condition);

		condition = LocaleExtensions.contains(Locales.HELLENIC);
		assertTrue(condition);

		final Locale locale = Locales.HELLENIC;

		final String englishName = locale.getDisplayName(Locale.ENGLISH);
		final String country = locale.getCountry();
		String iSO3Country = "";
		try
		{
			iSO3Country = locale.getISO3Country();
		}
		catch (final Exception e)
		{
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}
		final String englishCountryName = locale.getDisplayCountry(Locale.ENGLISH);

		final String language = locale.getLanguage();
		final String iSO3Language = locale.getISO3Language();
		final String englishLanguageName = locale.getDisplayLanguage(Locale.ENGLISH);

		final String script = locale.getScript();
		final String englishScript = locale.getDisplayScript(Locale.ENGLISH);

		System.out.printf(
			"Name: %s%n" + "Country: %s; %s - %s%n" + "" + "Language: %s; %s - %s%n"
				+ "Script: %s - %s%n",
			englishName, country, iSO3Country, englishCountryName, language, iSO3Language,
			englishLanguageName, script, englishScript);
	}

	/**
	 * Test method for {@link LocaleExtensions#getLocaleFilenameSuffix(Locale)}
	 */
	@Test(enabled = true)
	public void testGetLocaleFilenameSuffix()
	{

		String expected = "de_DE";
		String actual = LocaleExtensions.getLocaleFilenameSuffix(Locale.GERMANY);
		AssertJUnit.assertEquals(expected, actual);

		expected = "de";
		actual = LocaleExtensions.getLocaleFilenameSuffix(Locale.GERMAN);
		AssertJUnit.assertEquals(expected, actual);

		expected = "el_GR";
		actual = LocaleExtensions.getLocaleFilenameSuffix(Locales.HELLENIC);
		AssertJUnit.assertEquals(expected, actual);

		expected = "el";
		actual = LocaleExtensions.getLocaleFilenameSuffix(Locales.GREEK);
		AssertJUnit.assertEquals(expected, actual);
		expected = "";
		actual = LocaleExtensions.getLocaleFilenameSuffix(null);
		AssertJUnit.assertEquals(expected, actual);
		expected = "de_DE_schw";
		actual = LocaleExtensions.getLocaleFilenameSuffix(Locales.SCHWAEBISCH);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleExtensions#getLocaleFileSuffix(Locale, boolean)}
	 */
	@Test(enabled = true)
	public void testGetLocaleFileSuffixLocaleBoolean()
	{
		String expected = "_de_DE";
		String actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMANY, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de";
		actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMANY, false);
		AssertJUnit.assertEquals(expected, actual);
		expected = "";
		actual = LocaleExtensions.getLocaleFileSuffix(null, true);
		AssertJUnit.assertEquals(expected, actual);
		actual = LocaleExtensions.getLocaleFileSuffix(null, false);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de_DE";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.SCHWAEBISCH, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.SCHWAEBISCH, false);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleExtensions#getLocaleFileSuffix(Locale, boolean, boolean)}
	 */
	@Test(enabled = true)
	public void testGetLocaleFileSuffixLocaleBooleanBoolean()
	{
		String expected = "_de_DE";
		String actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMANY, true, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de";
		actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMANY, false, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de";
		actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMAN, true, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_el_GR";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.HELLENIC, true, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_el";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.HELLENIC, false, true);
		AssertJUnit.assertEquals(expected, actual);
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.GREEK, true, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "";
		actual = LocaleExtensions.getLocaleFileSuffix(null, false, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de_DE_schw";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.SCHWAEBISCH, true, true);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link LocaleExtensions#getLocaleFileSuffix(Locale, boolean, boolean, boolean)}
	 */
	@Test(enabled = true)
	public void testGetLocaleFileSuffixLocaleBooleanBooleanBoolean()
	{
		String expected = "de";
		String actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMANY, false, false, false);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de";
		actual = LocaleExtensions.getLocaleFileSuffix(Locale.GERMANY, false, false, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de_schw";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.SCHWAEBISCH, false, true, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "_de_DE_schw";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.SCHWAEBISCH, true, true, true);
		AssertJUnit.assertEquals(expected, actual);
		expected = "de_DE_schw";
		actual = LocaleExtensions.getLocaleFileSuffix(Locales.SCHWAEBISCH, true, true, false);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleExtensions#getLocaleName(Locale)}
	 */
	@Test(enabled = true)
	public void testGetLocaleName()
	{
		String expected = "de_DE";
		String actual = Locale.GERMANY.getLocaleName();
		AssertJUnit.assertEquals(expected, actual);
		expected = "de_DE_schw";
		actual = LocaleExtensions.getLocaleName(Locales.SCHWAEBISCH);
		AssertJUnit.assertEquals(expected, actual);
		expected = "el_GR";
		actual = LocaleExtensions.getLocaleName(Locales.HELLENIC);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(LocaleExtensions.class);
	}

}
