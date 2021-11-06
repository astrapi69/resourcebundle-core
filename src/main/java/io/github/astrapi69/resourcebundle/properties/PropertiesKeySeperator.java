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
package io.github.astrapi69.resourcebundle.properties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * The enum class {@link PropertiesKeySeperator} provides the constants for the separation character
 * of properties keys
 */
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum PropertiesKeySeperator
{
	/**
	 * The dot separator character
	 */
	DOT('.', true),

	/**
	 * The underscore separator character
	 */
	UNDERSCORE('_', false);

	/**
	 * The separator character
	 */
	char separator;

	/**
	 * The flag if the character shell be escaped for regex
	 */
	boolean escape;

	/**
	 * Gets the separator as a regex if needed
	 * 
	 * @return the separator as a regex if needed
	 */
	public String getSeparatorAsRegex()
	{
		return escape ? "\\" + separator : "" + separator;
	}

	/**
	 * Gets the separator as a string
	 * 
	 * @return the separator as a string
	 */
	public String getSeparatorAsString()
	{
		return "" + separator;
	}
}
