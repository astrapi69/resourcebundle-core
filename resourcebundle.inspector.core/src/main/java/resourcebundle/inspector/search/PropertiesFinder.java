package resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.alpharogroup.locale.LocaleUtils;

/**
 * The Class PropertiesFinder finds all properties file from the given root directory and save it to
 * a map with the locale string code.
 */
public class PropertiesFinder
{

	/** The properties file as key and the locale string code as value. */
	private final Map<File, String> propertiesToLocale = new HashMap<File, String>();

	/** The root dir. */
	private final File rootDir;

	/**
	 * Instantiates a new properties finder.
	 * 
	 * @param rootDir
	 *            the root dir
	 */
	public PropertiesFinder(final File rootDir)
	{
		if (rootDir == null)
		{
			throw new IllegalArgumentException("rootDir is null.");
		}
		if (!rootDir.isDirectory())
		{
			throw new IllegalArgumentException("rootDir is not a directory.");
		}
		this.rootDir = rootDir;
	}

	/**
	 * Find.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void find() throws IOException
	{
		final PropertiesDirectoryWalker walker = new PropertiesDirectoryWalker()
		{
			@Override
			protected void handleFile(final File file, final int depth,
				final Collection<File> results) throws IOException
			{
				final String localeCode = LocaleUtils.getLocaleCode(file);
				propertiesToLocale.put(file, localeCode);
			}
		};
		walker.start(rootDir);
	}

	/**
	 * Gets the properties to locale.
	 * 
	 * @return the properties to locale
	 */
	public Map<File, String> getPropertiesToLocale()
	{
		return propertiesToLocale;
	}

	/**
	 * Gets the root dir.
	 * 
	 * @return the root dir
	 */
	public File getRootDir()
	{
		return rootDir;
	}

}
