/**
 * The MIT License
 *
 * Copyright (C) 2012 Asterios Raptis
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
package de.alpharogroup.resourcebundle.locale;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.meanbean.factories.ObjectArrayFactory;
import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link ResourceBundleKey}.
 */
public class ResourceBundleKeyTest
{

	/**
	 * Test method for {@link ResourceBundleKey#clone()}
	 */
	@Test
	public void testClone()
	{
		final Object[] params = { "param1", "param2" };
		final ResourceBundleKey expected = ResourceBundleKey.builder().key("foo").parameters(params)
			.defaultValue("bla bla").build();
		final ResourceBundleKey actual = expected.clone();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ResourceBundleKey#equals(Object)} ,
	 * {@link ResourceBundleKey#hashCode()} and {@link ResourceBundleKey#toString()}
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
		final Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("parameters", new ObjectArrayFactory()).build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(ResourceBundleKey.class, configuration);
		beanTester.testBean(ResourceBundleKey.class);
	}

}
