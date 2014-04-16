package resourcebundle.inspector.search.processor;
import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sourceforge.jaulp.file.search.FileSearchUtils;

import org.apache.commons.io.FileUtils;

/**
 * The Class UsedKeysSearchFilter finds the used keys from.
 */
public class UsedKeysSearchFilter implements
		FilterProcessor<KeySearchModel, UsedKeysSearchResult> {

	/* (non-Javadoc)
	 * @see org.clean.resourcebundles.search.filter.FilterProcessor#process(java.lang.Object)
	 */
	public UsedKeysSearchResult process(KeySearchModel searchModel)
			 {
		UsedKeysSearchResult result;
		try {
			// Find 
			List<File> foundFiles = FileSearchUtils.findFilesWithFilter(
					searchModel.getSearchDir(), searchModel.getFileExtensions());
			result = new UsedKeysSearchResult();
			result.setSearchModel(searchModel);
			for (File file : foundFiles) {
				if (!searchModel.getExclude().contains(file)) {
					String fileContent = FileUtils.readFileToString(file, "UTF-8");
					for (Object key : searchModel.getBase().keySet()) {
						String k = "\"" + key.toString().trim() + "\"";
						if (fileContent.contains(k)) {
							result.getUsed().put(key,
									searchModel.getBase().get(key));
						}
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}