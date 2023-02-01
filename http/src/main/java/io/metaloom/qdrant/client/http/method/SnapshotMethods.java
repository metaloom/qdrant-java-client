package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantBinaryResponse;
import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotCreateResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotListResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotRecoverRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotResponse;

public interface SnapshotMethods {

	/**
	 * Get list of snapshots for a collection
	 * 
	 * 
	 * @param collectionName
	 * @return
	 */
	QDrantClientRequest<SnapshotListResponse> listCollectionSnapshots(String collectionName);

	/**
	 * Recover local collection data from a snapshot. This will overwrite any data, stored on this node, for the collection.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> recoverSnapshot(String collectionName, SnapshotRecoverRequest request);

	/**
	 * Create new snapshot for a collection.
	 * 
	 * @param collectionName
	 * @return
	 */
	QDrantClientRequest<SnapshotResponse> createCollectionSnapshot(String collectionName);

	/**
	 * Download specified snapshot from a collection as a file.
	 * 
	 * @param collectionName
	 * @param snapshotName
	 * @return
	 */
	QDrantClientRequest<QDrantBinaryResponse> downloadCollectionSnapshot(String collectionName, String snapshotName);

	// Storage

	/**
	 * Get list of snapshots of the whole storage.
	 * 
	 * @return
	 */
	QDrantClientRequest<SnapshotListResponse> listStorageSnapshots();

	/**
	 * Create new snapshot of the whole storage.
	 * 
	 * @return
	 */
	QDrantClientRequest<SnapshotCreateResponse> createStorageSnapshot();

	/**
	 * Download specified snapshot of the whole storage as a file.
	 * 
	 * @param snapshotName
	 * @return
	 */
	QDrantClientRequest<QDrantBinaryResponse> downloadStorageSnapshot(String snapshotName);
}
