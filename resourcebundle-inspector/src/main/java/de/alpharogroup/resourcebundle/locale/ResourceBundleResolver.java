package de.alpharogroup.resourcebundle.locale;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class {@link ResourceBundleResolver}.
 */
public class ResourceBundleResolver {

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for which a resource bundle
	 * @return the {@link ResourceBundle}
	 */
	public static ResourceBundle getBundle(final String baseName, final Locale locale) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
		return resourceBundle;
	}
	
}
