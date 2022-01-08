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

import java.util.Arrays;
import java.util.List;

import lombok.NonNull;
import io.github.astrapi69.collections.list.ListExtensions;
import io.github.astrapi69.collections.list.ListFactory;

/**
 * The class {@link PropertiesKeyExtensions} provides methods for split properties keys to parts and
 * to concatenate properties parts back again
 */
public final class PropertiesKeyExtensions
{
	private PropertiesKeyExtensions()
	{
	}

	/**
	 * Gets the parts of a properties key as a string array
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @param separator
	 *            the used separator
	 * @return the parts of a properties key as string array
	 */
	public static String[] getKeyParts(@NonNull final String propertiesKey,
		@NonNull final PropertiesKeySeperator separator)
	{
		return propertiesKey.split(separator.getSeparatorAsRegex());
	}

	/**
	 * Gets the parts of a properties key as list
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @param separator
	 *            the used separator
	 * @return the parts of a properties key as list
	 */
	public static List<String> getKeyPartsAsList(@NonNull final String propertiesKey,
		@NonNull final PropertiesKeySeperator separator)
	{
		return ListFactory.newArrayList(getKeyParts(propertiesKey, separator));
	}

	/**
	 * Gets the parts of a properties key as list
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @return the parts of a properties key as list
	 */
	public static List<String> getKeyPartsAsList(@NonNull final String propertiesKey)
	{
		return getKeyPartsAsList(propertiesKey, PropertiesKeySeperator.DOT);
	}

	/**
	 * Gets the parts of a properties key as a string array with the default seperator that is a dot
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @return the parts of a properties key as string array
	 */
	public static String[] getKeyParts(@NonNull final String propertiesKey)
	{
		return getKeyParts(propertiesKey, PropertiesKeySeperator.DOT);
	}

	/**
	 * Gets the parent properties key as a string
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @param separator
	 *            the used separator
	 * @return the parts of a properties key as a string
	 */
	public static String getParentPropertiesKey(String propertiesKey,
		@NonNull final PropertiesKeySeperator separator)
	{
		String[] keyParts = getKeyParts(propertiesKey, separator);
		String[] parentKeyParts = Arrays.copyOf(keyParts, keyParts.length - 1);
		return concatenate(parentKeyParts, separator);
	}

	/**
	 * Gets the parent properties key as a string
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @return the parts of a properties key as a string
	 */
	public static String getParentPropertiesKey(String propertiesKey)
	{
		return getParentPropertiesKey(propertiesKey, PropertiesKeySeperator.DOT);
	}

	/**
	 * Concatenates the given string array to a properties key with the given separator
	 *
	 * @param keyParts
	 *            the key parts of the properties key
	 * @param separator
	 *            the used separator
	 * @return the concatenated properties key
	 */
	public static String concatenate(String[] keyParts,
		@NonNull final PropertiesKeySeperator separator)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keyParts.length; i++)
		{
			sb.append(keyParts[i]);
			if (i < keyParts.length - 1)
			{
				sb.append(separator.getSeparator());
			}
		}
		return sb.toString();
	}

	/**
	 * Concatenates the given string list to a properties key with the given separator
	 *
	 * @param keyParts
	 *            the key parts of the properties key
	 * @param separator
	 *            the used separator
	 * @return the concatenated properties key
	 */
	public static String concatenate(List<String> keyParts,
		@NonNull final PropertiesKeySeperator separator)
	{
		return concatenate(ListExtensions.toArray(keyParts), separator);
	}

	/**
	 * Concatenates the given string list to a properties key with the default separator that is a
	 * dot
	 *
	 * @param keyParts
	 *            the key parts of the properties key
	 * @return the concatenated properties key
	 */
	public static String concatenate(List<String> keyParts)
	{
		return concatenate(keyParts, PropertiesKeySeperator.DOT);
	}

	/**
	 * Concatenates the given string array to a properties key with the default separator that is a
	 * dot
	 *
	 * @param keyParts
	 *            the key parts of the properties key
	 * @return the concatenated properties key
	 */
	public static String concatenate(String[] keyParts)
	{
		return concatenate(keyParts, PropertiesKeySeperator.DOT);
	}
}
