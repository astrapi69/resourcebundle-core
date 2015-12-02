/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.resourcebundle.properties;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import de.alpharogroup.file.copy.CopyFileExtensions;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.FileSearchExtensions;

public class PropertiesFileExtensions
{

	/**
	 * Gets the redundant keys in properties files from the given directory. The search is recursive
	 * and finds all properties files. The result is a map with key the properties file and the
	 * found redundant keys as a map.
	 * 
	 * @param dirToSearch
	 *            the dir to search
	 * @return the redundant keys
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Map<File, Map<String, List<String>>> getRedundantKeys(final File dirToSearch)
		throws IOException
	{
		final List<File> foundFiles = FileSearchExtensions.findAllFiles(dirToSearch,
			FileSearchExtensions.getSearchFilePattern("properties"));

		final Map<String, List<String>> linesMap = new LinkedHashMap<>();
		final Map<File, Map<String, List<String>>> fileMap = new LinkedHashMap<>();
		for (final File file : foundFiles)
		{
			final List<String> lines = PropertiesFileExtensions.removeComments(file);
			final Properties p = PropertiesExtensions.loadProperties(file);

			for (final Map.Entry<Object, Object> entry : p.entrySet())
			{
				final String key = ((String)entry.getKey()).trim();
				for (final String line : lines)
				{
					if (line.startsWith(key))
					{
						final char nextChar = line.charAt(key.length());
						// char[] anc = {nextChar};
						// String nc = new String(anc);
						if (nextChar == '.')
						{
							continue;
						}
						else if (nextChar == '=' || nextChar == ':' || nextChar == ' ')
						{
							if (!linesMap.containsKey(key))
							{
								final List<String> dl = new ArrayList<>();
								dl.add(line);
								linesMap.put(key, dl);
							}
							else
							{
								final List<String> dl = linesMap.get(key);
								dl.add(line);
								linesMap.put(key, dl);
							}
						}
						else
						{
							throw new RuntimeException("nextChar is '" + nextChar + "'");
						}
					}
				}
			}

			final Map<String, List<String>> duplicateKeys = new LinkedHashMap<>();
			for (final Map.Entry<String, List<String>> entry : linesMap.entrySet())
			{
				if (1 < entry.getValue().size())
				{
					duplicateKeys.put(entry.getKey(), entry.getValue());
				}
			}
			if (0 < duplicateKeys.size())
			{
				fileMap.put(file, duplicateKeys);
			}
		}
		return fileMap;
	}

	/**
	 * Creates a backup file from the given properties file.
	 * 
	 * @param file
	 *            the file
	 * @return the backup file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File newBackupOf(final File file) throws IOException
	{
		return CopyFileExtensions.newBackupOf(file, Charset.forName("ISO-8859-1"),
			Charset.forName("UTF-8"));
	}

	/**
	 * Removes the comments from the given properties file.
	 * 
	 * @param propertiesFile
	 *            the properties file
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<String> removeComments(final File propertiesFile) throws IOException
	{
		if (propertiesFile != null
			&& !propertiesFile.getName()
				.matches(FileSearchExtensions.getSearchFilePattern("properties")))
		{
			throw new IllegalArgumentException("The given file is not an properties file.");
		}
		final List<String> lines = ReadFileExtensions.readLinesInList(propertiesFile);
		for (final Iterator<String> itr = lines.iterator(); itr.hasNext();)
		{
			final String line = itr.next();
			if (line.startsWith("#") || line.trim().length() == 0)
			{
				itr.remove();
			}
		}
		return lines;
	}

}
