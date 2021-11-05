package io.github.astrapi69.resourcebundle.json;

import io.github.astrapi69.file.search.PathFinder;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

public class NgxTranslateJsonFileToPropertiesFileTest
{

	@Test public void testConvert() throws IOException
	{
		File jsonDir;
		File ngxTranslateJsonFile;
		File ngxTranslatePropertiesFile;
		String ngxTranslatePropertiesFileName = "ngxtranslate.properties";

		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		ngxTranslateJsonFile = new File(jsonDir, "en.json");
		ngxTranslatePropertiesFile = new File(jsonDir, ngxTranslatePropertiesFileName);
		NgxTranslateJsonFileToPropertiesFile translateJsonFileToPropertiesFile =
			NgxTranslateJsonFileToPropertiesFile
				.builder()
			.ngxTranslateJsonFile(ngxTranslateJsonFile)
			.generatedPropertiesFile(ngxTranslatePropertiesFile)
			.build();
		translateJsonFileToPropertiesFile.convert();
	}
}