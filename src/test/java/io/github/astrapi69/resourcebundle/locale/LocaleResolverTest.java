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
package io.github.astrapi69.resourcebundle.locale;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.lang.ClassExtensions;

/**
 * The unit test class {@link LocaleResolverTest} provides unit tests for the class
 * {@link LocaleResolver}.
 */
public class LocaleResolverTest
{

	public static Map<String, String> newCountries()
	{
		final Map<String, String> countriesMap = new LinkedHashMap<>();
		countriesMap.put("AF", "AFG");
		countriesMap.put("AX", "ALA");
		countriesMap.put("AL", "ALB");
		countriesMap.put("DZ", "DZA");
		countriesMap.put("AS", "ASM");
		countriesMap.put("AD", "AND");
		countriesMap.put("AO", "AGO");
		countriesMap.put("AI", "AIA");
		countriesMap.put("AQ", "ATA");
		countriesMap.put("AG", "ATG");
		countriesMap.put("AR", "ARG");
		countriesMap.put("AM", "ARM");
		countriesMap.put("AW", "ABW");
		countriesMap.put("AU", "AUS");
		countriesMap.put("AT", "AUT");
		countriesMap.put("AZ", "AZE");
		countriesMap.put("BS", "BHS");
		countriesMap.put("BH", "BHR");
		countriesMap.put("BD", "BGD");
		countriesMap.put("BB", "BRB");
		countriesMap.put("BY", "BLR");
		countriesMap.put("BE", "BEL");
		countriesMap.put("BZ", "BLZ");
		countriesMap.put("BJ", "BEN");
		countriesMap.put("BM", "BMU");
		countriesMap.put("BT", "BTN");
		countriesMap.put("BO", "BOL");
		countriesMap.put("BA", "BIH");
		countriesMap.put("BW", "BWA");
		countriesMap.put("BV", "BVT");
		countriesMap.put("BR", "BRA");
		countriesMap.put("IO", "IOT");
		countriesMap.put("BN", "BRN");
		countriesMap.put("BG", "BGR");
		countriesMap.put("BF", "BFA");
		countriesMap.put("BI", "BDI");
		countriesMap.put("KH", "KHM");
		countriesMap.put("CM", "CMR");
		countriesMap.put("CA", "CAN");
		countriesMap.put("CV", "CPV");
		countriesMap.put("KY", "CYM");
		countriesMap.put("CF", "CAF");
		countriesMap.put("TD", "TCD");
		countriesMap.put("CL", "CHL");
		countriesMap.put("CN", "CHN");
		countriesMap.put("CX", "CXR");
		countriesMap.put("CC", "CCK");
		countriesMap.put("CO", "COL");
		countriesMap.put("KM", "COM");
		countriesMap.put("CG", "COG");
		countriesMap.put("CD", "COD");
		countriesMap.put("CK", "COK");
		countriesMap.put("CR", "CRI");
		countriesMap.put("CI", "CIV");
		countriesMap.put("HR", "HRV");
		countriesMap.put("CU", "CUB");
		countriesMap.put("CY", "CYP");
		countriesMap.put("CZ", "CZE");
		countriesMap.put("DK", "DNK");
		countriesMap.put("DJ", "DJI");
		countriesMap.put("DM", "DMA");
		countriesMap.put("DO", "DOM");
		countriesMap.put("EC", "ECU");
		countriesMap.put("EG", "EGY");
		countriesMap.put("SV", "SLV");
		countriesMap.put("GQ", "GNQ");
		countriesMap.put("ER", "ERI");
		countriesMap.put("EE", "EST");
		countriesMap.put("ET", "ETH");
		countriesMap.put("FK", "FLK");
		countriesMap.put("FO", "FRO");
		countriesMap.put("FJ", "FJI");
		countriesMap.put("FI", "FIN");
		countriesMap.put("FR", "FRA");
		countriesMap.put("GF", "GUF");
		countriesMap.put("PF", "PYF");
		countriesMap.put("TF", "ATF");
		countriesMap.put("GA", "GAB");
		countriesMap.put("GM", "GMB");
		countriesMap.put("GE", "GEO");
		countriesMap.put("DE", "DEU");
		countriesMap.put("GH", "GHA");
		countriesMap.put("GI", "GIB");
		countriesMap.put("GR", "GRC");
		countriesMap.put("GL", "GRL");
		countriesMap.put("GD", "GRD");
		countriesMap.put("GP", "GLP");
		countriesMap.put("GU", "GUM");
		countriesMap.put("GT", "GTM");
		countriesMap.put("GG", "GGY");
		countriesMap.put("GN", "GIN");
		countriesMap.put("GW", "GNB");
		countriesMap.put("GY", "GUY");
		countriesMap.put("HT", "HTI");
		countriesMap.put("HM", "HMD");
		countriesMap.put("VA", "VAT");
		countriesMap.put("HN", "HND");
		countriesMap.put("HK", "HKG");
		countriesMap.put("HU", "HUN");
		countriesMap.put("IS", "ISL");
		countriesMap.put("IN", "IND");
		countriesMap.put("ID", "IDN");
		countriesMap.put("IR", "IRN");
		countriesMap.put("IQ", "IRQ");
		countriesMap.put("IE", "IRL");
		countriesMap.put("IM", "IMM");
		countriesMap.put("IL", "ISR");
		countriesMap.put("IT", "ITA");
		countriesMap.put("JM", "JAM");
		countriesMap.put("JP", "JPN");
		countriesMap.put("JE", "JEY");
		countriesMap.put("JO", "JOR");
		countriesMap.put("KZ", "KAZ");
		countriesMap.put("KE", "KEN");
		countriesMap.put("KI", "KIR");
		countriesMap.put("KP", "PRK");
		countriesMap.put("KR", "KOR");
		countriesMap.put("KW", "KWT");
		countriesMap.put("KG", "KGZ");
		countriesMap.put("LA", "LAO");
		countriesMap.put("LV", "LVA");
		countriesMap.put("LB", "LBN");
		countriesMap.put("LS", "LSO");
		countriesMap.put("LR", "LBR");
		countriesMap.put("LY", "LBY");
		countriesMap.put("LI", "LIE");
		countriesMap.put("LT", "LTU");
		countriesMap.put("LU", "LUX");
		countriesMap.put("MO", "MAC");
		countriesMap.put("MK", "MKD");
		countriesMap.put("MG", "MDG");
		countriesMap.put("MW", "MWI");
		countriesMap.put("MY", "MYS");
		countriesMap.put("MV", "MDV");
		countriesMap.put("ML", "MLI");
		countriesMap.put("MT", "MLT");
		countriesMap.put("MH", "MHL");
		countriesMap.put("MQ", "MTQ");
		countriesMap.put("MR", "MRT");
		countriesMap.put("MU", "MUS");
		countriesMap.put("YT", "MYT");
		countriesMap.put("MX", "MEX");
		countriesMap.put("FM", "FSM");
		countriesMap.put("MD", "MDA");
		countriesMap.put("MC", "MCO");
		countriesMap.put("MN", "MNG");
		countriesMap.put("ME", "MNE");
		countriesMap.put("MS", "MSR");
		countriesMap.put("MA", "MAR");
		countriesMap.put("MZ", "MOZ");
		countriesMap.put("MM", "MMR");
		countriesMap.put("NA", "NAM");
		countriesMap.put("NR", "NRU");
		countriesMap.put("NP", "NPL");
		countriesMap.put("NL", "NLD");
		countriesMap.put("AN", "ANT");
		countriesMap.put("NC", "NCL");
		countriesMap.put("NZ", "NZL");
		countriesMap.put("NI", "NIC");
		countriesMap.put("NE", "NER");
		countriesMap.put("NG", "NGA");
		countriesMap.put("NU", "NIU");
		countriesMap.put("NF", "NFK");
		countriesMap.put("MP", "MNP");
		countriesMap.put("NO", "NOR");
		countriesMap.put("OM", "OMN");
		countriesMap.put("PK", "PAK");
		countriesMap.put("PW", "PLW");
		countriesMap.put("PS", "PSE");
		countriesMap.put("PA", "PAN");
		countriesMap.put("PG", "PNG");
		countriesMap.put("PY", "PRY");
		countriesMap.put("PE", "PER");
		countriesMap.put("PH", "PHL");
		countriesMap.put("PN", "PCN");
		countriesMap.put("PL", "POL");
		countriesMap.put("PT", "PRT");
		countriesMap.put("PR", "PRI");
		countriesMap.put("QA", "QAT");
		countriesMap.put("RE", "REU");
		countriesMap.put("RO", "ROU");
		countriesMap.put("RU", "RUS");
		countriesMap.put("RW", "RWA");
		countriesMap.put("BL", "BLM");
		countriesMap.put("SH", "SHN");
		countriesMap.put("KN", "KNA");
		countriesMap.put("LC", "LCA");
		countriesMap.put("MT", "MAF");
		countriesMap.put("PM", "SPM");
		countriesMap.put("VC", "VCT");
		countriesMap.put("WS", "WSM");
		countriesMap.put("SM", "SMR");
		countriesMap.put("ST", "STP");
		countriesMap.put("SA", "SAU");
		countriesMap.put("SN", "SEN");
		countriesMap.put("RS", "SRB");
		countriesMap.put("SC", "SYC");
		countriesMap.put("SL", "SLE");
		countriesMap.put("SG", "SGP");
		countriesMap.put("SK", "SVK");
		countriesMap.put("SI", "SVN");
		countriesMap.put("SB", "SLB");
		countriesMap.put("SO", "SOM");
		countriesMap.put("ZA", "ZAF");
		countriesMap.put("GS", "SGS");
		countriesMap.put("ES", "ESP");
		countriesMap.put("LK", "LKA");
		countriesMap.put("SD", "SDN");
		countriesMap.put("SR", "SUR");
		countriesMap.put("SJ", "SJM");
		countriesMap.put("SZ", "SWZ");
		countriesMap.put("SE", "SWE");
		countriesMap.put("CH", "CHE");
		countriesMap.put("SY", "SYR");
		countriesMap.put("TW", "TWN");
		countriesMap.put("TJ", "TJK");
		countriesMap.put("TZ", "TZA");
		countriesMap.put("TH", "THA");
		countriesMap.put("TL", "TLS");
		countriesMap.put("TG", "TGO");
		countriesMap.put("TK", "TKL");
		countriesMap.put("TO", "TON");
		countriesMap.put("TT", "TTO");
		countriesMap.put("TN", "TUN");
		countriesMap.put("TR", "TUR");
		countriesMap.put("TM", "TKM");
		countriesMap.put("TC", "TCA");
		countriesMap.put("TV", "TUV");
		countriesMap.put("UG", "UGA");
		countriesMap.put("UA", "UKR");
		countriesMap.put("AE", "ARE");
		countriesMap.put("GB", "GBR");
		countriesMap.put("US", "USA");
		countriesMap.put("UM", "UMI");
		countriesMap.put("UY", "URY");
		countriesMap.put("UZ", "UZB");
		countriesMap.put("VU", "VUT");
		countriesMap.put("VA", "VAT");
		countriesMap.put("VE", "VEN");
		countriesMap.put("VN", "VNM");
		countriesMap.put("VG", "VGB");
		countriesMap.put("VI", "VIR");
		countriesMap.put("WF", "WLF");
		countriesMap.put("EH", "ESH");
		countriesMap.put("YE", "YEM");
		countriesMap.put("YU", "YUG");
		countriesMap.put("ZM", "ZMB");
		countriesMap.put("ZW", "ZWE");

		return countriesMap;
	}

