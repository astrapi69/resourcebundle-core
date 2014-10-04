package resourcebundle.inspector.search.processor;

/**
 * The Class UnusedKeysSearchFilter finds the unused keys from properties.
 */
public class UnusedKeysSearchFilter implements FilterProcessor<UsedKeysSearchResult, UnusedKeysSearchResult> {

	/* (non-Javadoc)
	 * @see org.clean.resourcebundles.search.filter.FilterProcessor#process(java.lang.Object)
	 */
	public UnusedKeysSearchResult process(UsedKeysSearchResult result) {
		UnusedKeysSearchResult searchResult = new UnusedKeysSearchResult();
		for (Object key : result.getSearchModel().getBase().keySet()) {
			if (!result.getUsed().containsKey(key)) {
				searchResult.getUnusedKeys().add(key.toString().trim());
			}
		}
		return searchResult;
	}

}
