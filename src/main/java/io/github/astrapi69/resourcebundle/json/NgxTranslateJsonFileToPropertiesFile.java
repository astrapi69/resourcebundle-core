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
package io.github.astrapi69.resourcebundle.json;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.write.WriteFileExtensions;
import io.github.astrapi69.gson.JsonToPropertiesExtensions;
import io.github.astrapi69.json.JsonLineNumberResolver;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NgxTranslateJsonFileToPropertiesFile
{
	@NonNull
	File ngxTranslateJsonFile;
	@NonNull
	File generatedPropertiesFile;

	public void convert() throws IOException
	{
		WriteFileExtensions.string2File(generatedPropertiesFile,
			getPropertiesAsString(ngxTranslateJsonFile));
	}

	private static String getPropertiesAsString(@NonNull File ngxTranslateJsonFile) throws IOException
	{
		Properties properties = JsonToPropertiesExtensions.toProperties(ngxTranslateJsonFile);
		return getPropertiesAsString(properties, ngxTranslateJsonFile);
	}

	private static String getPropertiesAsString(@NonNull Properties properties,
		@NonNull File ngxTranslateJsonFile) throws IOException
	{
		long lineCount = ReadFileExtensions.countAllLines(ngxTranslateJsonFile);
		TreeMap<Integer, String> lineNumberToKeyMap = generateLineNumberKeyMap(ngxTranslateJsonFile,
			properties);
		String propertiesAsString = getPropertiesAsString(lineCount, properties,
			lineNumberToKeyMap);
		return propertiesAsString;
	}

	private static String getPropertiesAsString(long lineCount, Properties properties,
		TreeMap<Integer, String> lineNumberToKeyMap)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= lineCount; i++)
		{
			String propertiesKey = lineNumberToKeyMap.get(i);
			if (propertiesKey == null)
			{
				sb.append(System.lineSeparator());
			}
			else
			{
				String value = (String)properties.get(propertiesKey);
				sb.append(propertiesKey).append("=").append(value).append(System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private static TreeMap<Integer, String> generateLineNumberKeyMap(
		@NonNull File ngxTranslateJsonFile, @NonNull Properties properties) throws IOException
	{
		TreeMap<Integer, String> lineNumberToKeyMap = new TreeMap<>();
		for (Map.Entry entry : properties.entrySet())
		{
			String key = (String)entry.getKey();
			int lineNumber = JsonLineNumberResolver.getLineNumber(ngxTranslateJsonFile, "$." + key);
			lineNumberToKeyMap.put(lineNumber, key);
		}
		return lineNumberToKeyMap;
	}
}
