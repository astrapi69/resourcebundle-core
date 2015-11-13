package de.alpharogroup.resourcebundle.locale;

import java.util.Locale;
import java.util.ResourceBundle;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * The class {@link ResourceBundleResolverTest} provides unit tests for the class {@link ResourceBundleResolver}.
 */
public class ResourceBundleResolverTest {

	/**
	 * Test method for {@link ResourceBundleResolver#getBundle(String, Locale)}
	 */
	@Test
	public void testGetBundle() {
		final ResourceBundle expected = ResourceBundle.getBundle("test", Locale.UK);
		ResourceBundle actual = ResourceBundleResolver.getBundle("test", Locale.UK);
		AssertJUnit.assertEquals(expected, actual);		
	}

}
