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
package de.alpharogroup.resourcebundle.inspector.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import lombok.Getter;

/**
 * The Class PropertiesLineReader that can read from an Reader or inputstream all lines from a
 * properties file.
 */
public class PropertiesLineReader
{

	/** The input byte buffer. */
	private byte[] inputByteBuffer;

	/** The input char buffer. */
	private char[] inputCharBuffer;

	/** The input limit. */
	private int inputLimit = 0;

	/** The input off. */
	private int inputOff = 0;

	/** The input stream. */
	private InputStream inputStream;

	/** The line buffer. */
	@Getter
	private char[] lineBuffer = new char[1024];

	/** The reader. */
	private Reader reader;

	/**
	 * Instantiates a new properties line reader.
	 *
	 * @param inStream
	 *            the in stream
	 */
	public PropertiesLineReader(final InputStream inStream)
	{
		this.inputStream = inStream;
		this.inputByteBuffer = new byte[8192];
	}

	/**
	 * Instantiates a new properties line reader.
	 *
	 * @param reader
	 *            the reader
	 */
	public PropertiesLineReader(final Reader reader)
	{
		this.reader = reader;
		this.inputCharBuffer = new char[8192];
	}

	/**
	 * Read line.
	 *
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public int readLine() throws IOException
	{
		int len = 0;
		char c;

		boolean skipWhiteSpace = true;
		boolean isCommentLine = false;
		boolean isNewLine = true;
		boolean appendedLineBegin = false;
		boolean precedingBackslash = false;
		boolean skipLineFeed = false;

		while (true)
		{
			if (inputOff >= inputLimit)
			{
				inputLimit = (inputStream == null)
					? reader.read(inputCharBuffer)
					: inputStream.read(inputByteBuffer);
				inputOff = 0;
				if (inputLimit <= 0)
				{
					if (len == 0 || isCommentLine)
					{
						return -1;
					}
					return len;
				}
			}
			if (inputStream != null)
			{
				c = (char)(0xff & inputByteBuffer[inputOff++]);
			}
			else
			{
				c = inputCharBuffer[inputOff++];
			}
			if (skipLineFeed)
			{
				skipLineFeed = false;
				if (c == '\n')
				{
					continue;
				}
			}
			if (skipWhiteSpace)
			{
				if (c == ' ' || c == '\t' || c == '\f')
				{
					continue;
				}
				if (!appendedLineBegin && (c == '\r' || c == '\n'))
				{
					continue;
				}
				skipWhiteSpace = false;
				appendedLineBegin = false;
			}
			if (isNewLine)
			{
				isNewLine = false;
				if (c == '#' || c == '!')
				{
					isCommentLine = true;
					continue;
				}
			}

			if (c != '\n' && c != '\r')
			{
				lineBuffer[len++] = c;
				if (len == lineBuffer.length)
				{
					int newLength = lineBuffer.length * 2;
					if (newLength < 0)
					{
						newLength = Integer.MAX_VALUE;
					}
					final char[] buf = new char[newLength];
					System.arraycopy(lineBuffer, 0, buf, 0, lineBuffer.length);
					lineBuffer = buf;
				}
				if (c == '\\')
				{
					precedingBackslash = !precedingBackslash;
				}
				else
				{
					precedingBackslash = false;
				}
			}
			else
			{
				if (isCommentLine || len == 0)
				{
					isCommentLine = false;
					isNewLine = true;
					skipWhiteSpace = true;
					len = 0;
					continue;
				}
				if (inputOff >= inputLimit)
				{
					inputLimit = (inputStream == null)
						? reader.read(inputCharBuffer)
						: inputStream.read(inputByteBuffer);
					inputOff = 0;
					if (inputLimit <= 0)
					{
						return len;
					}
				}
				if (precedingBackslash)
				{
					len -= 1;
					skipWhiteSpace = true;
					appendedLineBegin = true;
					precedingBackslash = false;
					if (c == '\r')
					{
						skipLineFeed = true;
					}
				}
				else
				{
					return len;
				}
			}
		}
	}
}
