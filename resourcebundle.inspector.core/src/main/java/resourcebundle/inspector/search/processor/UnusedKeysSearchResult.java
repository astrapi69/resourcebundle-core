package resourcebundle.inspector.search.processor;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class UnusedKeysSearchResult.
 */
public class UnusedKeysSearchResult
{

	/** The unused keys. */
	private Set<String> unusedKeys = new HashSet<String>();

	/**
	 * Gets the unused keys.
	 *
	 * @return the unused keys
	 */
	public Set<String> getUnusedKeys()
	{
		return unusedKeys;
	}

	/**
	 * Sets the unused keys.
	 *
	 * @param unusedKeys
	 *            the unused keys
	 * @return the unused keys search result
	 */
	public UnusedKeysSearchResult setUnusedKeys(final Set<String> unusedKeys)
	{
		this.unusedKeys = unusedKeys;
		return this;
	}
}