package de.alpharogroup.resourcebundle.inspector.search.processor;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link KeySearchBean}.
 */
public class KeySearchBeanTest
{

	/**
	 * Test method for {@link KeyValuesPair#equals(Object)} , {@link KeyValuesPair#hashCode()} and
	 * {@link KeyValuesPair#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToString()
	{
		boolean expected;
		boolean actual;

		final KeySearchBean first = KeySearchBean.builder().build();


		final KeySearchBean second = new KeySearchBean();


		final KeySearchBean third = KeySearchBean.builder().build();
		final KeySearchBean fourth = KeySearchBean.builder().build();


		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(first, second,
			third, fourth);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link KeySearchBean}
	 */
	@Test
	public void testWithBeanTester()
	{
		 Configuration configuration = new ConfigurationBuilder()
			 .overrideFactory("fileExtensions", new StringArrayFactory())
			 .overrideFactory("searchDir", new FileFactory())
			 .overrideFactory("locale", new LocaleFactory())
			 .build();
		    new BeanTester().testBean(KeySearchBean.class, configuration);

	}

}
