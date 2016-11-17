package de.alpharogroup.resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import de.alpharogroup.resourcebundle.locale.LocaleResolver;

/**
 * The Class PropertiesFinder finds all properties file from the given root directory and save it to
 * a map with the locale string code.
 * 
 * @deprecated use instead {@link PropertiesResolver}
 */
public class PropertiesFinder
{

	/** The properties file as key and the locale string code as value. */
	@Getter
	private final Map<File, String> propertiesToLocale = new HashMap<File, String>();

	/** The root dir. */
	@Getter
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
				final String localeCode = LocaleResolver.resolveLocaleCode(file);
				propertiesToLocale.put(file, localeCode);
			}
		};
		walker.start(rootDir);
	}

}
