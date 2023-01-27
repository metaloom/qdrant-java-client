package io.metaloom.qdrant.client.grcp;

import io.metaloom.qdrant.client.grcp.method.CollectionMethods;
import io.metaloom.qdrant.client.grcp.method.PointMethods;
import io.metaloom.qdrant.client.grcp.method.SearchMethods;
import io.metaloom.qdrant.client.grcp.method.SnapshotMethods;

/**
 * Aggregation interface for various organized client methods.
 */
public interface QDrantGRCPClientMethods extends CollectionMethods, PointMethods, SnapshotMethods, SearchMethods {

}
