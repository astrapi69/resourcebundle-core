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
		final KeySearchBean searchBean = KeySearchBean.builder().base(base).build();
		final UsedKeysSearchResult result = UsedKeysSearchResult.builder().used(used)
			.searchModel(searchBean).build();
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
