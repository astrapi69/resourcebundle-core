package resourcebundle.inspector.search.processor;

import java.io.File;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

/**
 * The Class KeySearchModel is model for search keys in all kind of files. For
 * instance in java files.
 */
public class KeySearchModel {

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

	/**
	 * Gets the base.
	 *
	 * @return the base
	 */
	public Properties getBase() {
		return base;
	}

	/**
	 * Gets the exclude.
	 *
	 * @return the exclude
	 */
	public Set<File> getExclude() {
		return exclude;
	}

	/**
	 * Gets the file extensions.
	 *
	 * @return the file extensions
	 */
	public String[] getFileExtensions() {
		return fileExtensions;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Gets the search dir.
	 *
	 * @return the search dir
	 */
	public File getSearchDir() {
		return searchDir;
	}

	/**
	 * Sets the base.
	 *
	 * @param base
	 *            the new base
	 */
	public void setBase(Properties base) {
		this.base = base;
	}

	/**
	 * Sets the exclude.
	 *
	 * @param exclude
	 *            the new exclude
	 */
	public void setExclude(Set<File> exclude) {
		this.exclude = exclude;
	}

	/**
	 * Sets the file extensions.
	 *
	 * @param fileExtensions
	 *            the new file extensions
	 */
	public void setFileExtensions(String[] fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale
	 *            the new locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Sets the search dir.
	 *
	 * @param searchDir
	 *            the new search dir
	 */
	public void setSearchDir(File searchDir) {
		this.searchDir = searchDir;
	}
}
