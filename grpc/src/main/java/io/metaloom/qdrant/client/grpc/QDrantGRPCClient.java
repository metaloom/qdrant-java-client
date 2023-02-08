package io.metaloom.qdrant.client.grpc;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.impl.QDrantGRPCClientImpl;
import io.metaloom.qdrant.client.grpc.method.CollectionMethods;
import io.metaloom.qdrant.client.grpc.method.PointMethods;
import io.metaloom.qdrant.client.grpc.method.SearchMethods;
import io.metaloom.qdrant.client.grpc.method.SnapshotMethods;

public interface QDrantGRPCClient extends CollectionMethods, PointMethods, SnapshotMethods, SearchMethods, ClientSettings, AutoCloseable {

	static QDrantGRPCClientImpl.Builder builder() {
		return QDrantGRPCClientImpl.builder();
	}

	/**
	 * Close the prepared transport channel.
	 */
	void close();

}
