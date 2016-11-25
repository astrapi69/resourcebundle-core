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

import java.util.Locale;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * The class {@link LocaleExtensionsTest} provides unit tests for the class {@link LocaleExtensions}.
 */
public class LocaleExtensionsTest {

	/**
	 * Test method for {@link LocaleExtensions#getLocaleFilenameSuffix(Locale)}
	 */
	@Test(enabled = true)
	public void testGetLocaleFilenameSuffix() {
		
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
	public void testGetLocaleFileSuffixLocaleBoolean() {
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
	public void testGetLocaleFileSuffixLocaleBooleanBoolean() {
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
	 * Test method for {@link LocaleExtensions#getLocaleFileSuffix(Locale, boolean, boolean, boolean)}
	 */
	@Test(enabled = true)
	public void testGetLocaleFileSuffixLocaleBooleanBooleanBoolean() {
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
	public void testGetLocaleName() {
		String expected = "de_DE";
		String actual = LocaleExtensions.getLocaleName(Locale.GERMANY);
		AssertJUnit.assertEquals(expected, actual);
		expected = "de_DE_schw";
		actual = LocaleExtensions.getLocaleName(Locales.SCHWAEBISCH);
		AssertJUnit.assertEquals(expected, actual);
		expected = "el_GR";
		actual = LocaleExtensions.getLocaleName(Locales.HELLENIC);
		AssertJUnit.assertEquals(expected, actual);
	}

}
