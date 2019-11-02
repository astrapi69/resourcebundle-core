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

import java.io.File;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.io.file.FilenameExtensions;
import de.alpharogroup.resourcebundle.file.namefilter.PropertiesResourceBundleFilenameFilter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The Class {@link LocaleResolver} helps to resolve locale objects and languages.
 */
@UtilityClass
public class LocaleResolver
{

	/** The available locales on the system. */
	private static List<Locale> availableLocales;

	/**
	 * Checks the given code if its a valide ISO 3166-1 countrycode.
	 *
	 * @param code
	 *            The code to check.
	 * @return true if the code is valide otherwise false.
	 */
	public static boolean isISOCountryCode(String code)
	{
		if (code.length() == 2)
		{
			code = code.toUpperCase();
			final List<String> lc = Arrays.asList(Locale.getISOCountries());
			return lc.contains(code);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Resolves all the available languages for the given resource bundle name in the given bundle
	 * package. Note the default resource bundle is named 'default'.
	 *
	 * @param bundlepackage
	 *            The package that contains the properties files.
	 * @param bundlename
	 *            The name of the resource bundle.
	 * @return a Set of String objects with the available languages excluding the default.
	 */
	public static Set<String> resolveAvailableLanguages(final String bundlepackage,
		final String bundlename)
	{
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final File root = new File(loader.getResource(bundlepackage.replace('.', '/')).getFile());
		final File[] files = root.listFiles(new PropertiesResourceBundleFilenameFilter(bundlename));

		final Set<String> languages = new TreeSet<>();
		for (final File file : files)
		{
			final String language = file.getName()
				.replaceAll("^" + bundlename + "(_)?|\\.properties$", "");
			if ((language != null) && !language.isEmpty())
			{
				languages.add(language);
			}
			else
			{
				languages.add("default");
			}
		}
		return languages;
	}

	/**
	 * Resolves the bundle name from the given properties file.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @return the bundle name
	 */
	public static String resolveBundlename(final File propertiesFile)
	{
		final String filename = propertiesFile.getName();
		final int indexOfUnderscore = filename.indexOf("_");
		String bundlename = filename;
		if (0 < indexOfUnderscore)
		{
			bundlename = propertiesFile.getName().substring(0, filename.indexOf("_"));
		}
		else
		{
			bundlename = FilenameExtensions.getFilenameWithoutExtension(propertiesFile);
		}
		return bundlename;
	}

	/**
	 * Resolves the locale from the given properties file.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @return the locale
	 */
	public static Locale resolveLocale(final File propertiesFile)
	{
		return LocaleResolver.resolveLocale(propertiesFile, true);
	}

	/**
	 * Resolves the locale from the given properties file.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @param systemsDefault
	 *            if this flag is true the systems default locale will be taken if not found
	 * @return the locale
	 */
	public static Locale resolveLocale(final File propertiesFile, final boolean systemsDefault)
	{
		return LocaleResolver.resolveLocale(propertiesFile, null, systemsDefault);
	}

	/**
	 * Resolves the locale from the given properties file.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @param defaultLocale
	 *            the default locale
	 * @param systemsDefault
	 *            if this flag is true the systems default locale will be taken if not found
	 * @return the locale
	 */
	public static Locale resolveLocale(final File propertiesFile, final Locale defaultLocale,
		final boolean systemsDefault)
	{
		final String localeCode = propertiesFile.getName()
			.replaceAll("^" + resolveBundlename(propertiesFile) + "(_)?|\\.properties$", "");
		return LocaleResolver.resolveLocale(localeCode, defaultLocale, systemsDefault);
	}

	/**
	 * Resolves the {@link Locale} object from the given locale code. If not found the system
	 * default locale will be taken.
	 *
	 * @param localeCode
	 *            the locale code
	 * @return the {@link Locale} object.
	 */
	public static Locale resolveLocale(final String localeCode)
	{
		return resolveLocale(localeCode, true);
	}

	/**
	 * Resolves the {@link Locale} object from the given locale code.
	 *
	 * @param localeCode
	 *            the locale code
	 * @param systemsDefault
	 *            if this flag is true the systems default locale will be taken if not found
	 *            otherwise not
	 * @return the {@link Locale} object or null if not found and flag systemsDefault is false.
	 */
	public static Locale resolveLocale(final String localeCode, final boolean systemsDefault)
	{
		return resolveLocale(localeCode, null, systemsDefault);
	}

	/**
	 * Resolves the {@link Locale} object from the given locale code. If the given default locale is
	 * not null and the locale code does resolve to no locale the default locale will be returned.
	 *
	 * @param localeCode
	 *            the locale code
	 * @param defaultLocale
	 *            the default locale
	 * @param systemsDefault
	 *            if this flag is true the systems default locale will be taken if not found
	 *            otherwise not
	 * @return the {@link Locale} object or null if not found and flag systemsDefault is false and
	 *         defaultLocale is null.
	 */
	public static Locale resolveLocale(final String localeCode, final Locale defaultLocale,
		final boolean systemsDefault)
	{
		Locale current = resolveLocaleCode(localeCode);
		if (current == null && defaultLocale != null)
		{
			current = defaultLocale;
		}
		if (current == null && systemsDefault)
		{
			current = Locale.getDefault();
		}
		return current;
	}

	/**
	 * Resolves from the given properties file the locale code like "en", "en_US" or "en_US_win".
	 * For instance if the filename is resource_en.properties than it will return "en", if the
	 * filename is resource_en_US.properties than it will return "en_US". If nothing is found than
	 * it returns that the properties file is the "default".
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @return the locale code
	 */
	public static String resolveLocaleCode(final File propertiesFile)
	{
		final String filename = FilenameExtensions.getFilenameWithoutExtension(propertiesFile);
		final int underscoreIndex = filename.indexOf("_");
		String stringCode = "default";
		if (0 < underscoreIndex)
		{
			stringCode = filename.substring(underscoreIndex + 1, filename.length());
		}
		return stringCode;
	}

	/**
	 * Converts the given {@code String} code like "en", "en_US" or "en_US_win" to <b>new</b>
	 * {@code Locale}.
	 *
	 * @param code
	 *            the code
	 * @return the {@code Locale} object or null.
	 */
	public static Locale resolveLocaleCode(final String code)
	{
		if ((code == null) || code.isEmpty())
		{
			return null;
		}
		final String[] splitted = code.split("_");
		if (splitted.length == 1)
		{
			return new Locale(code);
		}
		if (splitted.length == 2)
		{
			return new Locale(splitted[0], splitted[1]);
		}
		if (splitted.length == 3)
		{
			return new Locale(splitted[0], splitted[1], splitted[2]);
		}
		return null;
	}

	/**
	 * Resolves all the available Locales to the given resource bundle name in the given bundle
	 * package. For now it is only for properties files.
	 *
	 * @param bundlepackage
	 *            The package that contains the properties files.
	 * @param bundlename
	 *            The name of the resource bundle.
	 * @return a Map of File objects with the corresponding Locales to it.
	 */
	public static Map<File, Locale> resolveLocales(final String bundlepackage,
		final String bundlename)
	{
		return resolveLocales(bundlepackage, bundlename, true);
	}

	/**
	 * Resolves all the available Locales to the given resource bundle name in the given bundle
	 * package. For now it is only for properties files.
	 *
	 * @param bundlepackage
	 *            The package that contains the properties files.
	 * @param bundlename
	 *            The name of the resource bundle.
	 * @param systemsDefault
	 *            if this flag is true the systems default locale will be taken as the default
	 *            locale otherwise the value will be null for the default locale.
	 * @return a Map of File objects with the corresponding Locales to it.
	 */
	public static Map<File, Locale> resolveLocales(final String bundlepackage,
		final String bundlename, final boolean systemsDefault)
	{
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final File root = new File(loader.getResource(bundlepackage.replace('.', '/')).getFile());
		final File[] files = root.listFiles(new PropertiesResourceBundleFilenameFilter(bundlename));
		final Map<File, Locale> locales = new HashMap<>();
		for (final File file : files)
		{
			final Locale current = LocaleResolver.resolveLocale(file, systemsDefault);
			locales.put(file, current);
		}
		return locales;
	}

	/**
	 * Resolve available locales on the current system.
	 *
	 * @return the list with the available locales.
	 */
	public List<Locale> resolveAvailableLocales()
	{
		return Arrays.asList(DateFormat.getAvailableLocales());
	}

	/**
	 * Returns a list of all available locales on the current jdk.
	 *
	 * @return list of all available locales on the current jdk.
	 */
	public static List<Locale> getAvailableLocales()
	{
		if (availableLocales == null)
		{
			final Locale localesArray[] = DateFormat.getAvailableLocales();
			availableLocales = Arrays.asList(localesArray);
		}
		return availableLocales;
	}

	/**
	 * Resolves a {@link Locale} from the given country code.
	 *
	 * @param countryCode
	 *            the country code
	 * @return the found {@link Locale} or null if not found
	 */
	public static Locale getLocale(@NonNull String countryCode)
	{
		List<Locale> locales = ListFactory.newArrayList();
		getAvailableLocales().forEach(locale -> {
			if (locale.getCountry().equals(countryCode))
			{
				locales.add(locale);
			}
		});
		return ListExtensions.getFirst(locales);
	}

	/**
	 * Resolves a {@link Locale} objects from the given language code.
	 *
	 * @param languageCode
	 *            the language code
	 * @return the found {@link Locale} objects or an empty list if nothing found
	 */
	public static List<Locale> getLocales(@NonNull String languageCode)
	{

		List<Locale> locales = ListFactory.newArrayList();
		getAvailableLocales().forEach(locale -> {
			if (locale.getLanguage().equals(languageCode))
			{
				locales.add(locale);
			}
		});
		return locales;
	}

}
