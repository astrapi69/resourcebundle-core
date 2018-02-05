package de.alpharogroup.resourcebundle.config;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link ConfigurationPropertiesResolver}.
 */
public class ConfigurationPropertiesResolverTest
{

	/**
	 * Test the no argument constructor of {@link ConfigurationPropertiesResolver}.
	 */
	@Test
	public void testConfigurationPropertiesResolver()
	{
		ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver();
		assertEquals(configurationPropertiesResolver.getDefaultHttpPort(),
			ConfigurationPropertiesResolver.DEFAULT_HTTP_PORT);
		assertEquals(configurationPropertiesResolver.getDefaultHttpsPort(),
			ConfigurationPropertiesResolver.DEFAULT_HTTPS_PORT);
		assertEquals(configurationPropertiesResolver.getPropertiesFilename(),
			ConfigurationPropertiesResolver.DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
		assertEquals(configurationPropertiesResolver.getHttpPort(),
			18080);
		assertEquals(configurationPropertiesResolver.getHttpsPort(),
			18443);
	}

	/**
	 * Test the constructor with the http, https port and the file name from the configuration file
	 * from of {@link ConfigurationPropertiesResolver}.
	 */
	@Test
	public void testConfigurationPropertiesResolverIntegerIntegerString()
	{
		ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver(9090, 9443, "conf.properties");
		assertEquals(configurationPropertiesResolver.getDefaultHttpPort(),
			9090);
		assertEquals(configurationPropertiesResolver.getDefaultHttpsPort(),
			9443);
		assertEquals(configurationPropertiesResolver.getPropertiesFilename(),
			"conf.properties");
	}

	/**
	 * Test the constructor with the http, https port and the file name from the configuration file
	 * from of {@link ConfigurationPropertiesResolver}.
	 */
	@Test
	public void testConfigurationPropertiesResolverIntegerIntegerStringNumberFormatException()
	{
		ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver(9090, 9443, "config-ex.properties");
		assertEquals(configurationPropertiesResolver.getDefaultHttpPort(),
			9090);
		assertEquals(configurationPropertiesResolver.getDefaultHttpsPort(),
			9443);
		assertEquals(configurationPropertiesResolver.getPropertiesFilename(),
			"config-ex.properties");
	}

	/**
	 * Test for method {@link ConfigurationPropertiesResolver#loadProperties()}.
	 */
	@Test
	public void testLoadProperties()
	{
		new ConfigurationPropertiesResolver(9090, 9443, "not-exists.properties");
	}

	/**
	 * Test method for {@link ConfigurationPropertiesResolver}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ConfigurationPropertiesResolver.class);
	}

}