	/**
	 * Test method for {@link LocaleResolver#getAvailableLocales()}
	 */
	@Test(enabled = true)
	public void testGetAvailableLocalesNames()
	{
		// List<Locale> availableLocales = LocaleResolver.getAvailableLocales();
		// final Map<String, String> countriesMap = newCountries();
		// final Map<String, String> countries = MapFactory.newHashMap();
		// for (Locale locale : availableLocales)
		// {
		// if (countriesMap.containsKey(locale.getCountry()))
		// {
		// countries.put(locale.getCountry(), locale.getDisplayCountry(Locale.ENGLISH));
		// System.out.println("countriesMap.put(\"" + locale.getCountry() + "\", \""
		// + locale.getDisplayCountry(Locale.ENGLISH) + "\");");
		//
		// }
		// }
		// System.out.println(countries.size());
	}

	/**
	 * Test method for {@link LocaleResolver#getLocale(String, String)}
	 */
	@Test(enabled = true)
	public void testGetLocale()
	{
		Locale expected;
		Locale actual;
		actual = LocaleResolver.getLocale("de", "DE");
		expected = Locale.GERMANY;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#getLocales(String)}
	 */
	@Test(enabled = true)
	public void testGetLocales()
	{
		List<Locale> expected;
		List<Locale> actual;
		actual = LocaleResolver.getLocales("de");
		expected = ListFactory.newArrayList();
		expected.add(new Locale("de", "CH"));
		expected.add(new Locale("de", "AT"));
		expected.add(new Locale("de", "LU"));
		expected.add(Locale.GERMAN);
		expected.add(Locale.GERMANY);
		for (Locale l : expected)
		{
			assertTrue(actual.contains(l));
		}
	}

	/**
	 * Test method for {@link LocaleResolver#isISOCountryCode(String)}
	 */
	@Test(enabled = true)
	public void testIsISOCountryCode()
	{

		final String toSmall = "D";
		final String de = "de";
		final String doesNotExist = "AB";

		assertFalse("", LocaleResolver.isISOCountryCode(toSmall));

		assertFalse("", LocaleResolver.isISOCountryCode(doesNotExist));

		assertTrue("", LocaleResolver.isISOCountryCode(de));
	}

	/**
	 * Test method for {@link LocaleResolver#resolveAvailableLanguages(String, String)}
	 */
	@Test(enabled = true)
	public void testResolveAvailableLanguagesStringString()
	{
		final String bundlepackage = "io/github/astrapi69/lang";
		final String bundlename = "resources";
		final Set<String> availableLanguages = LocaleResolver
			.resolveAvailableLanguages(bundlepackage, bundlename);
		assertTrue(availableLanguages.contains("de"));
		assertTrue(availableLanguages.contains("de_DE"));
		assertTrue(availableLanguages.contains("en"));
		assertTrue(availableLanguages.contains("default"));
	}

	/**
	 * Test method for {@link LocaleResolver#resolveBundlename(File)}
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	@Test(enabled = true)
	public void testResolveBundlename() throws URISyntaxException
	{
		String expected;
		String actual;
		String propertiesFilename;
		File propertiesFile;

		expected = "resources";

		propertiesFilename = "io/github/astrapi69/lang/resources_de.properties";
		propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		actual = LocaleResolver.resolveBundlename(propertiesFile);
		assertTrue(expected.equals(actual));

		propertiesFilename = "io/github/astrapi69/lang/resources.properties";
		propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		actual = LocaleResolver.resolveBundlename(propertiesFile);
		assertTrue(expected.equals(actual));

	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocaleCode(File)}
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	@Test(enabled = true)
	public void testResolveLocaleCodeFile() throws URISyntaxException
	{
		final String propertiesFilename = "io/github/astrapi69/lang/resources_de.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		final String expected = "de";
		final String actual = LocaleResolver.resolveLocaleCode(propertiesFile);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocaleCode(String)}
	 */
	@Test(enabled = true)
	public void testResolveLocaleCodeString()
	{
		String code = null;
		Locale actual = LocaleResolver.resolveLocaleCode(code);
		code = "";
		actual = LocaleResolver.resolveLocaleCode(code);
		assertNull(actual);
		code = "de";
		actual = LocaleResolver.resolveLocaleCode(code);
		Locale expected = new Locale(code);
		assertEquals(expected, actual);
		code = "de_DE";
		actual = LocaleResolver.resolveLocaleCode(code);
		expected = new Locale("de", "DE");
		assertEquals(expected, actual);

		code = "de_DE_platt";
		actual = LocaleResolver.resolveLocaleCode(code);
		expected = new Locale("de", "DE", "platt");
		assertEquals(expected, actual);

		code = "de_DE_platt_flaem";
		actual = LocaleResolver.resolveLocaleCode(code);
		expected = null;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocale(File)}.
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	@Test(enabled = true)
	public void testResolveLocaleFile() throws URISyntaxException
	{
		final String propertiesFilename = "io/github/astrapi69/lang/resources_de.properties";
		final File propertiesFile = ClassExtensions.getResourceAsFile(propertiesFilename);
		final String code = "de";
		final Locale expected = new Locale(code);
		final Locale actual = LocaleResolver.resolveLocale(propertiesFile);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocales(String, String)}
	 */
	@Test(enabled = true)
	public void testResolveLocales()
	{
		final String bundlepackage = "io/github/astrapi69/lang";
		final String bundlename = "resources";
		final Map<File, Locale> fileToLocaleMap = LocaleResolver.resolveLocales(bundlepackage,
			bundlename);

		assertTrue(fileToLocaleMap.containsValue(Locale.GERMAN));
		assertTrue(fileToLocaleMap.containsValue(Locale.GERMANY));
		assertTrue(fileToLocaleMap.containsValue(Locale.ENGLISH));
	}

	/**
	 * Test method for {@link LocaleResolver#resolveLocale(String)}
	 */
	@Test(enabled = true)
	public void testResolveLocaleString()
	{
		String code = null;
		Locale actual = LocaleResolver.resolveLocale(code);
		code = "";
		actual = LocaleResolver.resolveLocale(code);
		assertEquals(Locale.getDefault(), actual);

		code = "de";
		actual = LocaleResolver.resolveLocale(code);
		Locale expected = new Locale(code);
		assertEquals(expected, actual);

		code = "de_DE";
		actual = LocaleResolver.resolveLocale(code);
		expected = new Locale("de", "DE");
		assertEquals(expected, actual);

		code = "de_DE_platt";
		actual = LocaleResolver.resolveLocale(code);
		expected = new Locale("de", "DE", "platt");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LocaleResolver#resolveAvailableLocales()}
	 */
	@Test(enabled = true)
	public void testRresolveAvailableLocales()
	{
		final List<Locale> availableLocales = LocaleResolver.resolveAvailableLocales();
		assertNotNull(availableLocales);
		assertFalse(availableLocales.isEmpty());
	}

	/**
	 * Test method for {@link LocaleResolver}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(LocaleResolver.class);
	}

}
