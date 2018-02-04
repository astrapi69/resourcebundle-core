package de.alpharogroup.resourcebundle.locale;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Locale;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.meanbean.factories.LocaleFactory;
import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;

/**
 * The class {@link BundleKey}.
 */
public class BundleKeyTest
{

	/**
	 * Test method for {@link BundleKey#equals(Object)} , {@link BundleKey#hashCode()} and
	 * {@link BundleKey#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToString()
	{
		boolean expected;
		boolean actual;


		final BundleKey first = BundleKey.builder()
			.baseName("resources")
			.locale(Locale.CANADA)
			.resourceBundleKey(ResourceBundleKey.builder().key("foo").build())
			.build();


		final BundleKey second = BundleKey.builder()
			.baseName("messages")
			.locale(Locale.ENGLISH)
			.resourceBundleKey(ResourceBundleKey.builder().key("bar").build())
			.build();


		final BundleKey third = new BundleKey();
		third.setBaseName("resources");
		third.setLocale(Locale.CANADA);
		third.setResourceBundleKey(new ResourceBundleKey("foo", null, null));
		final BundleKey fourth = new BundleKey("resources", Locale.CANADA, ResourceBundleKey.builder().key("foo").build());


		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(first, second,
			third, fourth);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link BundleKey}
	 */
	@Test
	public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.ignoreProperty("resourceBundleKey")
			.overrideFactory("locale", new LocaleFactory())
			.build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(BundleKey.class, configuration);
		beanTester.testBean(BundleKey.class);
	}

}
