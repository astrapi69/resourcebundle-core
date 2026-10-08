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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Documents #21 and pins its fix: what {@link ResourceBundleExtensions} hands out for a value that
 * holds an apostrophe, a quoted word or a parameter pattern.
 * <p>
 * <b>The error.</b> {@link java.text.MessageFormat} reads a single apostrophe as the start of a
 * quoted section and two as one apostrophe. Up to 6.0, the lookups without parameters passed
 * {@code (Object)null} on - an array with one element, which
 * {@link ResourceBundleExtensions#format(String, Object...)} formats - and
 * {@code getStringQuietly(..., parameters)} formatted a value that {@code getString} had formatted
 * already. Measured with this class against the code before the fix (commit 4df6415, 6.0 with the
 * next version number): 25 of its 40 cases failed.
 * <ul>
 * <li>{@code getStringQuietly(bundle, key, defaultValue)}: "don't" came back as "dont", "a 'word'
 * here" as "a word here", "don''t" as "dont", "Hello {0}" as "Hello null", and "''{0}'' already
 * exists" as "null already exists"</li>
 * <li>{@code getString(bundle, key, defaultValue)}, {@code getString(baseName, locale, key)} and
 * {@code getString(baseName, locale, key, defaultValue)}: the same, formatted once - "dont", "a
 * word here", "don't", "Hello null", "'null' already exists"</li>
 * <li>{@code getStringQuietly(..., "file.txt")}, by bundle and by base name: "''{0}'' already
 * exists" came back as "file.txt already exists", "don''t {0}" with "stop" as "dont stop"</li>
 * <li>a pattern read without parameters and formatted by its caller, as mystic-crypt-ui does with
 * the question before it replaces a file: "null already exists"</li>
 * </ul>
 * The two-argument lookups, {@code getString(bundle, key, parameters)} with parameters,
 * {@code getString(BundleKey)} and a missing key's default were right already, and are here so that
 * every variant is held to the same rule. Found by mystic-crypt-ui, which lost the apostrophes of
 * 34 texts (astrapi69/mystic-crypt-ui#533).
 * <p>
 * <b>The fix.</b> A value read without parameters comes back as the bundle holds it; a value read
 * with parameters is formatted once. A missing key still returns the default as it is.
 */
public class ResourceBundleExtensionsApostropheTest
{

	/** The bundle of {@code src/test/resources/apostrophes.properties} */
	private static final String BASE_NAME = "apostrophes";

	private static final ResourceBundle BUNDLE = ResourceBundleResolver.getBundle(BASE_NAME,
		Locale.ROOT);

	/**
	 * Every lookup without parameters, by bundle and by base name, and every value that holds
	 * something MessageFormat would read: each comes back exactly as the bundle holds it
	 *
	 * @return the variant, how it looks up a key, the key, and what it must come back as
	 */
	@DataProvider
	public static Object[][] withoutParameters()
	{
		Object[][] variants = {
				{ "getStringQuietly(bundle, key, defaultValue)", lookup(
					key -> ResourceBundleExtensions.getStringQuietly(BUNDLE, key, "default")) },
				{ "getString(bundle, key, defaultValue)",
						lookup(key -> ResourceBundleExtensions.getString(BUNDLE, key, "default")) },
				{ "getStringQuietly(bundle, key)",
						lookup(key -> ResourceBundleExtensions.getStringQuietly(BUNDLE, key)) },
				{ "getString(bundle, key)",
						lookup(key -> ResourceBundleExtensions.getString(BUNDLE, key)) },
				{ "getString(baseName, locale, key)", lookup(
					key -> ResourceBundleExtensions.getString(BASE_NAME, Locale.ROOT, key)) },
				{ "getString(baseName, locale, key, defaultValue)",
						lookup(key -> ResourceBundleExtensions.getString(BASE_NAME, Locale.ROOT,
							key, "default")) } };
		Object[][] values = { { "single", "don't" }, { "quoted", "a 'word' here" },
				{ "doubled", "don''t" }, { "pattern", "Hello {0}" },
				{ "quoted.parameter", "''{0}'' already exists" } };
		Object[][] rows = new Object[variants.length * values.length][];
		int row = 0;
		for (Object[] variant : variants)
		{
			for (Object[] value : values)
			{
				rows[row++] = new Object[] { variant[0], variant[1], value[0], value[1] };
			}
		}
		return rows;
	}

	/**
	 * A value read without parameters comes back as the bundle holds it. Up to 6.0 the variants
	 * with a default value formatted it, and "don't" came back as "dont" (#21)
	 *
	 * @param variant
	 *            the lookup, for the report
	 * @param lookup
	 *            how the variant looks up a key
	 * @param key
	 *            the key
	 * @param expected
	 *            the value as the bundle holds it
	 */
	@Test(dataProvider = "withoutParameters")
	public void testAValueReadWithoutParametersComesBackAsTheBundleHoldsIt(final String variant,
		final Function<String, String> lookup, final String key, final String expected)
	{
		assertEquals(variant + " for '" + key + "'", expected, lookup.apply(key));
	}

	/**
	 * Every lookup with parameters, by bundle, by base name and by {@link BundleKey}: each formats
	 * the value once
	 *
	 * @return the variant, how it looks up a key with one parameter, the key, the parameter, and
	 *         the formatted value
	 */
	@DataProvider
	public static Object[][] withParameters()
	{
		Object[][] variants = {
				{ "getStringQuietly(bundle, key, defaultValue, parameters)",
						withParameter((key, parameter) -> ResourceBundleExtensions
							.getStringQuietly(BUNDLE, key, "default", parameter)) },
				{ "getString(bundle, key, defaultValue, parameters)",
						withParameter((key, parameter) -> ResourceBundleExtensions.getString(BUNDLE,
							key, "default", parameter)) },
				{ "getStringQuietly(baseName, locale, key, defaultValue, parameters)",
						withParameter((key, parameter) -> ResourceBundleExtensions
							.getStringQuietly(BASE_NAME, Locale.ROOT, key, "default", parameter)) },
				{ "getString(BundleKey)", withParameter((key,
					parameter) -> ResourceBundleExtensions.getString(BundleKey.builder()
						.baseName(BASE_NAME).locale(Locale.ROOT)
						.resourceBundleKey(ResourceBundleKey.builder().key(key)
							.defaultValue("default").parameters(new Object[] { parameter }).build())
						.build())) } };
		Object[][] values = { { "quoted.parameter", "file.txt", "'file.txt' already exists" },
				{ "doubled.with.parameter", "stop", "don't stop" } };
		Object[][] rows = new Object[variants.length * values.length][];
		int row = 0;
		for (Object[] variant : variants)
		{
			for (Object[] value : values)
			{
				rows[row++] = new Object[] { variant[0], variant[1], value[0], value[1], value[2] };
			}
		}
		return rows;
	}

	/**
	 * A value read with parameters is formatted once: a doubled apostrophe comes back as one, and
	 * the quotes around a parameter stay. Up to 6.0 getStringQuietly formatted twice, and "''{0}''
	 * already exists" came back as "file.txt already exists" (#21)
	 *
	 * @param variant
	 *            the lookup, for the report
	 * @param lookup
	 *            how the variant looks up a key with one parameter
	 * @param key
	 *            the key
	 * @param parameter
	 *            the parameter
	 * @param expected
	 *            the value formatted once
	 */
	@Test(dataProvider = "withParameters")
	public void testAValueReadWithParametersIsFormattedOnce(final String variant,
		final ParameterLookup lookup, final String key, final String parameter,
		final String expected)
	{
		assertEquals(variant + " for '" + key + "'", expected, lookup.apply(key, parameter));
	}

	/**
	 * A pattern read without parameters and formatted by its caller names its parameter - what
	 * mystic-crypt-ui does with the question before it replaces a file. Up to 6.0 the lookup had
	 * filled the pattern with null already, and the question read "null already exists" (#21,
	 * astrapi69/mystic-crypt-ui#533)
	 */
	@Test
	public void testAPatternReadWithoutParametersCanBeFormattedByItsCaller()
	{
		String pattern = ResourceBundleExtensions.getStringQuietly(BUNDLE, "quoted.parameter",
			"default");

		assertEquals("'chain.sha256' already exists",
			MessageFormat.format(pattern, "chain.sha256"));
	}

	/**
	 * A missing key returns the default value as it is, apostrophe included - the one path #21 did
	 * not touch
	 */
	@Test
	public void testAMissingKeyReturnsTheDefaultValueAsItIs()
	{
		assertEquals("it's the default",
			ResourceBundleExtensions.getStringQuietly(BUNDLE, "missing", "it's the default"));
	}

	private static Function<String, String> lookup(final Function<String, String> lookup)
	{
		return lookup;
	}

	private static ParameterLookup withParameter(final ParameterLookup lookup)
	{
		return lookup;
	}

	/**
	 * A lookup of a key with one parameter
	 */
	@FunctionalInterface
	interface ParameterLookup
	{

		/**
		 * Looks up the key and formats it with the parameter
		 *
		 * @param key
		 *            the key
		 * @param parameter
		 *            the parameter
		 * @return the formatted value
		 */
		String apply(String key, String parameter);
	}
}
