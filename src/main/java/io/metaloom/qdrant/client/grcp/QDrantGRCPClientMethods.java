package io.metaloom.qdrant.client.grcp;

import io.metaloom.qdrant.client.grcp.methods.CollectionMethods;
import io.metaloom.qdrant.client.grcp.methods.PointMethods;
import io.metaloom.qdrant.client.grcp.methods.SearchMethods;
import io.metaloom.qdrant.client.grcp.methods.SnapshotMethods;

/**
 * Aggregation interface for various organized client methods.
 */
public interface QDrantGRCPClientMethods extends CollectionMethods, PointMethods, SnapshotMethods, SearchMethods {

}
