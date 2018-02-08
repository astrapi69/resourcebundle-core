/**
 * The MIT License
 *
 * Copyright (C) 2012 Asterios Raptis
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
package de.alpharogroup.resourcebundle.locale;

import static org.testng.AssertJUnit.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class {@link ResourceBundleExtensionsTest} provides unit tests for the class
 * {@link ResourceBundleExtensions}.
 */
public class ResourceBundleExtensionsTest
{

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(BundleKey)}.
	 */
	@Test
	public void testGetStringBundleKey()
	{
		String expected;
		String actual;
		final Object[] parameters = { "foo", "bar" };
		final BundleKey bundleKey = BundleKey.builder().baseName("test").locale(Locale.UK)
			.resourceBundleKey(
				ResourceBundleKey.builder().key("com.example.gui.prop.with.params.label")
					.defaultValue("default value of com.example.gui.prop.with.params.label")
					.parameters(parameters).build())
			.build();
		actual = ResourceBundleExtensions.getString(bundleKey);
		expected = "Hello i am foo and i come from bar.";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getStringQuietly(ResourceBundle, String, String)}.
	 */
	@Test
	public void testGetStringQuietlyResourceBundleStringString()
	{
		String expected;
		String actual;
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello, there!";
		actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title");
		actual = ResourceBundleExtensions.getStringQuietly(resourceBundle,
			"com.example.gui.window.title", "default value of com.example.gui.window.title");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getStringQuietly(String, Locale, String, String, Object[])}.
	 */
	@Test
	public void testGetStringQuietlyStringLocaleStringStringObjectArray()
	{
		String expected;
		String actual;
		final Object[] parameters = { "foo", "bar" };
		actual = ResourceBundleExtensions.getStringQuietly("test", Locale.UK,
			"com.example.gui.prop.with.params.label",
			"default value of com.example.gui.prop.with.params.label", parameters);
		expected = "Hello i am foo and i come from bar.";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String)}
	 */
	@Test
	public void testGetStringResourceBundleString()
	{
		String expected;
		String actual;
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello, there!";
		actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title");
		assertEquals(expected, actual);
		actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title",
			(Object)null);
		assertEquals(expected, actual);
		expected = "Warning:!!!Missing key is 'foo.bar'!!!";
		actual = ResourceBundleExtensions.getStringQuietly(resourceBundle, "foo.bar");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 */
	@Test
	public void testGetStringResourceBundleStringObjectArray()
	{
		String expected;
		String actual;
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello i am Martin and i come from Germany.";
		final Object[] parameters = { "Martin", "Germany" };
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", parameters);
		assertEquals(expected, actual);
		expected = "Warning:!!!Missing key is 'foo.bar'!!!";
		actual = ResourceBundleExtensions.getStringQuietly(resourceBundle, "foo.bar", parameters);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 * with default value
	 */
	@Test(expectedExceptions = MissingResourceException.class)
	public void testGetStringResourceBundleStringObjectArrayWithDefaultValue()
	{
		final String expected;
		final String actual;
		String defaultValue;
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		final Object[] parameters = { "Martin", "Germany" };
		defaultValue = "Default value";
		expected = defaultValue;
		actual = ResourceBundleExtensions.getString(resourceBundle, "foo.bar", defaultValue,
			parameters);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, String)}
	 */
	@Test
	public void testGetStringResourceBundleStringString()
	{
		final String expected;
		final String actual;
		final String defaultValue;
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		defaultValue = "Default value";
		actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title",
			defaultValue);
		expected = "Hello, there!";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, String)}
	 */
	@Test(expectedExceptions = MissingResourceException.class)
	public void testGetStringResourceBundleStringStringException()
	{
		final String defaultValue;
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		defaultValue = "Default value";
		ResourceBundleExtensions.getString(resourceBundle, "foo.bar", defaultValue);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(ResourceBundle, String, String, Object...)}
	 */
	@Test
	public void testGetStringResourceBundleStringStringObjectArray()
	{
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		final String defaultValue = "Default value";
		final String expected = "Hello i am Martin and i come from Germany.";
		final Object[] parameters = { "Martin", "Germany" };
		final String actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", defaultValue, parameters);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String)}
	 */
	@Test
	public void testGetStringStringLocaleString()
	{
		final String expected = "Hello, there!";
		final String actual = ResourceBundleExtensions.getString("test", Locale.UK,
			"com.example.gui.window.title");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String, Object...)}
	 */
	@Test
	public void testGetStringStringLocaleStringObjectArray()
	{
		final String expected = "Hello i am Martin and i come from Germany.";
		final Object[] parameters = { "Martin", "Germany" };
		final String actual = ResourceBundleExtensions.getString("test", Locale.UK,
			"com.example.gui.prop.with.params.label", parameters);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String, String)}.
	 */
	@Test
	public void testGetStringStringLocaleStringString()
	{
		String expected;
		String actual;
		actual = ResourceBundleExtensions.getString("test", Locale.UK,
			"com.example.gui.window.title", "foo bar");
		expected = "Hello, there!";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ResourceBundleExtensions.class);
	}

}
