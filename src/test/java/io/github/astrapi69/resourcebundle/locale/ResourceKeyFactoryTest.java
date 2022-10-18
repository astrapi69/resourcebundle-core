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

import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.github.astrapi69.test.object.Person;

/**
 * The unit test class {@link ResourceKeyFactoryTest} provides unit tests for the class
 * {@link ResourceKeyFactory}.
 */
public class ResourceKeyFactoryTest
{

	/**
	 * Test method for {@link ResourceKeyFactory#newResourceKey(Object, boolean, String)}
	 */
	@Test
	public void testNewResourceKeyTBooleanString()
	{
		final String resourceKey = ResourceKeyFactory.newResourceKey(new Person(), false, "foo");
		AssertJUnit.assertEquals("io.github.astrapi69.test.object.Person.foo", resourceKey);
	}

	/**
	 * Test method for {@link ResourceKeyFactory#newResourceKey(Object, String)}
	 */
	@Test
	public void testNewResourceKeyTString()
	{
		final String resourceKey = ResourceKeyFactory.newResourceKey(new Person(), "foo");
		AssertJUnit.assertEquals("Person.foo", resourceKey);
	}

	/**
	 * Test method for {@link ResourceKeyFactory}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ResourceKeyFactory.class);
	}

}
