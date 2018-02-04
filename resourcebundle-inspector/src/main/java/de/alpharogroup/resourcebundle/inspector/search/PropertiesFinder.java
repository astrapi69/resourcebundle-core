/**
 * The MIT License
 *
 * Copyright (C) 2012 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import lombok.Getter;

/**
 * The Class PropertiesFinder finds all properties file from the given root directory and save it to
 * a map with the locale string code.
 *
 * @deprecated use instead {@link PropertiesResolver}.
 * Will be removed with the next minor version.
 */
@Deprecated
public class PropertiesFinder
{

	/** The properties file as key and the locale string code as value. */
	@Getter
	private final Map<File, String> propertiesToLocale = new HashMap<>();

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
				PropertiesFinder.this.propertiesToLocale.put(file, localeCode);
			}
		};
		walker.start(this.rootDir);
	}

}
