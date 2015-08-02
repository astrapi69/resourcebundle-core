package resourcebundle.inspector.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.DirectoryWalker;

import de.alpharogroup.file.FileExtension;
import de.alpharogroup.file.filter.MultiplyExtensionsFileFilter;

/**
 * The Class PropertiesDirectoryWalker finds Properties files.
 */
public class PropertiesDirectoryWalker extends DirectoryWalker<File>
{

	/**
	 * Instantiates a new properties directory walker.
	 */
	public PropertiesDirectoryWalker()
	{
		super(new MultiplyExtensionsFileFilter(true, FileExtension.PROPERTIES.getExtension()), -1);
	}

	/**
	 * Start.
	 * 
	 * @param dir
	 *            the dir
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void start(final File dir) throws IOException
	{
		walk(dir, new ArrayList<File>());
	}
}