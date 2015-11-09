package de.alpharogroup.resourcebundle.locale;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class AbstractPropertiesKeysListResolver provides the properties key for every value
 * in the given list. Derived classes can get Properties that have a properties key prefix and
 * suffix for instance: properties key prefix='infringement.list.entry' and the properties key
 * suffix='label'. The values list contains the String object '1' and '2'. The properties file for
 * Locale.ENGLISH could look something like this: infringement.list.entry.1.label = foo
 * infringement.list.entry.2.label = bar And the properties file for Locale.GERMANY could look
 * something like this: infringement.list.entry.1.label = bla infringement.list.entry.2.label =
 * fasel And so on for other Locale objects.
 *
 * @param <T>
 *            the generic type of the values list
 */
public abstract class PropertiesKeysListResolver<T>
{

	/** The properties key prefix. */
	private String propertiesKeyPrefix;

	/** The properties key suffix. */
	private String propertiesKeySuffix;

	/** The values. */
	private final List<T> values;

	/**
	 * Instantiates a new properties list view renderer.
	 *
	 * @param propertiesKeyPrefix
	 *            the properties key prefix
	 * @param values
	 *            the values
	 */
	public PropertiesKeysListResolver(final String propertiesKeyPrefix, final List<T> values)
	{
		this(propertiesKeyPrefix, null, values);
	}

	/**
	 * Instantiates a new properties list view renderer.
	 *
	 * @param propertiesKeyPrefix
	 *            the properties key prefix
	 * @param propertiesKeySuffix
	 *            the properties key suffix
	 * @param values
	 *            the values
	 */
	public PropertiesKeysListResolver(final String propertiesKeyPrefix,
		final String propertiesKeySuffix, final List<T> values)
	{
		this.propertiesKeyPrefix = propertiesKeyPrefix;
		this.propertiesKeySuffix = propertiesKeySuffix;
		this.values = values;
	}

	/**
	 * Gets the display value for the given object.
	 *
	 * @param object
	 *            is a value in the list 'values'. the object
	 * @return the display value is the value from the resulted properties key in the properties
	 *         file.
	 */
	public abstract String getDisplayValue(final T object);

	/**
	 * Gets the properties key from the given object.
	 *
	 * @param object
	 *            is a value in the list 'values'.
	 * @return the properties key
	 */
	protected String getPropertiesKey(final String object)
	{
		final String propertiesKey = propertiesKeyPrefix != null ? propertiesKeySuffix != null
			? propertiesKeyPrefix + "." + object + "." + propertiesKeySuffix
			: propertiesKeyPrefix + "." + object : propertiesKeySuffix != null ? object + "."
			+ propertiesKeySuffix : object;
		return propertiesKey;
	}

	/**
	 * Gets the result list from the display values.
	 *
	 * @return the result list from the display values.
	 */
	public List<String> getResultList()
	{
		final List<String> results = new ArrayList<>();
		for (final T string : getValues())
		{
			results.add(getDisplayValue(string));
		}
		return results;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<T> getValues()
	{
		return values;
	}

}
