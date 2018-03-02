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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import de.alpharogroup.check.Check;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import lombok.Getter;

/**
 * The Class {@link PropertiesListResolver} finds all properties files from the given root directory
 * and save it to a key value list with the locales.
 */
@Getter
public class PropertiesListResolver
{

	/** The default locale. */
	private final Locale defaultLocale;

	/**
	 * The properties list with {@linkplain KeyValuePair} objects as properties file as key and the
	 * locale string code as value.
	 */
	private final List<KeyValuePair<File, Locale>> propertiesList = new ArrayList<>();

	/** The root dir. */
	private final File rootDir;

	/**
	 * Instantiates a new {@link PropertiesListResolver}.
	 *
	 * @param rootDir
	 *            the root dir
	 * @param defaultLocale
	 *            the default locale
	 */
	public PropertiesListResolver(final File rootDir, final Locale defaultLocale)
	{
		Check.get().notNull(rootDir, "rootDir");
		Check.get().notNull(defaultLocale, "defaultLocale");
		if (!rootDir.isDirectory())
		{
			throw new IllegalArgumentException("rootDir is not a directory.");
		}
		this.rootDir = rootDir;
		this.defaultLocale = defaultLocale;
	}

	/**
	 * Resolves the properties file from the given root directory and put the result to the
	 * properties list with {@linkplain KeyValuePair} objects as properties file as key and the
	 * locale string code as value.
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
				final Locale locale = LocaleResolver.resolveLocale(file, defaultLocale, false);
				propertiesList
					.add(KeyValuePair.<File, Locale> builder().key(file).value(locale).build());
			}
		};
		walker.start(rootDir);
	}

}
