package io.metaloom.qdrant.client.http;

import java.time.Duration;

import io.metaloom.qdrant.client.http.impl.QDrantHttpClientImpl;
import io.metaloom.qdrant.client.http.method.CollectionMethods;
import io.metaloom.qdrant.client.http.method.ServiceMethods;
import io.metaloom.qdrant.client.http.method.SnapshotMethods;

public interface QDrantHttpClient extends CollectionMethods, SnapshotMethods, ServiceMethods {

	static QDrantHttpClientImpl.Builder builder() {
		return QDrantHttpClientImpl.builder();
	}

	/**
	 * Return the configured protocol scheme.
	 * 
	 * @return
	 */
	String getScheme();

	/**
	 * Return the configured server hostname.
	 * 
	 * @return
	 */
	String getHostname();

	/**
	 * Return the configured server port.
	 * 
	 * @return
	 */
	int getPort();

	/**
	 * Close the client and release all resources.
	 */
	void close();

	/**
	 * Return the configured connect timeout.
	 * 
	 * @return
	 */
	Duration getConnectTimeout();

	/**
	 * Return the configured read timeout.
	 * 
	 * @return
	 */
	Duration getReadTimeout();

	/**
	 * Return the configured write timeout.
	 * 
	 * @return
	 */
	Duration getWriteTimeout();

}