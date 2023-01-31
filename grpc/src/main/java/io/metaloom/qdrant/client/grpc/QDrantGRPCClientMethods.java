package io.metaloom.qdrant.client.grpc;

import io.metaloom.qdrant.client.grpc.method.CollectionMethods;
import io.metaloom.qdrant.client.grpc.method.PointMethods;
import io.metaloom.qdrant.client.grpc.method.SearchMethods;
import io.metaloom.qdrant.client.grpc.method.SnapshotMethods;

/**
 * Aggregation interface for various organized client methods.
 */
public interface QDrantGRPCClientMethods extends CollectionMethods, PointMethods, SnapshotMethods, SearchMethods {

}
