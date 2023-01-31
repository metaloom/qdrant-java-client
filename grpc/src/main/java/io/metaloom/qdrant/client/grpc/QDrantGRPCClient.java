package io.metaloom.qdrant.client.grpc;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.impl.QDrantGRPCClientImpl;

public interface QDrantGRPCClient extends QDrantGRPCClientMethods, ClientSettings {

	static QDrantGRPCClientImpl.Builder builder() {
		return QDrantGRPCClientImpl.builder();
	}

	/**
	 * Close the prepared transport channel.
	 */
	void close();
}
