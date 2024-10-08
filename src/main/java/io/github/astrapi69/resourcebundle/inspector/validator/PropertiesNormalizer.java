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
package io.github.astrapi69.resourcebundle.inspector.validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.github.astrapi69.file.exception.FileIsADirectoryException;
import io.github.astrapi69.io.file.FileExtension;
import io.github.astrapi69.resourcebundle.properties.PropertiesFileExtensions;
import lombok.experimental.UtilityClass;

/**
 * Normalizes Properties and replaces existing invalid characters to utf8 characters.
 **/
@UtilityClass
public class PropertiesNormalizer
{
	/** The invalid characters. */
	public static Map<Character, String> INVALID_CHARACTERS = new HashMap<Character, String>()
	{
		private static final long serialVersionUID = 1L;
		{
			// german
			put(Character.valueOf('ä'), "\\u00E4");
			put(Character.valueOf('Ä'), "\\u00C4");
			put(Character.valueOf('ö'), "\\u00F6");
			put(Character.valueOf('Ö'), "\\u00D6");
			put(Character.valueOf('ü'), "\\u00FC");
			put(Character.valueOf('Ü'), "\\u00DC");
			put(Character.valueOf('ß'), "\\u00DF");
			put(Character.valueOf('@'), "\\u0040");
			// greek
			put(Character.valueOf('Α'), "\\u0391");
			put(Character.valueOf('Ά'), "\\u0386");
			put(Character.valueOf('Β'), "\\u0392");
			put(Character.valueOf('Γ'), "\\u0393");
			put(Character.valueOf('Δ'), "\\u0394");
			put(Character.valueOf('Ε'), "\\u0395");
			put(Character.valueOf('Έ'), "\\u0388");
			put(Character.valueOf('Ζ'), "\\u0396");
			put(Character.valueOf('Η'), "\\u0397");
			put(Character.valueOf('Ή'), "\\u0389");
			put(Character.valueOf('Θ'), "\\u0398");
			put(Character.valueOf('Ι'), "\\u0399");
			put(Character.valueOf('Ί'), "\\u038a");
			put(Character.valueOf('Ϊ'), "\\u03aa");
			put(Character.valueOf('Κ'), "\\u039a");
			put(Character.valueOf('Λ'), "\\u039b");
			put(Character.valueOf('Μ'), "\\u039c");
			put(Character.valueOf('Ν'), "\\u039d");
			put(Character.valueOf('Ξ'), "\\u039e");
			put(Character.valueOf('Ο'), "\\u039f");
			put(Character.valueOf('Ό'), "\\u038c");
			put(Character.valueOf('Π'), "\\u03a0");
			put(Character.valueOf('Ρ'), "\\u03a1");
			put(Character.valueOf('Σ'), "\\u03a3");
			put(Character.valueOf('Τ'), "\\u03a4");
			put(Character.valueOf('Υ'), "\\u03a5");
			put(Character.valueOf('Ύ'), "\\u038e");
			put(Character.valueOf('Ϋ'), "\\u03ab");
			put(Character.valueOf('Φ'), "\\u03a6");
			put(Character.valueOf('Χ'), "\\u03a7");
			put(Character.valueOf('Ψ'), "\\u03a8");
			put(Character.valueOf('Ω'), "\\u03a9");
			put(Character.valueOf('Ώ'), "\\u038f");
			put(Character.valueOf('α'), "\\u03b1");
			put(Character.valueOf('ά'), "\\u03ac");
			put(Character.valueOf('β'), "\\u03b2");
			put(Character.valueOf('γ'), "\\u03b3");
			put(Character.valueOf('δ'), "\\u03b4");
			put(Character.valueOf('ε'), "\\u03b5");
			put(Character.valueOf('έ'), "\\u03ad");
			put(Character.valueOf('ζ'), "\\u03b6");
			put(Character.valueOf('η'), "\\u03b7");
			put(Character.valueOf('ή'), "\\u03ae");
			put(Character.valueOf('θ'), "\\u03b8");
			put(Character.valueOf('ι'), "\\u03b9");
			put(Character.valueOf('ί'), "\\u03af");
			put(Character.valueOf('ϊ'), "\\u03ca");
			put(Character.valueOf('ΐ'), "\\u0390");
			put(Character.valueOf('κ'), "\\u03ba");
			put(Character.valueOf('λ'), "\\u03bb");
			put(Character.valueOf('μ'), "\\u03bc");
			put(Character.valueOf('ν'), "\\u03bd");
			put(Character.valueOf('ξ'), "\\u03be");
			put(Character.valueOf('ο'), "\\u03bf");
			put(Character.valueOf('ό'), "\\u03cc");
			put(Character.valueOf('π'), "\\u03c0");
			put(Character.valueOf('ρ'), "\\u03c1");
			put(Character.valueOf('σ'), "\\u03c3");
			put(Character.valueOf('ς'), "\\u03c2");
			put(Character.valueOf('τ'), "\\u03c4");
			put(Character.valueOf('υ'), "\\u03c5");
			put(Character.valueOf('ύ'), "\\u03cd");
			put(Character.valueOf('ϋ'), "\\u03cb");
			put(Character.valueOf('ΰ'), "\\u03b0");
			put(Character.valueOf('φ'), "\\u03c6");
			put(Character.valueOf('χ'), "\\u03c7");
			put(Character.valueOf('ψ'), "\\u03c8");
			put(Character.valueOf('ω'), "\\u03c9");
			put(Character.valueOf('ώ'), "\\u03ce");
		}
	};

