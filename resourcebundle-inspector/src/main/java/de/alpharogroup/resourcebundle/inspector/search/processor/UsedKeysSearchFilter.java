package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import de.alpharogroup.file.search.FileSearchUtils;

/**
 * The class {@link UsedKeysSearchFilter} finds the used keys from.
 */
public class UsedKeysSearchFilter implements FilterProcessor<KeySearchBean, UsedKeysSearchResult>
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsedKeysSearchResult process(final KeySearchBean searchModel)
	{
		UsedKeysSearchResult result;
		try
		{
			// Find
			final List<File> foundFiles = FileSearchUtils.findFilesWithFilter(
				searchModel.getSearchDir(), searchModel.getFileExtensions());
			result = UsedKeysSearchResult.builder().used(new Properties()).build();
			result.setSearchModel(searchModel);
			for (final File file : foundFiles)
			{
				if (!searchModel.getExclude().contains(file))
				{
					final String fileContent = FileUtils.readFileToString(file, "UTF-8");
					for (final Object key : searchModel.getBase().keySet())
					{
						final String k = "\"" + key.toString().trim() + "\"";
						if (fileContent.contains(k))
						{
							result.getUsed().put(key, searchModel.getBase().get(key));
						}
					}
				}
			}
		}
		catch (final IOException e)
		{
			throw new RuntimeException(e);
		}
		return result;
	}

}