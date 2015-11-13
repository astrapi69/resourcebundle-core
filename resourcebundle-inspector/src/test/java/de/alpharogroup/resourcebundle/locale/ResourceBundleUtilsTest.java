package de.alpharogroup.resourcebundle.locale;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

/**
 * The class {@link ResourceBundleUtilsTest} provides unit tests for the class {@link ResourceBundleUtils}.
 */
public class ResourceBundleUtilsTest {

	/**
	 * Test method for {@link ResourceKeyFactory#newResourceKey(Object, String)}
	 */
	@Test
	public void newResourceKeyTest()
	{
		final String resourceKey = ResourceKeyFactory.newResourceKey(new Person(), "foo");
		AssertJUnit.assertEquals("Person.foo", resourceKey);
	}

}
