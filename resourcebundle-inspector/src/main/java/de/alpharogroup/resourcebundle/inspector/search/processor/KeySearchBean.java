package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.io.File;
import java.util.HashSet;
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

/**
 * The Class KeySearchModel is model for search keys in all kind of files. For instance in java
 * files.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeySearchBean
{

	/**
	 * The Properties that contain the keys to search.
	 * */
	private Properties base;

	/** The files to be excluded. */
	private Set<File> exclude = new HashSet<File>();

	/** The file extensions to find. */
	private String[] fileExtensions;

	/** The locale from the properties. */
	private Locale locale;

	/** The directory where the search shell be begin. */
	private File searchDir;

}
