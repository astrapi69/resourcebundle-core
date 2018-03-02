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
package de.alpharogroup.resourcebundle.inspector.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lombok.Getter;

/**
 * The Class KeyValueLists holds to lists for the keys and values from a
 * properties file and a map to save duplicate keys and the occurrences of it.
 * It is used to find duplicate keys in a properties file.
 */
public class KeyValueLists implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The duplicate map. */
	@Getter
	private final Map<String, Integer> duplicateMap = new TreeMap<String, Integer>();

	/** The duplicate map. */
	@Getter
	private final Map<String, List<String>> duplicateValueMap = new LinkedHashMap<String, List<String>>();

	/** The keys of the properties file. */
	@Getter
	private final List<String> keys = new ArrayList<String>();
	/** The values of the properties file. */
	@Getter
	private final List<String> values = new ArrayList<String>();

}
