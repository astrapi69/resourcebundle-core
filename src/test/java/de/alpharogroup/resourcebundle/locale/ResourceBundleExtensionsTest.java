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
package de.alpharogroup.resourcebundle.locale;

import de.alpharogroup.collections.array.ArrayFactory;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The unit test class {@link ResourceBundleExtensionsTest} provides unit tests for the class
 * {@link ResourceBundleExtensions}.
 */
public class ResourceBundleExtensionsTest
{

	/**
	 * Test method for {@link ResourceBundleExtensions#format(String, Object...)}.
	 */
	@Test
	public void testFormat()
	{
		String expected;
		String actual;
		Object[] parameters;
		String value;

		value = "Hello i am {0} and i come from {1}.";
		actual = ResourceBundleExtensions.format(value, "Brad", "Hollywood");
		expected = "Hello i am Brad and i come from Hollywood.";
		assertEquals(expected, actual);

		parameters = ArrayFactory.newArray("Brad", "Hollywood");

		value = "Hallo ich bin {0} und komme aus {1}.";
		actual = ResourceBundleExtensions.format(value, parameters);
		expected = "Hallo ich bin Brad und komme aus Hollywood.";
		assertEquals(expected, actual);

		parameters = null;

		value = "Hallo ich bin {0} und komme aus {1}.";
		actual = ResourceBundleExtensions.format(value, parameters);
		expected = "Hallo ich bin {0} und komme aus {1}.";
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(BundleKey)}.
	 */
	@Test
	public void testGetStringBundleKey()
	{
		String expected;
		String actual;
		Object[] parameters;
		BundleKey bundleKey;

		parameters = ArrayFactory.newArray("foo", "bar");
		bundleKey = BundleKey.builder().baseName("test").locale(Locale.UK)
			.resourceBundleKey(
				ResourceBundleKey.builder().key("com.example.gui.prop.with.params.label")
					.defaultValue("default value of com.example.gui.prop.with.params.label")
					.parameters(parameters).build())
			.build();
		actual = ResourceBundleExtensions.getString(bundleKey);
		expected = "Hello i am foo and i come from bar.";
		assertEquals(expected, actual);

		bundleKey = BundleKey.builder().baseName("test").locale(Locale.UK)
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
		ResourceBundle resourceBundle;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello, there!";
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
		Object[] parameters;

		parameters = ArrayFactory.newArray("foo", "bar");
		actual = ResourceBundleExtensions.getStringQuietly("test", Locale.UK,
			"com.example.gui.prop.with.params.label",
			"default value of com.example.gui.prop.with.params.label", parameters);
		expected = "Hello i am foo and i come from bar.";
		assertEquals(expected, actual);

		parameters = ArrayFactory.newArray("foo", "bar");
		actual = ResourceBundleExtensions.getStringQuietly("test", Locale.UK,
			"com.example.gui.prop.with.params.label.not-exists",
			"default value of com.example.gui.prop.with.params.label", parameters);
		expected = "default value of com.example.gui.prop.with.params.label";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 */
	@Test
	public void testGetStringResourceBundleString()
	{
		String expected;
		String actual;
		ResourceBundle resourceBundle;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
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
		ResourceBundle resourceBundle;
		Object[] parameters;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello i am Martin and i come from Germany.";
		parameters = ArrayFactory.newArray("Martin", "Germany");
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", parameters);
		assertEquals(expected, actual);

		expected = "Warning:!!!Missing key is 'foo.bar'!!!";
		actual = ResourceBundleExtensions.getStringQuietly(resourceBundle, "foo.bar", parameters);
		assertEquals(expected, actual);

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello i am Martin and i come from Germany.";
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", parameters);
		assertEquals(expected, actual);

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.GERMANY);
		parameters = ArrayFactory.newArray("Martin", "Deutschland");
		expected = "Hallo ich bin Martin und komme aus Deutschland.";
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", parameters);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 * with default value
	 */
	@Test(expectedExceptions = MissingResourceException.class)
	public void testGetStringResourceBundleStringObjectArrayWithDefaultValue()
	{
		String expected;
		String actual;
		String defaultValue;
		ResourceBundle resourceBundle;
		Object[] parameters;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		parameters = ArrayFactory.newArray("Martin", "Germany");
		defaultValue = "Default value";
		expected = defaultValue;
		actual = ResourceBundleExtensions.getString(resourceBundle, "foo.bar", defaultValue,
			parameters);
		assertEquals(expected, actual);

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		expected = "Hello i am Martin and i come from Germany.";
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", defaultValue, parameters);
		assertEquals(expected, actual);

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.GERMANY);
		parameters = ArrayFactory.newArray("Martin", "Deutschland");
		expected = "Hallo ich bin Martin und komme aus Deutschland.";
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", defaultValue, parameters);
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
		ResourceBundle resourceBundle;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
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
		String defaultValue;
		ResourceBundle resourceBundle;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
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
		String expected;
		String actual;
		String defaultValue;
		ResourceBundle resourceBundle;
		Object[] parameters;

		resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		defaultValue = "Default value";
		expected = "Hello i am Martin and i come from Germany.";
		parameters = ArrayFactory.newArray("Martin", "Germany");
		actual = ResourceBundleExtensions.getString(resourceBundle,
			"com.example.gui.prop.with.params.label", defaultValue, parameters);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String)}
	 */
	@Test
	public void testGetStringStringLocaleString()
	{
		String expected;
		String actual;

		expected = "Hello, there!";
		actual = ResourceBundleExtensions.getString("test", Locale.UK,
			"com.example.gui.window.title");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String, Object...)}
	 */
	@Test
	public void testGetStringStringLocaleStringObjectArray()
	{
		String expected;
		String actual;
		Object[] parameters;

		expected = "Hello i am Martin and i come from Germany.";
		parameters = ArrayFactory.newArray("Martin", "Germany");
		actual = ResourceBundleExtensions.getString("test", Locale.UK,
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
