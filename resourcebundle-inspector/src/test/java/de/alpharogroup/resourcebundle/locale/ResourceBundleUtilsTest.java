package de.alpharogroup.resourcebundle.locale;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

public class ResourceBundleUtilsTest {

	@Test
	public void newResourceKeyTest()
	{
		final String resourceKey = ResourceBundleUtils.newResourceKey(new Person(), "foo");
		AssertJUnit.assertEquals("Person.foo", resourceKey);
	}

}
