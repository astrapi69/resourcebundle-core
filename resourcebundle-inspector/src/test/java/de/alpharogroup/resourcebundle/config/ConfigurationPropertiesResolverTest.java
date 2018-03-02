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
package de.alpharogroup.resourcebundle.config;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link ConfigurationPropertiesResolver}.
 */
public class ConfigurationPropertiesResolverTest {

	/**
	 * Test the no argument constructor of
	 * {@link ConfigurationPropertiesResolver}.
	 */
	@Test
	public void testConfigurationPropertiesResolver() {
		final ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver();
		assertEquals(configurationPropertiesResolver.getDefaultHttpPort(),
				ConfigurationPropertiesResolver.DEFAULT_HTTP_PORT);
		assertEquals(configurationPropertiesResolver.getDefaultHttpsPort(),
				ConfigurationPropertiesResolver.DEFAULT_HTTPS_PORT);
		assertEquals(configurationPropertiesResolver.getPropertiesFilename(),
				ConfigurationPropertiesResolver.DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
		assertEquals(configurationPropertiesResolver.getHttpPort(), 18080);
		assertEquals(configurationPropertiesResolver.getHttpsPort(), 18443);
	}

	/**
	 * Test the constructor with the http, https port and the file name from the
	 * configuration file from of {@link ConfigurationPropertiesResolver}.
	 */
	@Test
	public void testConfigurationPropertiesResolverIntegerIntegerString() {
		final ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver(
				9090, 9443, "conf.properties");
		assertEquals(configurationPropertiesResolver.getDefaultHttpPort(), 9090);
		assertEquals(configurationPropertiesResolver.getDefaultHttpsPort(), 9443);
		assertEquals(configurationPropertiesResolver.getPropertiesFilename(), "conf.properties");
	}

	/**
	 * Test the constructor with the http, https port and the file name from the
	 * configuration file from of {@link ConfigurationPropertiesResolver}.
	 */
	@Test
	public void testConfigurationPropertiesResolverIntegerIntegerStringNumberFormatException() {
		final ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver(
				9090, 9443, "config-ex.properties");
		assertEquals(configurationPropertiesResolver.getDefaultHttpPort(), 9090);
		assertEquals(configurationPropertiesResolver.getDefaultHttpsPort(), 9443);
		assertEquals(configurationPropertiesResolver.getPropertiesFilename(), "config-ex.properties");
	}

	/**
	 * Test for method {@link ConfigurationPropertiesResolver#loadProperties()}.
	 */
	@Test
	public void testLoadProperties() {
		new ConfigurationPropertiesResolver(9090, 9443, "not-exists.properties");
	}

	/**
	 * Test method for {@link ConfigurationPropertiesResolver}
	 */
	@Test
	public void testWithBeanTester() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ConfigurationPropertiesResolver.class);
	}

}
