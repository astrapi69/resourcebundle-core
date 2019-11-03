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
package de.alpharogroup.resourcebundle.file.namefilter;

import java.io.File;
import java.io.FilenameFilter;

import lombok.Getter;

/**
 * The class {@link PropertiesResourceBundleFilenameFilter} for accept only bundle properties files
 * with various locale extensions.
 */
public class PropertiesResourceBundleFilenameFilter implements FilenameFilter
{

	/** The bundlename. */
	@Getter
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
		return name.matches("^" + this.bundlename + "(_\\w{2}(_\\w{2})?)?\\.properties$");
	}

}
