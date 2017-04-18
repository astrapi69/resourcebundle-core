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
package de.alpharogroup.resourcebundle.inspector.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import de.alpharogroup.resourcebundle.inspector.core.KeyValueLists;
import de.alpharogroup.resourcebundle.inspector.io.PropertiesLineReader;
import lombok.Getter;

/**
 * The Class {@link DuplicatePropertiesKeyInspector}.
 */
public class DuplicatePropertiesKeyInspector
{
	/**
	 * Finds redundant values from the given Properties object and saves it to a Map.
	 *
	 * @param properties
	 *            The Properties to check.
	 * @return A map that contains the redundant value as the key of the map and a List(as value of
	 *         the map) of keys that have the redundant value.
	 */
	public static Map<String, List<String>> findRedundantValues(final Properties properties)
	{
		final Map<String, List<String>> reverseEntries = new LinkedHashMap<>();
		for (final Map.Entry<Object, Object> entry : properties.entrySet())
		{
			final String key = (String)entry.getKey();
			final String value = (String)entry.getValue();
			if (!reverseEntries.containsKey(value))
			{
				final List<String> keys = new ArrayList<>();
				keys.add(key);
				reverseEntries.put(value, keys);
			}
			else
			{
				final List<String> keys = reverseEntries.get(value);
				keys.add(key);
			}
		}
		final Map<String, List<String>> redundantValues = new LinkedHashMap<>();
		for (final Map.Entry<String, List<String>> entry : reverseEntries.entrySet())
		{
			final String key = entry.getKey();
			final List<String> keys = entry.getValue();
			if (1 < keys.size())
			{
				redundantValues.put(key, keys);
			}
		}
		return redundantValues;
	}

	/** The result. */
	@Getter
	private final KeyValueLists result;

