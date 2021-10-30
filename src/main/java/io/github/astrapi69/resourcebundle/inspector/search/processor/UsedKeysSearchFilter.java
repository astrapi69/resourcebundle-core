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
package io.github.astrapi69.resourcebundle.inspector.search.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import io.github.astrapi69.file.search.FileSearchExtensions;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

/**
 * The class {@link UsedKeysSearchFilter} can process {@link KeySearchBean} and find used keys.
 */
public class UsedKeysSearchFilter implements FilterProcessor<KeySearchBean, UsedKeysSearchResult>
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsedKeysSearchResult process(final KeySearchBean searchModel)
	{
		return RuntimeExceptionDecorator.decorate(() -> getUsedKeysSearchResult(searchModel));
	}

	private UsedKeysSearchResult getUsedKeysSearchResult(KeySearchBean searchModel)
		throws IOException
	{
		UsedKeysSearchResult result;
		final List<File> foundFiles = FileSearchExtensions
			.findFilesWithFilter(searchModel.getSearchDir(), searchModel.getFileExtensions());
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
		return result;
	}

}
