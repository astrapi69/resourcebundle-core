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

import java.io.Serializable;
import java.util.Locale;

/**
 * The Class Locales provides constants for different Locales that are not in the Locale class.
 *
 * @author Asterios Raptis
 */
public final class Locales implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** Useful constant for the hellenic language. */
	public static final Locale HELLENIC = new Locale("el", "GR");
	/** Useful constant for the greek language. */
	public static final Locale GREEK = new Locale("el");
	/** Useful constant for the greek language. */
	public static final Locale RUSSIAN = new Locale("ru");
	/** Useful constant for the german language in variant schwaebisch. */
	public static final Locale SCHWAEBISCH = new Locale("de", "DE", "schw");

}