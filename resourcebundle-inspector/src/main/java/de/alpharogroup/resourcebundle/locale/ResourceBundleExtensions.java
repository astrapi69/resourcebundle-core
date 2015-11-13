package de.alpharogroup.resourcebundle.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ResourceBundleExtensions} holds extension methods for the class {@link ResourceBundle}.
 */
@UtilityClass
public class ResourceBundleExtensions {

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @return the string
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key) {
		try {
			return resourceBundle.getString(key);
		} catch (final MissingResourceException e) {
			return "Warning:Missing key is '" + key + "'";
		}
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the string
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key, final Object... parameters) {
		try {
			return MessageFormat.format(resourceBundle.getString(key), parameters);
		} catch (final MissingResourceException e) {
			return "Warning:Missing key is '" + key + "'";
		}
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the string
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
			final Object... parameters) {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle(baseName, locale);
		return ResourceBundleExtensions.getString(resourceBundle, key, parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @return the string
	 */
	public static String getString(final String baseName, final Locale locale, final String key) {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle(baseName, locale);
		return ResourceBundleExtensions.getString(resourceBundle, key);
	}

}
