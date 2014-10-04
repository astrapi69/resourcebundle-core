package resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.sourceforge.jaulp.file.FileExtension;
import net.sourceforge.jaulp.file.filter.MultiplyExtensionsFileFilter;

import org.apache.commons.io.DirectoryWalker;

/**
 * The Class PropertiesDirectoryWalker finds Properties files.
 */
public class PropertiesDirectoryWalker extends DirectoryWalker<File> {

	/**
	 * Instantiates a new properties directory walker.
	 */
	public PropertiesDirectoryWalker() {
		super(new MultiplyExtensionsFileFilter(true,
				FileExtension.PROPERTIES.getExtension()), -1);
	}

	/**
	 * Start.
	 * 
	 * @param dir
	 *            the dir
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void start(File dir) throws IOException {
		walk(dir, new ArrayList<File>());
	}
}