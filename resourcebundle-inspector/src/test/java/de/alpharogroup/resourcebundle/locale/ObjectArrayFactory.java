package de.alpharogroup.resourcebundle.locale;

import org.meanbean.lang.Factory;

/**
 * A factory for creating {@linkplain Object} array objects.
 */
public class ObjectArrayFactory implements Factory<Object[]>
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] create()
	{
		return new Object[] { "foo", "bar", "john", "doe" };
	}
}