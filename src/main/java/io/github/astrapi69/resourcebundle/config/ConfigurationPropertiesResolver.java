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
package io.github.astrapi69.resourcebundle.config;

import java.io.Serializable;
import java.util.Optional;
import java.util.Properties;

import lombok.Getter;
import lombok.NonNull;
import io.github.astrapi69.collections.properties.PropertiesExtensions;
import io.github.astrapi69.resourcebundle.properties.PropertiesFileExtensions;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

/**
 * The class {@link ConfigurationPropertiesResolver} resolves the configuration properties for an
 * application like the http, https ports.
 */
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
	public ConfigurationPropertiesResolver(final @NonNull Integer defaultHttpPort,
		final @NonNull Integer defaultHttpsPort, final @NonNull String propertiesFilename)
	{
		this.defaultHttpPort = defaultHttpPort;
		this.defaultHttpsPort = defaultHttpsPort;
		this.propertiesFilename = propertiesFilename;
		this.properties = loadProperties();
		this.httpPort = resolveHttpPort();
		this.httpsPort = resolveHttpsPort();
	}

	/**
	 * Try to get the http port from the properties. If it does not exists an empty {@link Optional}
	 * will be returned.
	 *
	 * @return the optional http port
	 */
	private Optional<Integer> getOptionalHttpPort()
	{
		return PropertiesExtensions.getInteger(getProperties(), APPLICATION_HTTP_PORT_KEY);
	}

	/**
	 * Try to get the https port from the properties. If it does not exists an empty
	 * {@link Optional} will be returned.
	 *
	 * @return the optional https port
	 */
	private Optional<Integer> getOptionalHttpsPort()
	{
		return PropertiesExtensions.getInteger(getProperties(), APPLICATION_HTTPS_PORT_KEY);
	}

	/**
	 * Try to load the configuration properties file from disk.
	 *
	 * @return configuration properties
	 */
	protected Properties loadProperties()
	{
		return RuntimeExceptionDecorator
			.decorate(() -> PropertiesFileExtensions.loadProperties(getPropertiesFilename()));
	}

	/**
	 * Resolves the http port from the configuration properties file.
	 *
	 * @return the resolved http port or if not found the default http port.
	 */
	private int resolveHttpPort()
	{
		return getOptionalHttpPort().map(port -> port).orElse(getDefaultHttpPort());
	}

	/**
	 * Resolves the https port from the configuration properties file.
	 *
	 * @return the resolved https port or if not found the default https port.
	 */
	private int resolveHttpsPort()
	{
		return getOptionalHttpsPort().map(port -> port).orElse(getDefaultHttpsPort());
	}

}
