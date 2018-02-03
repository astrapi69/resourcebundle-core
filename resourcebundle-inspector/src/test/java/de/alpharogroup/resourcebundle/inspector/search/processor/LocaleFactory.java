package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.util.Locale;

import org.meanbean.lang.Factory;

public class LocaleFactory implements Factory<Locale>
{
	@Override
	public Locale create()
	{
		return Locale.CANADA;
	}
}