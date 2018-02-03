package de.alpharogroup.resourcebundle.inspector.core;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The class {@link KeyValueLists}.
 */
public class KeyValueListsTest
{

	/**
	 * Test method for {@link KeyValueLists}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(KeyValueLists.class);
	}
}
