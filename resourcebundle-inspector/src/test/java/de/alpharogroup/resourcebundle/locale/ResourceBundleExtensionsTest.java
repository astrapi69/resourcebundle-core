package de.alpharogroup.resourcebundle.locale;

import java.util.Locale;
import java.util.ResourceBundle;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * The class {@link ResourceBundleExtensionsTest} provides unit tests for the class {@link ResourceBundleExtensions}.
 */
public class ResourceBundleExtensionsTest {

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String)}
	 */
	@Test
	public void testGetStringResourceBundleString() {
		ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		String expected = "Hello, there!";
		String actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.window.title");
		AssertJUnit.assertEquals(expected, actual);		
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(ResourceBundle, String, Object...)}
	 */
	@Test
	public void testGetStringResourceBundleStringObjectArray() {
		ResourceBundle resourceBundle = ResourceBundleResolver.getBundle("test", Locale.UK);
		String expected = "Hello i am Martin and i come from Germany.";
		Object [] parameters = {"Martin", "Germany"};
		String actual = ResourceBundleExtensions.getString(resourceBundle, "com.example.gui.prop.with.params.label", parameters);
		AssertJUnit.assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String, Object...)}
	 */
	@Test
	public void testGetStringStringLocaleStringObjectArray() {
		String expected = "Hello i am Martin and i come from Germany.";
		Object [] parameters = {"Martin", "Germany"};
		String actual = ResourceBundleExtensions.getString("test", Locale.UK, "com.example.gui.prop.with.params.label", parameters);
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleExtensions#getString(String, Locale, String)}
	 */
	@Test
	public void testGetStringStringLocaleString() {
		String expected = "Hello, there!";
		String actual = ResourceBundleExtensions.getString("test", Locale.UK, "com.example.gui.window.title");
		AssertJUnit.assertEquals(expected, actual);
	}

}
