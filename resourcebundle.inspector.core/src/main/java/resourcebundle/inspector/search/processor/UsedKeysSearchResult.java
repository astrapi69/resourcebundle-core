package resourcebundle.inspector.search.processor;

import java.util.Properties;

/**
 * The Class UsedKeysSearchResult keeps the result from the search and the a reference from the
 * search model.
 */
public class UsedKeysSearchResult
{

	/** The search model. */
	private KeySearchModel searchModel;

	/** The used. */
	private Properties used = new Properties();

	/**
	 * Gets the search model.
	 *
	 * @return the search model
	 */
	public KeySearchModel getSearchModel()
	{
		return searchModel;
	}

	/**
	 * Gets the used.
	 *
	 * @return the used
	 */
	public Properties getUsed()
	{
		return used;
	}

	/**
	 * Sets the search model.
	 *
	 * @param searchModel
	 *            the new search model
	 */
	public void setSearchModel(final KeySearchModel searchModel)
	{
		this.searchModel = searchModel;
	}

	/**
	 * Sets the used.
	 *
	 * @param used
	 *            the new used
	 */
	public void setUsed(final Properties used)
	{
		this.used = used;
	}
}
