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
package io.github.astrapi69.resourcebundle.inspector.search.processor;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Optional;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.evaluate.object.api.ContractViolation;
import io.github.astrapi69.evaluate.object.checker.EqualsHashCodeAndToStringCheck;

/**
 * The unit test class for the class {@link UnusedKeysSearchResult}.
 */
public class UnusedKeysSearchResultTest
{

	/**
	 * Test method for {@link UnusedKeysSearchResult#equals(Object)} ,
	 * {@link UnusedKeysSearchResult#hashCode()} and {@link UnusedKeysSearchResult#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToString()
	{
		Optional<ContractViolation> expected;
		Optional<ContractViolation> actual;

		final UnusedKeysSearchResult first = UnusedKeysSearchResult.builder().build();

		final UnusedKeysSearchResult second = new UnusedKeysSearchResult();
		second.setUnusedKeys(SetFactory.newHashSet("foo"));

		final UnusedKeysSearchResult third = UnusedKeysSearchResult.builder().build();
		final UnusedKeysSearchResult fourth = UnusedKeysSearchResult.builder().build();

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(first, second, third,
			fourth);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link UnusedKeysSearchResult}
	 */
	@Test
	public void testWithBeanTester()
	{
		final Configuration configuration = new ConfigurationBuilder().build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(UnusedKeysSearchResult.class, configuration);
		beanTester.testBean(UnusedKeysSearchResult.class);

	}
}
