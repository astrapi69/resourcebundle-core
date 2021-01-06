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
package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.io.File;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import de.alpharogroup.collections.set.SetFactory;

/**
 * The class {@link KeySearchBean} is model bean for search keys in all kind of files. For instance
 * in java files.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class KeySearchBean
{

	/**
	 * The Properties that contain the keys to search.
	 */
	private Properties base;
	/** The files to be excluded. */
	@Builder.Default
	private Set<File> exclude = SetFactory.newHashSet();
	/** The file extensions to find. */
	private String[] fileExtensions;
	/** The locale from the properties. */
	private Locale locale;
	/** The directory where the search shell be begin. */
	private File searchDir;

	/**
	 * Factory method to create a new {@link KeySearchBean} with the given arguments
	 *
	 * @param properties
	 *            the properties that contain the keys to search
	 * @param searchDir
	 *            the directory where the search shell be begin
	 * @param exclude
	 *            The files to be excluded
	 * @param locale
	 *            the locale from the properties
	 * @param fileExtensions
	 *            the file extensions to find
	 * @return the new {@link KeySearchBean}
	 */
	public static KeySearchBean newKeySearchBean(final Properties properties, final File searchDir,
		final Set<File> exclude, final Locale locale, final String... fileExtensions)
	{
		return KeySearchBean.builder().base(properties).searchDir(searchDir).exclude(exclude)
			.locale(locale).fileExtensions(fileExtensions).build();
	}

}
