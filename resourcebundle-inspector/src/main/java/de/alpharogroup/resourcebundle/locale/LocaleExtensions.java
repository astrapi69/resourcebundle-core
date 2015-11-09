package de.alpharogroup.resourcebundle.locale;

import java.util.Locale;

/**
 * Extension class for Locales.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
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
