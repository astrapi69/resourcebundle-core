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
package io.github.astrapi69.resourcebundle.json;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

public class NgxTranslateJsonFileToPropertiesFileTest
{

	@Test
	public void testConvert() throws IOException
	{
		String actual;
		String expected;

		File jsonDir;
		File ngxTranslateJsonFile;
		File ngxTranslatePropertiesFile;
		NgxTranslateJsonFileToPropertiesFile translateJsonFileToPropertiesFile;

		String ngxTranslatePropertiesFileName = "ngxtranslate.properties";

		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		ngxTranslateJsonFile = new File(jsonDir, "en.json");
		ngxTranslatePropertiesFile = new File(jsonDir, ngxTranslatePropertiesFileName);
		translateJsonFileToPropertiesFile = NgxTranslateJsonFileToPropertiesFile
			.builder().ngxTranslateJsonFile(ngxTranslateJsonFile)
			.generatedPropertiesFile(ngxTranslatePropertiesFile).build();
		translateJsonFileToPropertiesFile.convert();
		actual = ReadFileExtensions.readFromFile(ngxTranslatePropertiesFile);
		expected = "\n" + "\n" + "myapp.title=Translation app\n"
			+ "myapp.text=Translation app for test with ngx-translate\n" + "\n"
			+ "myapp.menu.new=Translation new\n" + "myapp.menu.edit=Translation edit\n" + "\n"
			+ "myapp.menu.popup.copy=Copy\n" + "\n" + "\n" + "\n" + "\n";
		assertEquals(actual, expected);

		ngxTranslateJsonFile = new File(jsonDir, "en-long.json");
		ngxTranslatePropertiesFile = new File(jsonDir, ngxTranslatePropertiesFileName);
		translateJsonFileToPropertiesFile = NgxTranslateJsonFileToPropertiesFile
			.builder().ngxTranslateJsonFile(ngxTranslateJsonFile)
			.generatedPropertiesFile(ngxTranslatePropertiesFile).build();
		translateJsonFileToPropertiesFile.convert();
		actual = ReadFileExtensions.readFromFile(ngxTranslatePropertiesFile);
		expected = "\n" + "\n" + "myapp.title=Translation app\n"
			+ "myapp.text=Translation app for test with ngx-translate\n" + "\n"
			+ "foo.menu.new=Translation new\n" + "foo.menu.edit=Translation edit\n" + "\n"
			+ "foo.menu.popup.copy=Copy\n" + "\n" + "\n" + "\n" + "\n"
			+ "foo.title=Translation foo\n"
			+ "foo.text=Translation foo for test with ngx-translate\n" + "\n" + "\n" + "\n" + "\n"
			+ "\n" + "\n" + "\n" + "\n" + "\n";
		assertEquals(actual, expected);
	}
}