package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantBinaryResponse;
import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotCreateResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotListResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotRecoverRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotResponse;

public interface SnapshotMethods {

	QDrantClientRequest<SnapshotListResponse> listCollectionSnapshots(String collectionName);

	QDrantClientRequest<GenericBooleanStatusResponse> recoverSnapshot(String collectionName, SnapshotRecoverRequest request);

	QDrantClientRequest<SnapshotResponse> createCollectionSnapshot(String collectionName);

	QDrantClientRequest<QDrantBinaryResponse> downloadCollectionSnapshot(String collectionName, String snapshotName);

	// Storage
	QDrantClientRequest<SnapshotListResponse> listStorageSnapshots();

	QDrantClientRequest<SnapshotCreateResponse> createStorageSnapshot();

	QDrantClientRequest<QDrantBinaryResponse> downloadStorageSnapshot(String snapshotName);
}
