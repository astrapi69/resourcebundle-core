package de.alpharogroup.resourcebundle.file.namefilter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * The Class {@link PropertiesResourceBundleFilenameFilter} for accept only bundle properties files with
 * various locale extensions.
 */
public class PropertiesResourceBundleFilenameFilter implements FilenameFilter
{

	/** The bundlename. */
	private final String bundlename;

	/**
	 * Instantiates a new resource bundle filename filter.
	 *
	 * @param bundlename
	 *            the bundlename
	 */
	public PropertiesResourceBundleFilenameFilter(final String bundlename)
	{
		this.bundlename = bundlename;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(final File dir, final String name)
	{
		return name.matches("^" + bundlename + "(_\\w{2}(_\\w{2})?)?\\.properties$");
	}

}
