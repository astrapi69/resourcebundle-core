package de.alpharogroup.resourcebundle.file.namefilter;

import static org.testng.AssertJUnit.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link PropertiesFileTypeExtensions}.
 */
public class PropertiesFileTypeExtensionsTest
{

	/**
	 * Test method for {@link PropertiesFileTypeExtensions#getPropertiesFileExtension()}
	 */
	@Test
	public void testGetPropertiesFileExtension()
	{

		String actual;
		String expected;

		actual = PropertiesFileTypeExtensions.PROPERTIES.getPropertiesFileExtension();
		expected = ".properties";
		assertEquals(expected, actual);

		actual = PropertiesFileTypeExtensions.UTF8_PROPERTIES.getPropertiesFileExtension();
		expected = ".utf8.properties";
		assertEquals(expected, actual);

		actual = PropertiesFileTypeExtensions.XML.getPropertiesFileExtension();
		expected = ".properties.xml";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link PropertiesFileTypeExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PropertiesFileTypeExtensions.class);
	}

}
