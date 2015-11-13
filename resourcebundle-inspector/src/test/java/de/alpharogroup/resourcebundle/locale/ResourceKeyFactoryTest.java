package de.alpharogroup.resourcebundle.locale;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

/**
 * The class {@link ResourceKeyFactoryTest} provides unit tests for the class {@link ResourceKeyFactory}.
 */
public class ResourceKeyFactoryTest {

	/**
	 * Test method for {@link ResourceKeyFactory#newResourceKey(Object, String)}
	 */
	@Test
	public void testNewResourceKeyTString() {
		final String resourceKey = ResourceKeyFactory.newResourceKey(new Person(), "foo");
		AssertJUnit.assertEquals("Person.foo", resourceKey);
	}

	/**
	 * Test method for {@link ResourceKeyFactory#newResourceKey(Object, boolean, String)}
	 */
	@Test
	public void testNewResourceKeyTBooleanString() {
		final String resourceKey = ResourceKeyFactory.newResourceKey(new Person(), false, "foo");
		AssertJUnit.assertEquals("de.alpharogroup.test.objects.Person.foo", resourceKey);
	}

}
