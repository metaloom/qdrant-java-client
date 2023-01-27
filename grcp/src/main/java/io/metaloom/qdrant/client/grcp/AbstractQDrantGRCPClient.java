package io.metaloom.qdrant.client.grcp;

import java.time.Duration;

public abstract class AbstractQDrantGRCPClient implements QDrantGRCPClient {

	protected final String hostname;
	protected final int port;

	protected final Duration connectTimeout;
	protected final Duration readTimeout;
	protected final Duration writeTimeout;

	/**
	 * @param hostname
	 * @param port
	 * @param connectTimeout
	 * @param readTimeout
	 * @param writeTimeout
	 */
	protected AbstractQDrantGRCPClient(String hostname, int port, Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
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
