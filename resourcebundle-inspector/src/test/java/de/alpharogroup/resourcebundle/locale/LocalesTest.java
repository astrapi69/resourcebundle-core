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

import java.util.Locale;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalesTest
{

	@Test
	public void testContains()
	{
		boolean condition = Locales.contains(Locales.GREEK);
		assertTrue(condition);

		condition = Locales.contains(Locales.HELLENIC);
		assertTrue(condition);

		Locale locale = Locales.HELLENIC;

		final String englishName = locale.getDisplayName(Locale.ENGLISH);
		final String country = locale.getCountry();
		String iSO3Country = "";
		try
		{
			iSO3Country = locale.getISO3Country();
		}
		catch (final Exception e)
		{
			log.error(e.getClass().getName()+ ": " +e.getMessage());
		}
		final String englishCountryName = locale.getDisplayCountry(Locale.ENGLISH);

		final String language = locale.getLanguage();
		final String iSO3Language = locale.getISO3Language();
		final String englishLanguageName = locale.getDisplayLanguage(Locale.ENGLISH);

		final String script = locale.getScript();
		final String englishScript = locale.getDisplayScript(Locale.ENGLISH);

		System.out.printf(
			"Name: %s%n" +
		"Country: %s; %s - %s%n" + ""
			+ "Language: %s; %s - %s%n"
				+ "Script: %s - %s%n",
			englishName,
			country, iSO3Country, englishCountryName,
			language, iSO3Language, englishLanguageName,
			script, englishScript);
	}

}
