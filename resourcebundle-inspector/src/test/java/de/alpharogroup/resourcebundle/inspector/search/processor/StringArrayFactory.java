package de.alpharogroup.resourcebundle.inspector.search.processor;

import org.meanbean.lang.Factory;

public class StringArrayFactory implements Factory<String[]>
{
	@Override
	public String[] create()
	{
		return new String[] { "host1", "host2", "host3" };
	}
}