package io.metaloom.qdrant.client.grpc.impl;

import java.time.Duration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.metaloom.qdrant.client.grpc.AbstractQDrantGRPCClient;
import io.metaloom.qdrant.client.grpc.QDrantGRPCClient;

/**
 * Implementation of the {@link QDrantGRPCClient}. 
 */
public class QDrantGRPCClientImpl extends AbstractQDrantGRPCClient {

	public static final Logger log = LoggerFactory.getLogger(QDrantGRPCClientImpl.class);

	private ManagedChannel channel;

	/**
	 * Create a new client with a default timeout of 10s for all timeouts (connect, read and write).
	 * 
	 * @param hostname
	 * @param port
	 * @param connectTimeout
	 * @param readTimeout
	 * @param writeTimeout
	 */
	protected QDrantGRPCClientImpl(String hostname, int port, Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
		super(hostname, port, connectTimeout, readTimeout, writeTimeout);
	}

	public static Builder builder() {
		return new Builder();
	}

	public QDrantGRPCClientImpl init() {
		channel = ManagedChannelBuilder.forAddress(hostname, port)
			.usePlaintext()
			.build();
		return this;
	}

	@Override
	public void close() {
		if (channel != null) {
			channel.shutdown();
		} else {
			log.warn("gRPC channel has not been initialized.");
		}
	}

	@Override
	public ManagedChannel channel() {
		return channel;
	}

	public static class Builder {

		private String hostname = "localhost";
		private int port = 6334;

		private Duration connectTimeout = Duration.ofMillis(10_000);
		private Duration readTimeout = Duration.ofMillis(10_000);
		private Duration writeTimeout = Duration.ofMillis(10_000);

		/**
		 * Verify the builder and build the client.
		 * 
		 * @return
		 */
		public QDrantGRPCClientImpl build() {
			Objects.requireNonNull(hostname, "A hostname has to be specified.");

			QDrantGRPCClientImpl client = new QDrantGRPCClientImpl(hostname, port,
				connectTimeout, readTimeout, writeTimeout);
			client.init();
			return client;
		}

		/**
		 * Set the hostname for the client.
		 * 
		 * @param hostname
		 * @return Fluent API
		 */
		public Builder setHostname(String hostname) {
			this.hostname = hostname;
			return this;
		}

		/**
		 * Set the port to connect to. (e.g. 6334).
		 * 
		 * @param port
		 * @return Fluent API
		 */
		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		/**
		 * Set connection timeout.
		 * 
		 * @param connectTimeout
		 * @return Fluent API
		 */
		public Builder setConnectTimeout(Duration connectTimeout) {
			this.connectTimeout = connectTimeout;
			return this;
		}

		/**
		 * Set read timeout for the client.
		 * 
		 * @param readTimeout
		 * @return Fluent API
		 */
		public Builder setReadTimeout(Duration readTimeout) {
			this.readTimeout = readTimeout;
			return this;
		}

		/**
		 * Set write timeout for the client.
		 * 
		 * @param writeTimeout
		 * @return Fluent API
		 */
		public Builder setWriteTimeout(Duration writeTimeout) {
			this.writeTimeout = writeTimeout;
			return this;
		}

	}
}
