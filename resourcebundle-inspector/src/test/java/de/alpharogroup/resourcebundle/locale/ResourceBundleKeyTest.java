package de.alpharogroup.resourcebundle.locale;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;

/**
 * The class {@link ResourceBundleKey}.
 */
public class ResourceBundleKeyTest
{

	/**
	 * Test method for {@link ResourceBundleKey#clone()}
	 */
	@Test
	public void testClone() {
		 Object[] params = {"param1", "param2"};
		final ResourceBundleKey expected = ResourceBundleKey.builder()
			.key("foo")
			.parameters(params)
			.defaultValue("bla bla").build();
		ResourceBundleKey actual = expected.clone();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleKey#equals(Object)} , {@link ResourceBundleKey#hashCode()} and
	 * {@link ResourceBundleKey#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToString()
	{
		boolean expected;
		boolean actual;

		final ResourceBundleKey first = ResourceBundleKey.builder().key("foo").build();

		final ResourceBundleKey second = ResourceBundleKey.builder().key("bar").build();


		final ResourceBundleKey third = new ResourceBundleKey();
		third.setKey("foo");
		final ResourceBundleKey fourth = new ResourceBundleKey("foo", null, null);


		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(first, second,
			third, fourth);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleKey}
	 */
	@Test
	public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("parameters", new ObjectArrayFactory())
			.build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(ResourceBundleKey.class, configuration);
		beanTester.testBean(ResourceBundleKey.class);
	}

}
