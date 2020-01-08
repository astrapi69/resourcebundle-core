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
package de.alpharogroup.resourcebundle.locale;

import de.alpharogroup.lang.ClassExtensions;
import lombok.experimental.UtilityClass;

/**
 * A factory for creating new resource(properties) key as {@link String} objects.
 */
@UtilityClass
public final class ResourceKeyFactory
{

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
	 */
	public static <T> String newResourceKey(final T object, final String keySuffix)
	{
		return ResourceKeyFactory.newResourceKey(object, true, keySuffix);
	}

	/**
	 * Creates a new resource key from the given object and the given key. The name of the given
	 * object will be taken as the prefix and the given keySuffix will be taken as suffix for the
	 * generated resource key.
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
	public static <T> String newResourceKey(final T object, final boolean simpleName,
		final String keySuffix)
	{
		return ClassExtensions.getName(object.getClass(), simpleName) + '.' + keySuffix;
	}
}
