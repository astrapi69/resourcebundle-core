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

import lombok.experimental.UtilityClass;

/**
 * Extension class for Locales.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
@UtilityClass
public class LocaleExtensions
{

	/**
	 * Gets the locale file name suffix that has the format 'language_COUNTRY_variant' for instance
	 * 'de_DE' for the Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @return the locale name
	 */
	public static String getLocaleFilenameSuffix(final Locale locale)
	{
		return getLocaleFileSuffix(locale, true, true, false);
	}

	/**
	 * Gets the locale file name suffix for instance '_de_DE' for the Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @param withCountry
	 *            the with country
	 * @return the locale file suffix
	 */
	public static String getLocaleFileSuffix(final Locale locale, final boolean withCountry)
	{
		return getLocaleFileSuffix(locale, withCountry, false);
	}

	/**
	 * Gets the locale file name suffix for instance '_de_DE' for the Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @param withCountry
	 *            with country
	 * @param withVariant
	 *            with variant
	 * @return the locale file suffix
	 */
	public static String getLocaleFileSuffix(final Locale locale, final boolean withCountry,
		final boolean withVariant)
	{
		return getLocaleFileSuffix(locale, withCountry, withVariant, true);
	}

	/**
	 * Gets the locale file name suffix for instance '_de_DE' for the Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @param withCountry
	 *            with country
	 * @param withVariant
	 *            with variant
	 * @param withUnderscorePrefix
	 *            true if the result has to have the underscore prefix
	 * @return the locale file suffix
	 */
	public static String getLocaleFileSuffix(final Locale locale, final boolean withCountry,
		final boolean withVariant, final boolean withUnderscorePrefix)
	{
		final StringBuilder localizedName = new StringBuilder();
		if (locale != null)
		{
			if ((locale.getLanguage() != null) && !locale.getLanguage().isEmpty())
			{
				if (withUnderscorePrefix)
				{
					localizedName.append("_");
				}
				localizedName.append(locale.getLanguage());
			}
			if (withCountry && (locale.getCountry() != null) && !locale.getCountry().isEmpty())
			{
				localizedName.append("_");
				localizedName.append(locale.getCountry());
			}
			if (withVariant && (locale.getVariant() != null) && !locale.getVariant().isEmpty())
			{
				localizedName.append("_");
				localizedName.append(locale.getVariant());
			}
		}
		return localizedName.toString().trim();
	}

	/**
	 * Gets the locale name for instance 'de_DE' for the Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @return the locale name
	 */
	public static String getLocaleName(final Locale locale)
	{
		return getLocaleFileSuffix(locale, true, true, false);
	}

}
