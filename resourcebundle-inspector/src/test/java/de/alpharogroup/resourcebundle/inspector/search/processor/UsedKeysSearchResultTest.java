package de.alpharogroup.resourcebundle.inspector.search.processor;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.meanbean.factories.FileFactory;
import de.alpharogroup.meanbean.factories.LocaleFactory;
import de.alpharogroup.meanbean.factories.StringArrayFactory;
import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link UsedKeysSearchResult}.
 */
public class UsedKeysSearchResultTest
{

	/**
	 * Test method for {@link UsedKeysSearchResult#equals(Object)} ,
	 * {@link UsedKeysSearchResult#hashCode()} and {@link UsedKeysSearchResult#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToString()
	{
		boolean expected;
		boolean actual;

		final UsedKeysSearchResult first = UsedKeysSearchResult.builder().build();


		final UsedKeysSearchResult second = new UsedKeysSearchResult();


		final UsedKeysSearchResult third = UsedKeysSearchResult.builder().build();
		final UsedKeysSearchResult fourth = UsedKeysSearchResult.builder().build();


		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(first, second,
			third, fourth);
		expected = true;
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link UsedKeysSearchResult}
	 */
	@Test
	public void testWithBeanTester()
	{
		final Configuration configuration = new ConfigurationBuilder().ignoreProperty("searchModel")
			.build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(UsedKeysSearchResult.class, configuration);
		beanTester.testBean(UsedKeysSearchResult.class);
	}
}
