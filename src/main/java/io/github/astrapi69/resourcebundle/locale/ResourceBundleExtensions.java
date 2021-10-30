/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
package io.github.astrapi69.resourcebundle.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import io.github.astrapi69.check.Check;

/**
 * The class {@link ResourceBundleExtensions} holds extension methods for the class
 * {@link ResourceBundle}.
 */
@UtilityClass
public final class ResourceBundleExtensions
{

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key,
		final Object... parameters) throws MissingResourceException
	{
		return getString(resourceBundle, key, null, parameters);
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
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final ResourceBundle resourceBundle, final String key,
		final Object... parameters)
	{
		return getStringQuietly(resourceBundle, key, null, parameters);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key,
		final String defaultValue) throws MissingResourceException
	{
		return getString(resourceBundle, key, defaultValue, (Object)null);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final ResourceBundle resourceBundle, final String key,
		final String defaultValue)
	{
		return getStringQuietly(resourceBundle, key, defaultValue, (Object)null);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final @NonNull ResourceBundle resourceBundle,
		final @NonNull String key, final String defaultValue, final Object... parameters)
		throws MissingResourceException
	{
		String value = null;
		value = format(resourceBundle.getString(key), parameters);
		return value;
	}

	/**
	 * Formats the given value with the given parameters.
	 *
	 * @param value
	 *            the value with the pattern
	 * @param parameters
	 *            the parameters
	 * @see MessageFormat#format(String, Object...)
	 * @return the formatted string
	 */
	public static String format(String value, final Object... parameters)
	{
		if (parameters != null && 0 < parameters.length)
		{
			value = MessageFormat.format(value, parameters);
		}
		return value;
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final @NonNull ResourceBundle resourceBundle,
		final String key, final String defaultValue, final Object... parameters)
	{
		Check.get().notEmpty(key, "key");
		String value = null;
		try
		{
			value = getString(resourceBundle, key, defaultValue, parameters);
		}
		catch (final MissingResourceException e)
		{
			final String warnMessage = "Warning:!!!Missing key is '" + key + "'!!!";
			if (defaultValue != null && !defaultValue.isEmpty())
			{
				return defaultValue;
			}
			return warnMessage;
		}
		value = format(value, parameters);
		return value;
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle} and get the
	 * {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final @NonNull Locale locale,
		final String key, final String defaultValue, final Object... parameters)
		throws MissingResourceException
	{
		Check.get().notEmpty(baseName, "baseName");
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle(baseName, locale);
		return ResourceBundleExtensions.getString(resourceBundle, key, defaultValue, parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle} and get the
	 * {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final String baseName, final @NonNull Locale locale,
		final String key, final String defaultValue, final Object... parameters)
	{
		Check.get().notEmpty(baseName, "baseName");
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle(baseName, locale);
		return ResourceBundleExtensions.getStringQuietly(resourceBundle, key, defaultValue,
			parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle} and get the
	 * {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
		final Object... parameters) throws MissingResourceException
	{
		return getString(baseName, locale, key, null, parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle} and get the
	 * {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key)
		throws MissingResourceException
	{
		return getString(baseName, locale, key, (String)null, (Object)null);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle} and get the
	 * {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
		final String defaultValue) throws MissingResourceException
	{
		return getString(baseName, locale, key, defaultValue, (Object)null);
	}

	/**
	 * Resolves the {@link String} object from the given {@link BundleKey}.
	 *
	 * @param bundleKey
	 *            the bundle key
	 * @return the {@link String} value from the given {@link BundleKey}.
	 * @throws MissingResourceException
	 *             is thrown if the given key is missing
	 */
	public static String getString(final BundleKey bundleKey) throws MissingResourceException
	{
		return getString(bundleKey.getBaseName(), bundleKey.getLocale(),
			bundleKey.getResourceBundleKey().getKey(),
			bundleKey.getResourceBundleKey().getDefaultValue(),
			bundleKey.getResourceBundleKey().getParameters());
	}

}
