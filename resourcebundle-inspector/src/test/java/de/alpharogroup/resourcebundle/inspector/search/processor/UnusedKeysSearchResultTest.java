package de.alpharogroup.resourcebundle.inspector.search.processor;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link UnusedKeysSearchResult}.
 */
public class UnusedKeysSearchResultTest
{

	/**
	 * Test method for {@link UnusedKeysSearchResult#equals(Object)} , {@link UnusedKeysSearchResult#hashCode()} and
	 * {@link UnusedKeysSearchResult#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToString()
	{
		boolean expected;
		boolean actual;

		final UnusedKeysSearchResult first = UnusedKeysSearchResult.builder().build();


		final UnusedKeysSearchResult second = new UnusedKeysSearchResult();


		final UnusedKeysSearchResult third = UnusedKeysSearchResult.builder().build();
		final UnusedKeysSearchResult fourth = UnusedKeysSearchResult.builder().build();


		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(first, second,
			third, fourth);
		expected = true;
		assertEquals(expected, actual);
	}



	/**
	 * Test method for {@link UnusedKeysSearchResult}
	 */
	@Test
	public void testWithBeanTester()
	{
		final Configuration configuration = new ConfigurationBuilder()
			.build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(UnusedKeysSearchResult.class, configuration);
		beanTester.testBean(UnusedKeysSearchResult.class);

	}
}