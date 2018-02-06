package de.alpharogroup.resourcebundle.locale;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link Locales}.
 */
public class LocalesTest
{

	/**
	 * Test method for {@link Locales}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(Locales.class);
	}

}
