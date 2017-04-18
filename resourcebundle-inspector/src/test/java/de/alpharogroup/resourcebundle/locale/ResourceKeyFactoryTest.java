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

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

/**
 * The class {@link ResourceKeyFactoryTest} provides unit tests for the class
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
		AssertJUnit.assertEquals("de.alpharogroup.test.objects.Person.foo", resourceKey);
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

}
