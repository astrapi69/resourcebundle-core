/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.resourcebundle.locale;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Locale;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import io.github.astrapi69.evaluate.object.evaluator.EqualsHashCodeAndToStringEvaluator;
import io.github.astrapi69.meanbean.factory.LocaleFactory;

/**
 * The unit test class for the class {@link BundleKey}.
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

		final BundleKey first = BundleKey.builder().baseName("resources").locale(Locale.CANADA)
			.resourceBundleKey(ResourceBundleKey.builder().key("foo").build()).build();

		final BundleKey second = BundleKey.builder().baseName("messages").locale(Locale.ENGLISH)
			.resourceBundleKey(ResourceBundleKey.builder().key("bar").build()).build();

		final BundleKey third = new BundleKey();
		third.setBaseName("resources");
		third.setLocale(Locale.CANADA);
		third.setResourceBundleKey(new ResourceBundleKey(null, "foo", null));
		final BundleKey fourth = new BundleKey("resources", Locale.CANADA,
			ResourceBundleKey.builder().key("foo").build());

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
		final Configuration configuration = new ConfigurationBuilder()
			.ignoreProperty("resourceBundleKey").overrideFactory("locale", new LocaleFactory())
			.build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(BundleKey.class, configuration);
		beanTester.testBean(BundleKey.class);
	}

}
