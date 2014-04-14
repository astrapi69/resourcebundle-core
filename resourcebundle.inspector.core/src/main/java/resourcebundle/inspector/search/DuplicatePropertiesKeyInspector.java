package resourcebundle.inspector.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Class DuplicatePropertiesKeyInspector.
 */
public class DuplicatePropertiesKeyInspector {


	/** The result. */
	private final KeyValueLists result;

	/**
	 * Instantiates a new duplicate properties key finder.
	 *
	 * @param propertiesFile the properties file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DuplicatePropertiesKeyInspector(File propertiesFile) throws IOException {
		this.result = findDuplicateKeys(propertiesFile);
	}

	/**
	 * Instantiates a new duplicate properties key finder.
	 *
	 * @param inputStream the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DuplicatePropertiesKeyInspector(InputStream inputStream)
			throws IOException {
		this.result = findDuplicateKeys(inputStream);
	}

	/**
	 * Find duplicate keys from the given properties file.
	 * 
	 * @param propertiesFile
	 *            the properties file
	 * @return the map with the duplicate keys as key and the occurences as
	 *         value.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists findDuplicateKeys(File propertiesFile)
			throws IOException {
		return findDuplicateKeys(new FileInputStream(propertiesFile));
	}

	/**
	 * Find duplicate keys from the given properties file as input stream.
	 * 
	 * @param inputStream
	 *            the properties file as InputStream.
	 * @return the KeyValueLists that hold a map with the duplicate keys as key
	 *         and the occurences as value.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists findDuplicateKeys(InputStream inputStream)
			throws IOException {
		Set<String> set = new TreeSet<String>();
		KeyValueLists keyValueLists = read(inputStream);
		List<String> keys = keyValueLists.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if (!set.add(key)) {
				if (keyValueLists.getDuplicateMap().containsKey(key)) {
					keyValueLists.getDuplicateMap()
							.put(key,
									keyValueLists.getDuplicateMap().get(key)
											.intValue() + 1);
					List<String> duplicateValues = keyValueLists
							.getDuplicateValueMap().get(key);
					String currentValue = keyValueLists.getValues().get(i);
					duplicateValues.add(currentValue);
				} else {
					keyValueLists.getDuplicateMap().put(key, 1);
					keyValueLists.getDuplicateValueMap().put(key,
							new ArrayList<String>());
					List<String> duplicateValues = keyValueLists
							.getDuplicateValueMap().get(key);
					String currentValue = keyValueLists.getValues().get(i);
					duplicateValues.add(currentValue);
				}
			}
		}
		return keyValueLists;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public KeyValueLists getResult() {
		return result;
	}

	/**
	 * Read from inputstream.
	 * 
	 * @param inputStream
	 *            the inputStream
	 * @return the key value lists
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists read(InputStream inputStream) throws IOException {
		return read(new PropertiesLineReader(inputStream));
	}

	/**
	 * Read from given PropertiesLineReader.
	 * 
	 * @param propertiesLineReader
	 *            the lr
	 * @return the key value lists
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private KeyValueLists read(PropertiesLineReader propertiesLineReader)
			throws IOException {
		char[] buffer = new char[1024];
		int limit;
		int keyLength;
		int valueStart;
		char c;
		boolean hasSep;
		boolean precedingBackslash;
		KeyValueLists keyValueLists = new KeyValueLists();
		while ((limit = propertiesLineReader.readLine()) >= 0) {
			c = 0;
			keyLength = 0;
			valueStart = limit;
			hasSep = false;
			precedingBackslash = false;
			while (keyLength < limit) {
				c = propertiesLineReader.getLineBuffer()[keyLength];
				if ((c == '=' || c == ':') && !precedingBackslash) {
					valueStart = keyLength + 1;
					hasSep = true;
					break;
				} else if ((c == ' ' || c == '\t' || c == '\f')
						&& !precedingBackslash) {
					valueStart = keyLength + 1;
					break;
				}
				if (c == '\\') {
					precedingBackslash = !precedingBackslash;
				} else {
					precedingBackslash = false;
				}
				keyLength++;
			}
			while (valueStart < limit) {
				c = propertiesLineReader.getLineBuffer()[valueStart];
				if (c != ' ' && c != '\t' && c != '\f') {
					if (!hasSep && (c == '=' || c == ':')) {
						hasSep = true;
					} else {
						break;
					}
				}
				valueStart++;
			}
			char[] lineBuffer = propertiesLineReader.getLineBuffer();
			String key = readPartOfLine(lineBuffer, 0, keyLength, buffer);
			String value = readPartOfLine(lineBuffer, valueStart, limit
					- valueStart, buffer);
			keyValueLists.getKeys().add(key);
			keyValueLists.getValues().add(value);
		}
		return keyValueLists;
	}	

	/**
	 * Converts encoded &#92;uxxxx to unicode chars and changes special saved
	 * chars to their original forms.
	 * 
	 * @param in
	 *            the in
	 * @param off
	 *            the off
	 * @param len
	 *            the len
	 * @param buffer
	 *            the buffer
	 * @return the string
	 */
	private String readPartOfLine(char[] in, int off, int len,
			char[] buffer) {
		if (buffer.length < len) {
			int newLength = len * 2;
			if (newLength < 0) {
				newLength = Integer.MAX_VALUE;
			}
			buffer = new char[newLength];
		}
		char aChar;
		char[] out = buffer;
		int outputLength = 0;
		int end = off + len;

		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx(Hex-values)
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed \\uxxxx encoding.");
						}
					}
					out[outputLength++] = (char) value;
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					out[outputLength++] = aChar;
				}
			} else {
				out[outputLength++] = aChar;
			}
		}
		return new String(out, 0, outputLength);
	}

}
