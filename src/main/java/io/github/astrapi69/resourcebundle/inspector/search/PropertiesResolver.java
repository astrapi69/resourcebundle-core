/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
package io.github.astrapi69.resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.github.astrapi69.resourcebundle.locale.LocaleResolver;
import lombok.Getter;

/**
 * The Class {@link PropertiesResolver} finds all properties file from the given root directory and
 * save it to a map with the locale string code.
 */
public class PropertiesResolver
{

	/** The properties file as key and the locale string code as value. */
	@Getter
	private final Map<File, String> propertiesToLocale = new HashMap<>();

	/** The root dir. */
	@Getter
	private final File rootDir;

	/**
	 * Instantiates a new {@link PropertiesResolver}.
	 *
	 * @param rootDir
	 *            the root dir
	 */
	public PropertiesResolver(final File rootDir)
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
	 * Resolves to the propertiesToLocale {@linkplain Map} where the properties file is the key and
	 * the locale string code the value.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void resolve() throws IOException
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
