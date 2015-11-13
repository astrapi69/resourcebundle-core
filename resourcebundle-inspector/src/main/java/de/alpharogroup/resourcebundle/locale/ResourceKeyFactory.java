package de.alpharogroup.resourcebundle.locale;

import de.alpharogroup.lang.ClassExtensions;

public class ResourceKeyFactory {

	/**
	 * Creates a new resource key from the given object and the given key. The
	 * simple name of the given object will be taken as the prefix and the given
	 * keySuffix will be taken as suffix for the generated resource key.
	 *
	 * @param object
	 *            The object that will be used to generate the resource key.
	 * @param keySuffix
	 *            The suffix that will be used to generate the resource key.
	 * @param <T>
	 *            The generic type of the given object.
	 * @return The generated resource key.
	 */
	public static <T> String newResourceKey(final T object, final String keySuffix) {
		return ResourceKeyFactory.newResourceKey(object, true, keySuffix);
	}

	/**
	 * Creates a new resource key from the given object and the given key. The
	 * name of the given object will be taken as the prefix and the given
	 * keySuffix will be taken as suffix for the generated resource key.
	 *
	 * @param object
	 *            The object that will be used to generate the resource key.
	 * @param keySuffix
	 *            The suffix that will be used to generate the resource key.
	 * @param simpleName
	 *            The flag if the simple name will be used as prefix.
	 * @param <T>
	 *            The generic type of the given object.
	 * @return The generated resource key.
	 */
	public static <T> String newResourceKey(final T object, boolean simpleName, final String keySuffix) {
		return ClassExtensions.getName(object.getClass(), simpleName) + '.' + keySuffix;
	}
}
