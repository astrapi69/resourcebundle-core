package de.alpharogroup.resourcebundle.inspector.search.processor;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link UsedKeysSearchResult}.
 */
public class UsedKeysSearchResultTest {


	/**
	 * Test method for {@link UsedKeysSearchResult}
	 */
	@Test
	public void testWithBeanTester()
	{
		final Configuration configuration = new ConfigurationBuilder().build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(UsedKeysSearchResult.class, configuration);
		beanTester.testBean(UsedKeysSearchResult.class);
	}
}
