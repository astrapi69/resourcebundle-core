package de.alpharogroup.resourcebundle.inspector.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lombok.Getter;

/**
 * The Class KeyValueLists holds to lists for the keys and values from a properties file and a map
 * to save duplicate keys and the occurrences of it. It is used to find duplicate keys in a
 * properties file.
 */
public class KeyValueLists implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The keys of the properties file. */
	@Getter
	private final List<String> keys = new ArrayList<String>();

	/** The values of the properties file. */
	@Getter
	private final List<String> values = new ArrayList<String>();

	/** The duplicate map. */
	@Getter
	private final Map<String, Integer> duplicateMap = new TreeMap<String, Integer>();
	/** The duplicate map. */
	@Getter
	private final Map<String, List<String>> duplicateValueMap = new LinkedHashMap<String, List<String>>();

}
