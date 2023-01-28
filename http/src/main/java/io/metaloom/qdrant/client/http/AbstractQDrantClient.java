package io.metaloom.qdrant.client.http;

import java.time.Duration;

public abstract class AbstractQDrantClient implements QDrantHttpClient {

	protected final String scheme;
	protected final String hostname;
	protected final int port;

	protected final Duration connectTimeout;
	protected final Duration readTimeout;
	protected final Duration writeTimeout;

	/**
	 * @param scheme
	 * @param hostname
	 * @param port
	 * @param connectTimeout
	 * @param readTimeout
	 * @param writeTimeout
	 */
	protected AbstractQDrantClient(String scheme, String hostname, int port, Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
		this.scheme = scheme;
		this.hostname = hostname;
		this.port = port;

		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.writeTimeout = writeTimeout;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getScheme() {
		return scheme;
	}

	@Override
	public String getHostname() {
		return hostname;
	}

	@Override
	public Duration getConnectTimeout() {
		return connectTimeout;
	}

	@Override
	public Duration getReadTimeout() {
		return readTimeout;
	}

	@Override
	public Duration getWriteTimeout() {
		return writeTimeout;
	}
	

}
