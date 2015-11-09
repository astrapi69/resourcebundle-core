package de.alpharogroup.resourcebundle.inspector.search.processor;

/**
 * The Class UnusedKeysSearchFilter finds the unused keys from properties.
 */
public class UnusedKeysSearchFilter
	implements
		FilterProcessor<UsedKeysSearchResult, UnusedKeysSearchResult>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.clean.resourcebundles.search.filter.FilterProcessor#process(java.lang.Object)
	 */
	@Override
	public UnusedKeysSearchResult process(final UsedKeysSearchResult result)
	{
		final UnusedKeysSearchResult searchResult = new UnusedKeysSearchResult();
		for (final Object key : result.getSearchModel().getBase().keySet())
		{
			if (!result.getUsed().containsKey(key))
			{
				searchResult.getUnusedKeys().add(key.toString().trim());
			}
		}
		return searchResult;
	}

}
