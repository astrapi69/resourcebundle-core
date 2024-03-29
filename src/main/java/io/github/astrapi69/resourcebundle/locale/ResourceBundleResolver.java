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

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ResourceBundleResolver}.
 */
@UtilityClass
public final class ResourceBundleResolver
{

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for which a resource bundle
	 * @return the {@link ResourceBundle}
	 */
	public static ResourceBundle getBundle(final String baseName, final Locale locale)
	{
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
		return resourceBundle;
	}

}
