package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.util.HashSet;

/**
 * The class {@link UnusedKeysSearchFilter} finds the unused keys from
 * properties.
 */
public class UnusedKeysSearchFilter implements FilterProcessor<UsedKeysSearchResult, UnusedKeysSearchResult> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UnusedKeysSearchResult process(final UsedKeysSearchResult result) {
		final UnusedKeysSearchResult searchResult = UnusedKeysSearchResult.builder().unusedKeys(new HashSet<String>())
				.build();
		for (final Object key : result.getSearchModel().getBase().keySet()) {
			if (!result.getUsed().containsKey(key)) {
				searchResult.getUnusedKeys().add(key.toString().trim());
			}
		}
		return searchResult;
	}

}