	/**
	 * Checks if the given File contains invalid characters.
	 *
	 * @param input
	 *            the input file
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("resource")
	private static boolean containsInvalidCharacters(final File input) throws IOException
	{
		Reader bufferIn = null;
		try
		{
			bufferIn = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			int r;
			while ((r = bufferIn.read()) != -1)
			{
				if (r == 65533)
				{
					return true;
				}
				final char ch = (char)r;
				final String invalidCharacter = getUtf8Character(ch);

				if (invalidCharacter != null)
				{
					return true;
				}
			}
			bufferIn.close();
		}
		catch (final IOException e)
		{
			throw e;
		}
		return false;
	}

	/**
	 * Finds properties files that contain invalid characters and adds them to the collection from
	 * the given directory recursively.
	 *
	 * @param rootDir
	 *            the root directory that shall be searched for all properties files.
	 * @return a collection with all found properties files.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Collection<File> findPropertiesFilesWithInvalidCharacters(final File rootDir)
		throws IOException
	{
		final Collection<File> found = new ArrayList<>();

		Files.walkFileTree(rootDir.toPath(), new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException
			{
				File asFile = file.toFile();
				if (asFile.getName().endsWith(FileExtension.PROPERTIES.getExtension())
					&& containsInvalidCharacters(asFile))
				{
					found.add(asFile);
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
			{
				// Handle failure case if needed
				return FileVisitResult.CONTINUE;
			}
		});

		return found;
	}

	/**
	 * Checks if the given char is an invalid character, if true than it returns it as a utf8
	 * character in a String object, otherwise it returns null.
	 *
	 * @param c
	 *            the char
	 *
	 * @return the utf8 character
	 */
	private static String getUtf8Character(final char c)
	{
		if (INVALID_CHARACTERS.containsKey(Character.valueOf(c)))
		{
			return INVALID_CHARACTERS.get(Character.valueOf(c));
		}
		return null;
	}

	/**
	 * Replaces all occurrences from invalid characters with utf8 characters for the given path from
	 * the properties file and creates a backup file for comparisons if necessary.
	 *
	 * @param path
	 *            the path from the properties file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws FileIsADirectoryException
	 *             is thrown if the given path is a directory.
	 */
	public static void normalizeProperties(final String path)
		throws IOException, FileIsADirectoryException
	{
		final File originalFile = new File(path);
		final File backupFile = PropertiesFileExtensions.newBackupOf(originalFile);
		replaceCharacters(backupFile, originalFile);
	}

	/**
	 * Reads the input file and writes the converted characters to the output file.
	 *
	 * @param input
	 *            the input file
	 * @param output
	 *            the output file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void replaceCharacters(final File input, final File output) throws IOException
	{
		Reader bufferIn = null;
		Writer bufferOut = null;

		try
		{
			final InputStream in = new FileInputStream(input);
			bufferIn = new BufferedReader(new InputStreamReader(in));

			final OutputStream out = new FileOutputStream(output);
			bufferOut = new BufferedWriter(new OutputStreamWriter(out));

			int r;
			while ((r = bufferIn.read()) != -1)
			{
				final char ch = (char)r;
				final String invalidCharacters = getUtf8Character(ch);

				if (invalidCharacters != null)
				{
					bufferOut.write(invalidCharacters.toCharArray());
				}
				else
				{
					bufferOut.write(ch);
				}
			}
			bufferOut.flush();
			bufferOut.close();
			bufferIn.close();
		}
		catch (final IOException e)
		{
			throw e;
		}
	}

}
