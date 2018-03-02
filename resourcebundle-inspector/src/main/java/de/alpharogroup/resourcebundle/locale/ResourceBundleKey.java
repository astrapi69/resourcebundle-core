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
package de.alpharogroup.resourcebundle.locale;

import java.io.Serializable;

import de.alpharogroup.clone.object.CloneObjectQuietlyExtensions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link ResourceBundleKey} represents a resource bundle key.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResourceBundleKey implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The resource bundle key.
	 */
	@NonNull
	private String key;

	/**
	 * The parameters of the resource bundle key.
	 */
	private Object[] parameters;

	/**
	 * The default value.
	 */
	private String defaultValue;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResourceBundleKey clone()
	{
		return CloneObjectQuietlyExtensions.cloneQuietly(this);
	}

}
