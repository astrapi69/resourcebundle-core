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

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.Properties;

import de.alpharogroup.check.Check;
import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.resourcebundle.properties.PropertiesFileExtensions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link ConfigurationPropertiesResolver} resolves the configuration properties for an
 * application like the http, https ports.
 */
@Slf4j
public class ConfigurationPropertiesResolver implements Serializable
{

	/** The constant for the properties key of the http port. */
	public static final String APPLICATION_HTTP_PORT_KEY = "application.http.port";

	/** The constant for the properties key of the https port. */
	public static final String APPLICATION_HTTPS_PORT_KEY = "application.https.port";

	/**
	 * The constant for the default file name of the configuration properties file.
	 */
	public static final String DEFAULT_CONFIGURATION_PROPERTIES_FILENAME = "config.properties";

	/** The constant for the default http port. */
	public static final int DEFAULT_HTTP_PORT = 8080;

	/** The constant for the default http port. */
	public static final int DEFAULT_HTTPS_PORT = 8443;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The default http port.
	 */
	@Getter
	private final int defaultHttpPort;

	/**
	 * The default https port.
	 */
	@Getter
	private final int defaultHttpsPort;

	/**
	 * The http port.
	 */
	@Getter
	private final int httpPort;

	/**
	 * The https port.
	 */
	@Getter
	private final int httpsPort;

	/** The configuration properties. */
	@Getter
	private final Properties properties;

	/**
	 * The properties filename.
	 */
	@Getter
	private final String propertiesFilename;

	/**
	 * Instantiates a new {@link ConfigurationPropertiesResolver} with the default settings.
	 */
	public ConfigurationPropertiesResolver()
	{
		this(DEFAULT_HTTP_PORT, DEFAULT_HTTPS_PORT, DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
	}

	/**
	 * Instantiates a new {@link ConfigurationPropertiesResolver}.
	 *
	 * @param defaultHttpPort
	 *            the default http port
	 * @param defaultHttpsPort
	 *            the default https port
	 * @param propertiesFilename
	 *            the properties filename
	 */
	public ConfigurationPropertiesResolver(final Integer defaultHttpPort,
		final Integer defaultHttpsPort, final String propertiesFilename)
	{
		Check.get().notNull(defaultHttpPort, "defaultHttpPort")
			.notNull(defaultHttpsPort, "defaultHttpsPort")
			.notNull(propertiesFilename, "propertiesFilename");
		this.defaultHttpPort = defaultHttpPort;
		this.defaultHttpsPort = defaultHttpsPort;
		this.propertiesFilename = propertiesFilename;
		this.properties = loadProperties();
		this.httpPort = resolveHttpPort();
		this.httpsPort = resolveHttpsPort();
	}

	/**
	 * Try to get a number from the given properties key from the given properties. If it does not
	 * exists an empty {@link Optional} will be returned and a log message will be logged.
	 *
	 * @param properties
	 *            the properties
	 * @param propertiesKey
	 *            the properties key
	 * @return the port number or an empty {@linkplain Optional}
	 * @deprecated use instead the corresponding method in the {@link PropertiesExtensions} from the
	 *             next release.
	 */
	@Deprecated
	private Optional<Integer> getInteger(final Properties properties, final String propertiesKey)
	{
		if (properties != null && properties.containsKey(propertiesKey))
		{
			final String portAsString = properties.getProperty(propertiesKey);
			try
			{
				final Integer port = Integer.valueOf(portAsString);
				return Optional.of(port);
			}
			catch (final NumberFormatException e)
			{
				log.error("Value of given properties key:" + propertiesKey + " is not a number.",
					e);
				return Optional.empty();
			}
		}
		return Optional.empty();
	}

	/**
	 * Try to get the http port from the properties. If it does not exists an empty {@link Optional}
	 * will be returned.
	 *
	 * @return the optional http port
	 */
	private Optional<Integer> getOptionalHttpPort()
	{
		return getInteger(getProperties(), APPLICATION_HTTP_PORT_KEY);
	}

	/**
	 * Try to get the https port from the properties. If it does not exists an empty
	 * {@link Optional} will be returned.
	 *
	 * @return the optional https port
	 */
	private Optional<Integer> getOptionalHttpsPort()
	{
		return getInteger(getProperties(), APPLICATION_HTTPS_PORT_KEY);
	}

	/**
	 * Try to load the configuration properties file from disk.
	 *
	 * @return configuration properties
	 */
	protected Properties loadProperties()
	{
		final Properties properties;
		try
		{
			properties = PropertiesFileExtensions.loadProperties(getPropertiesFilename());
		}
		catch (final IOException e)
		{
			throw new RuntimeException(e);
		}
		return properties;
	}

	/**
	 * Resolves the http port from the configuration properties file.
	 *
	 * @return the resolved http port or if not found the default http port.
	 */
	private int resolveHttpPort()
	{
		final Optional<Integer> optionalHttpPort = getOptionalHttpPort();
		if (optionalHttpPort.isPresent())
		{
			return optionalHttpPort.get();
		}
		return getDefaultHttpPort();
	}

	/**
	 * Resolves the https port from the configuration properties file.
	 *
	 * @return the resolved https port or if not found the default https port.
	 */
	private int resolveHttpsPort()
	{
		final Optional<Integer> optionalHttpsPort = getOptionalHttpsPort();
		if (optionalHttpsPort.isPresent())
		{
			return optionalHttpsPort.get();
		}
		return getDefaultHttpsPort();
	}

}