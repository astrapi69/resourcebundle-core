package de.alpharogroup.resourcebundle.locale;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lombok.experimental.UtilityClass;
import de.alpharogroup.file.FilenameUtils;
import de.alpharogroup.resourcebundle.file.namefilter.PropertiesResourceBundleFilenameFilter;

/**
 * The Class {@link LocaleResolver} helps to resolve locale objects and languages.
 */
@UtilityClass
public class LocaleResolver
{

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
	 * package. Note the default resource bundle is excluded.
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
		final String localeCode = propertiesFile.getName()
			.replaceAll("^" + resolveBundlename(propertiesFile) + "(_)?|\\.properties$", "");
		return LocaleResolver.resolveLocale(localeCode);
	}

	/**
	 * Resolves the {@link Locale} object from the given locale code.
	 *
	 * @param localeCode
	 *            the locale code
	 * @return the {@link Locale} object.
	 */
	public static Locale resolveLocale(final String localeCode)
	{
		Locale current = resolveLocaleCode(localeCode);
		if(current == null) {
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
		final String filename = FilenameUtils.getFilenameWithoutExtension(propertiesFile);
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
			return Locale.getDefault();
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
		return Locale.getDefault();
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
	public static Map<File, Locale> resolveLocales(final String bundlepackage, final String bundlename)
	{
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final File root = new File(loader.getResource(bundlepackage.replace('.', '/')).getFile());
		final File[] files = root.listFiles(new PropertiesResourceBundleFilenameFilter(bundlename));
		final Map<File, Locale> locales = new HashMap<>();
		for (final File file : files)
		{
			final Locale current = LocaleResolver.resolveLocale(file);
			locales.put(file, current);
		}
		return locales;
	}

}
