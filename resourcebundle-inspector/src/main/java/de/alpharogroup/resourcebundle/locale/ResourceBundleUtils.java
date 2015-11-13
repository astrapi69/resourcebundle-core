package de.alpharogroup.resourcebundle.locale;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class ResourceBundleUtils provides utility methods for the class ResourceBundle.
 * @deprecated use instead the corresponding methods from the classes {@link ResourceBundleExtensions}
 */
public class ResourceBundleUtils
{

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @return the string
	 * 
	 * @deprecated use instead {@link ResourceBundleExtensions#getString(ResourceBundle, String)}
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key)
	{
		return ResourceBundleExtensions.getString(resourceBundle, key);
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
	 * 
	 * @deprecated use instead {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key,
		final Object... parameters)
	{
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
	 *            the locale for which a resource bundle
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the string
	 * 
	 * @deprecated use instead {@link ResourceBundleExtensions#getString(String, Locale, String, Object...)}
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
		final Object... parameters)
	{
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
		return getString(resourceBundle, key, parameters);
	}

	/**
	 * Creates a new resource key from the given object and the given key. The simple name of the
	 * given object will be taken as the prefix and the given keySuffix will be taken as suffix for
	 * the generated resource key.
	 *
	 * @param object
	 *            The object that will be used to generate the resource key.
	 * @param keySuffix
	 *            The suffix that will be used to generate the resource key.
	 * @param <T>
	 *            The generic type of the given object.
	 * @return The generated resource key.
	 * 
	 * @deprecated use instead {@link ResourceKeyFactory#newResourceKey(Object, String)}
	 */
	public static <T> String newResourceKey(final T object, final String keySuffix)
	{
		return ResourceKeyFactory.newResourceKey(object, keySuffix);
	}
}
