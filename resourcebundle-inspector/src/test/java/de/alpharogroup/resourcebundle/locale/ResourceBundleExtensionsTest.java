package de.alpharogroup.resourcebundle.locale;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * The class {@link ResourceBundleExtensionsTest} provides unit tests for the
 * class {@link ResourceBundleExtensions}.
 */
public class ResourceBundleExtensionsTest {

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(ResourceBundle, String)}
	 */
	@Test
	public void testGetStringResourceBundleString() {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		String expected = "Hello, there!";
		String actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title");
		AssertJUnit.assertEquals(expected, actual);
		actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title", (Object) null);
		AssertJUnit.assertEquals(expected, actual);
		expected = "Warning:!!!Missing key is 'foo.bar'!!!";
		actual = ResourceBundleExtensions.getStringQuietly(resourceBundle, "foo.bar");
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(ResourceBundle, String, String)}
	 */
	@Test
	public void testGetStringResourceBundleStringString() {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		String expected = "Hello i am Martin and i come from Germany.";
		expected = "Warning:!!!Missing key is 'foo.bar'!!!";
		final String actual = ResourceBundleExtensions.getStringQuietly(resourceBundle, "foo.bar", (String)null);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 */
	@Test
	public void testGetStringResourceBundleStringObjectArray() {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		String expected = "Hello i am Martin and i come from Germany.";
		final Object[] parameters = { "Martin", "Germany" };
		String actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.prop.with.params.label",
				parameters);
		AssertJUnit.assertEquals(expected, actual);
		expected = "Warning:!!!Missing key is 'foo.bar'!!!";
		actual = ResourceBundleExtensions.getStringQuietly(resourceBundle, "foo.bar", parameters);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)} with default value
	 */
	@Test(expectedExceptions = MissingResourceException.class)
	public void testGetStringResourceBundleStringObjectArrayWithDefaultValue() {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		final Object[] parameters = { "Martin", "Germany" };
		final String defaultValue = "Default value";
		final String expected = defaultValue;
		final String actual = ResourceBundleExtensions.getString(resourceBundle, "foo.bar", defaultValue, parameters);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(ResourceBundle, String, String, Object...)}
	 */
	@Test
	public void testGetStringResourceBundleStringStringObjectArray() {
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		final String defaultValue = "Default value";
		final String expected = "Hello i am Martin and i come from Germany.";
		final Object[] parameters = { "Martin", "Germany" };
		final String actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.prop.with.params.label", defaultValue, parameters);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(String, Locale, String, Object...)}
	 */
	@Test
	public void testGetStringStringLocaleStringObjectArray() {
		final String expected = "Hello i am Martin and i come from Germany.";
		final Object[] parameters = { "Martin", "Germany" };
		final String actual = ResourceBundleExtensions.getString("test", Locale.UK, "com.example.gui.prop.with.params.label",
				parameters);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ResourceBundleExtensions#getString(String, Locale, String)}
	 */
	@Test
	public void testGetStringStringLocaleString() {
		final String expected = "Hello, there!";
		final String actual = ResourceBundleExtensions.getString("test", Locale.UK, "com.example.gui.window.title");
		AssertJUnit.assertEquals(expected, actual);
	}

}
