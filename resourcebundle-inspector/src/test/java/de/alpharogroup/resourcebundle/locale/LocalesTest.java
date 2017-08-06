package de.alpharogroup.resourcebundle.locale;

import static org.testng.AssertJUnit.assertTrue;

import java.util.Locale;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalesTest
{

	@Test
	public void testContains()
	{
		boolean condition = Locales.contains(Locales.GREEK);
		assertTrue(condition);

		condition = Locales.contains(Locales.HELLENIC);
		assertTrue(condition);

		Locale locale = Locales.HELLENIC;

		final String englishName = locale.getDisplayName(Locale.ENGLISH);
		final String country = locale.getCountry();
		String iSO3Country = "";
		try
		{
			iSO3Country = locale.getISO3Country();
		}
		catch (final Exception e)
		{
			log.error(e.getClass().getName()+ ": " +e.getMessage());
		}
		final String englishCountryName = locale.getDisplayCountry(Locale.ENGLISH);

		final String language = locale.getLanguage();
		final String iSO3Language = locale.getISO3Language();
		final String englishLanguageName = locale.getDisplayLanguage(Locale.ENGLISH);

		final String script = locale.getScript();
		final String englishScript = locale.getDisplayScript(Locale.ENGLISH);

		System.out.printf(
			"Name: %s%n" +
		"Country: %s; %s - %s%n" + ""
			+ "Language: %s; %s - %s%n"
				+ "Script: %s - %s%n",
			englishName,
			country, iSO3Country, englishCountryName,
			language, iSO3Language, englishLanguageName,
			script, englishScript);
	}

}
