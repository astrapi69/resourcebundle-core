package de.alpharogroup.resourcebundle.inspector.core;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The class {@link Umlaute}.
 */
public class UmlauteTest
{

	/**
	 * Test method for {@link Umlaute}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(Umlaute.class);
	}

}