	/**
	 * Instantiates a new duplicate properties key finder.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DuplicatePropertiesKeyInspector(final File propertiesFile) throws IOException
	{
		this.result = findDuplicateKeys(propertiesFile);
	}

	/**
	 * Instantiates a new duplicate properties key finder.
	 *
	 * @param inputStream
	 *            the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DuplicatePropertiesKeyInspector(final InputStream inputStream) throws IOException
	{
		this.result = findDuplicateKeys(inputStream);
	}

	/**
	 * Find duplicate keys from the given properties file.
	 *
	 * @param propertiesFile
	 *            the properties file
	 * @return the map with the duplicate keys as key and the occurences as value.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists findDuplicateKeys(final File propertiesFile) throws IOException
	{
		return findDuplicateKeys(new FileInputStream(propertiesFile));
	}

	/**
	 * Find duplicate keys from the given properties file as input stream.
	 *
	 * @param inputStream
	 *            the properties file as InputStream.
	 * @return the KeyValueLists that hold a map with the duplicate keys as key and the occurences
	 *         as value.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists findDuplicateKeys(final InputStream inputStream) throws IOException
	{
		final Set<String> set = new TreeSet<String>();
		final KeyValueLists keyValueLists = read(inputStream);
		final List<String> keys = keyValueLists.getKeys();
		for (int i = 0; i < keys.size(); i++)
		{
			final String key = keys.get(i);
			if (!set.add(key))
			{
				if (keyValueLists.getDuplicateMap().containsKey(key))
				{
					keyValueLists.getDuplicateMap().put(key,
						keyValueLists.getDuplicateMap().get(key).intValue() + 1);
					final List<String> duplicateValues = keyValueLists.getDuplicateValueMap()
						.get(key);
					final String currentValue = keyValueLists.getValues().get(i);
					duplicateValues.add(currentValue);
				}
				else
				{
					keyValueLists.getDuplicateMap().put(key, 1);
					keyValueLists.getDuplicateValueMap().put(key, new ArrayList<String>());
					final List<String> duplicateValues = keyValueLists.getDuplicateValueMap()
						.get(key);
					final String currentValue = keyValueLists.getValues().get(i);
					duplicateValues.add(currentValue);
				}
			}
		}
		return keyValueLists;
	}

	/**
	 * Read from inputstream.
	 *
	 * @param inputStream
	 *            the inputStream
	 * @return the key value lists
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists read(final InputStream inputStream) throws IOException
	{
		return read(new PropertiesLineReader(inputStream));
	}

	/**
	 * Read from given PropertiesLineReader.
	 *
	 * @param propertiesLineReader
	 *            the lr
	 * @return the key value lists
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists read(final PropertiesLineReader propertiesLineReader) throws IOException
	{
		final char[] buffer = new char[1024];
		int limit;
		int keyLength;
		int valueStart;
		char c;
		boolean hasSep;
		boolean precedingBackslash;
		final KeyValueLists keyValueLists = new KeyValueLists();
		while ((limit = propertiesLineReader.readLine()) >= 0)
		{
			c = 0;
			keyLength = 0;
			valueStart = limit;
			hasSep = false;
			precedingBackslash = false;
			while (keyLength < limit)
			{
				c = propertiesLineReader.getLineBuffer()[keyLength];
				if (((c == '=') || (c == ':')) && !precedingBackslash)
				{
					valueStart = keyLength + 1;
					hasSep = true;
					break;
				}
				else if (((c == ' ') || (c == '\t') || (c == '\f')) && !precedingBackslash)
				{
					valueStart = keyLength + 1;
					break;
				}
				if (c == '\\')
				{
					precedingBackslash = !precedingBackslash;
				}
				else
				{
					precedingBackslash = false;
				}
				keyLength++;
			}
			while (valueStart < limit)
			{
				c = propertiesLineReader.getLineBuffer()[valueStart];
				if ((c != ' ') && (c != '\t') && (c != '\f'))
				{
					if (!hasSep && ((c == '=') || (c == ':')))
					{
						hasSep = true;
					}
					else
					{
						break;
					}
				}
				valueStart++;
			}
			final char[] lineBuffer = propertiesLineReader.getLineBuffer();
			final String key = readPartOfLine(lineBuffer, 0, keyLength, buffer);
			final String value = readPartOfLine(lineBuffer, valueStart, limit - valueStart, buffer);
			keyValueLists.getKeys().add(key);
			keyValueLists.getValues().add(value);
		}
		return keyValueLists;
	}

	/**
	 * Converts encoded &#92;uxxxx to unicode chars and changes special saved chars to their
	 * original forms.
	 *
	 * @param in
	 *            the in
	 * @param off
	 *            the off
	 * @param len
	 *            the len
	 * @param buffer
	 *            the buffer
	 * @return the string
	 */
	private String readPartOfLine(final char[] in, int off, final int len, char[] buffer)
	{
		if (buffer.length < len)
		{
			int newLength = len * 2;
			if (newLength < 0)
			{
				newLength = Integer.MAX_VALUE;
			}
			buffer = new char[newLength];
		}
		char aChar;
		final char[] out = buffer;
		int outputLength = 0;
		final int end = off + len;

		while (off < end)
		{
			aChar = in[off++];
			if (aChar == '\\')
			{
				aChar = in[off++];
				if (aChar == 'u')
				{
					// Read the xxxx(Hex-values)
					int value = 0;
					for (int i = 0; i < 4; i++)
					{
						aChar = in[off++];
						switch (aChar)
						{
							case '0' :
							case '1' :
							case '2' :
							case '3' :
							case '4' :
							case '5' :
							case '6' :
							case '7' :
							case '8' :
							case '9' :
								value = ((value << 4) + aChar) - '0';
								break;
							case 'a' :
							case 'b' :
							case 'c' :
							case 'd' :
							case 'e' :
							case 'f' :
								value = ((value << 4) + 10 + aChar) - 'a';
								break;
							case 'A' :
							case 'B' :
							case 'C' :
							case 'D' :
							case 'E' :
							case 'F' :
								value = ((value << 4) + 10 + aChar) - 'A';
								break;
							default :
								throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outputLength++] = (char)value;
				}
				else
				{
					if (aChar == 't')
					{
						aChar = '\t';
					}
					else if (aChar == 'r')
					{
						aChar = '\r';
					}
					else if (aChar == 'n')
					{
						aChar = '\n';
					}
					else if (aChar == 'f')
					{
						aChar = '\f';
					}
					out[outputLength++] = aChar;
				}
			}
			else
			{
				out[outputLength++] = aChar;
			}
		}
		return new String(out, 0, outputLength);
	}

}
