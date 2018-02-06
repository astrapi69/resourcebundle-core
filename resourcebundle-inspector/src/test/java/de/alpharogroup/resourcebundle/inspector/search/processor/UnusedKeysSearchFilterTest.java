package de.alpharogroup.resourcebundle.inspector.search.processor;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link UnusedKeysSearchFilter}.
 */
public class UnusedKeysSearchFilterTest
{

	/**
	 * Test method for {@link UnusedKeysSearchFilter#process(UsedKeysSearchResult)}.
	 */
	@Test
	public void testProcess()
	{
		final Properties used = new Properties();
		used.setProperty("com", "bar");
		final Properties base = new Properties();
		base.setProperty("com", "bar");
		base.setProperty("bar", "foo");
		final KeySearchBean searchBean = KeySearchBean.builder()
			.base(base)
			.build();
		final UsedKeysSearchResult result = UsedKeysSearchResult.builder()
			.used(used)
			.searchModel(searchBean)
			.build();
		final UnusedKeysSearchFilter searchFilter = new UnusedKeysSearchFilter();
		final UnusedKeysSearchResult process = searchFilter.process(result);
		assertEquals(process.getUnusedKeys().size(), 1);
	}

	/**
	 * Test method for {@link UnusedKeysSearchFilter}
	 */
	@Test
	public void testWithBeanTester()
	{
		final Configuration configuration = new ConfigurationBuilder().build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(UnusedKeysSearchFilter.class, configuration);
		beanTester.testBean(UnusedKeysSearchFilter.class);
	}
}
