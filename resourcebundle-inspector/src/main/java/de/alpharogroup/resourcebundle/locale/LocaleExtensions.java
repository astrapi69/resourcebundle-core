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

import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import lombok.experimental.UtilityClass;

/**
 * Extension class for Locales.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
@UtilityClass
public final class LocaleExtensions {

	/** The available locales on the system. */
	private static List<Locale> availableLocales;

	/**
	 * Gets the locale file name suffix that has the format
	 * 'language_COUNTRY_variant' for instance 'de_DE' for the Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @return the locale name
	 */
	public static String getLocaleFilenameSuffix(final Locale locale) {
		return getLocaleFileSuffix(locale, true, true, false);
	}

	/**
	 * Gets the locale file name suffix for instance '_de_DE' for the
	 * Locale.GERMANY.
	 *
	 * @param locale
	 *            the locale
	 * @param withCountry
	 *            the with country
	 * @return the locale file suffix
	 */
	public static String getLocaleFileSuffix(final Locale locale, final boolean withCountry) {
		return getLocaleFileSuffix(locale, withCountry, false);
	}

	/**
	 * Gets the locale file name suffix for instance '_de_DE' for the
	 * Locale.GERMANY.
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
			final boolean withVariant) {
		return getLocaleFileSuffix(locale, withCountry, withVariant, true);
	}

	/**
	 * Gets the locale file name suffix for instance '_de_DE' for the
	 * Locale.GERMANY.
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
	public static String getLocaleFileSuffix(final Locale locale, final boolean withCountry, final boolean withVariant,
			final boolean withUnderscorePrefix) {
		final StringBuilder localizedName = new StringBuilder();
		if (locale != null) {
			if ((locale.getLanguage() != null) && !locale.getLanguage().isEmpty()) {
				if (withUnderscorePrefix) {
					localizedName.append("_");
				}
				localizedName.append(locale.getLanguage());
			}
			if (withCountry && (locale.getCountry() != null) && !locale.getCountry().isEmpty()) {
				localizedName.append("_");
				localizedName.append(locale.getCountry());
			}
			if (withVariant && (locale.getVariant() != null) && !locale.getVariant().isEmpty()) {
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
	public static String getLocaleName(final Locale locale) {
		return getLocaleFileSuffix(locale, true, true, false);
	}

	/**
	 * Checks if the given {@link Locale} is in the available locales on the
	 * current jdk.
	 *
	 * @param locale
	 *            the locale to check
	 * @return true, if successful
	 */
	public static boolean contains(final Locale locale) {
		final List<Locale> availableLocales = getAvailableLocales();
		final boolean exists = availableLocales.contains(locale);
		return exists;
	}

	/**
	 * Returns a list of all available locales on the current jdk.
	 * 
	 * @deprecated use instead same name method in LocaleResolver class. Note:
	 *             will be removed on next minor release.
	 *
	 * @return list of all available locales on the current jdk.
	 */
	public static List<Locale> getAvailableLocales() {
		if (availableLocales == null) {
			final Locale localesArray[] = DateFormat.getAvailableLocales();
			availableLocales = Arrays.asList(localesArray);
		}
		return availableLocales;
	}

	/**
	 * Gets the display country name from the given country code in the given
	 * {@link Locale}.
	 *
	 * @param countryCode
	 *            the country code
	 * @param inLocale
	 *            the in locale
	 * @return the country name
	 */
	public static String getCountryName(String countryCode, Locale inLocale) {
		Locale locale = LocaleResolver.getLocale(countryCode);
		return locale.getDisplayCountry(inLocale);
	}

}
