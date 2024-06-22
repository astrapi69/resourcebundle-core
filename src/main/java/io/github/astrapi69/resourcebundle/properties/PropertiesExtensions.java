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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.Properties;

import io.github.astrapi69.io.StringOutputStream;

/**
 * The class {@link PropertiesExtensions} provides helper methods for {@link Properties} objects
 */
public class PropertiesExtensions
{
	/**
	 * Converts the given {@link Properties} object to a {@link String} object
	 * 
	 * @param properties
	 * @return the given {@link Properties} object as a {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static Optional<String> toString(Properties properties) throws IOException
	{
		Optional<String> result;
		try (OutputStream outputStream = new StringOutputStream())
		{
			io.github.astrapi69.collection.properties.PropertiesExtensions.export(properties,
				outputStream);
			result = Optional.of(outputStream.toString());
		}
		return result;
	}
}
