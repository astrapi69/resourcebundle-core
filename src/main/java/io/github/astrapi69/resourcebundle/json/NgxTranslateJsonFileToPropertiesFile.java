package io.github.astrapi69.resourcebundle.json;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import io.github.astrapi69.collections.array.ArrayExtensions;
import io.github.astrapi69.collections.list.ListExtensions;
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
		long lineCount;
		try (Stream<String> stream = Files.lines(ngxTranslateJsonFile.toPath(),
			StandardCharsets.UTF_8))
		{
			lineCount = stream.count();
		}
		Properties properties = JsonToPropertiesExtensions.toProperties(ngxTranslateJsonFile);
		TreeMap<Integer, String> lineNumberToKeyValueMap = new TreeMap<>();
		TreeMap<Integer, String> lineNumberToKeyMap = new TreeMap<>();
		TreeMap<Integer, String> lineNumberToParentKeyMap = new TreeMap<>();
		for (Map.Entry entry : properties.entrySet())
		{
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			int lineNumber = JsonLineNumberResolver.getLineNumber(ngxTranslateJsonFile, "$." + key);
			lineNumberToKeyMap.put(lineNumber, key);
			lineNumberToKeyValueMap.put(lineNumber, key + "=" + value + System.lineSeparator());
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, String> entry : lineNumberToKeyValueMap.entrySet())
		{
			Integer key = entry.getKey();
			Integer previousKey = key - 1;
			if (!lineNumberToKeyValueMap.containsKey(previousKey))
			{
				Integer tmpKey = key;
				while (0 < previousKey && !lineNumberToKeyValueMap.containsKey(previousKey))
				{
					String propertiesKey = lineNumberToKeyMap.get(tmpKey);
					String parentPropertiesKey = getParentPropertiesKey(propertiesKey);
					lineNumberToParentKeyMap.put(previousKey, parentPropertiesKey);
					previousKey -= previousKey;
					tmpKey -= tmpKey;
				}
			}
		}
		int size = properties.size();
		for (int i = 1; i <= lineCount; i++)
		{
			String propertiesKey = lineNumberToKeyMap.get(i);
			if (propertiesKey == null)
			{
				propertiesKey = lineNumberToParentKeyMap.get(i);
				if (propertiesKey == null)
				{
					sb.append(System.lineSeparator());
				}
				else
				{
					sb.append("# ").append(propertiesKey).append(System.lineSeparator());
				}
			}
			else
			{
				String value = (String)properties.get(propertiesKey);
				sb.append(propertiesKey).append("=").append(value).append(System.lineSeparator());
			}
		}
		WriteFileExtensions.string2File(generatedPropertiesFile, sb.toString());
	}

	private static String getParentPropertiesKey(String propertiesKey)
	{
		String[] keyParts = propertiesKey.split("\\.");
		if (keyParts.length == 1)
		{
			return "";
		}
		List<String> parentKeyParts = ArrayExtensions.toList(keyParts);
		ListExtensions.removeLast(parentKeyParts);
		StringBuilder sb = new StringBuilder();
		int size = parentKeyParts.size();
		for (int i = 0; i < size; i++)
		{
			sb.append(parentKeyParts.get(i));
			if (i < size - 1)
			{
				sb.append(".");
			}
		}
		return sb.toString();
	}
}
