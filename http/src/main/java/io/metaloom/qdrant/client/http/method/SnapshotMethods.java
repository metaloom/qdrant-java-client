package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotCreateResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotListResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotRecoverRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotResponse;

public interface SnapshotMethods extends HTTPMethods {

	// Collection
	default RequestBuilder<SnapshotListResponse> listCollectionSnapshots(String collectionName) {
		return getBuilder("collections/" + collectionName + "/snapshots");
	}

	default RequestBuilder<GenericBooleanStatusResponse> recoverSnapshot(String collectionName, SnapshotRecoverRequest request) {
		return putBuilder("collections/" + collectionName + "snapshots/recover", request);
	}

	default RequestBuilder<SnapshotResponse> createCollectionSnapshot(String collectionName) {
		return postBuilder("collections/" + collectionName + "/snapshots");
	}

	default RequestBuilder<?> downloadCollectionSnapshot(String collectionName, String snapshotName) {
		return getBuilder("collections/" + collectionName + "/snapshots/" + snapshotName);
	}

	// Storage
	default RequestBuilder<SnapshotListResponse> listStorageSnapshots() {
		return getBuilder("snapshots");
	}

	default RequestBuilder<SnapshotCreateResponse> createStorageSnapshot() {
		return postBuilder("snapshots");
	}

	default RequestBuilder<?> downloadStorageSnapshot(String snapshotName) {
		return getBuilder("snapshots/" + snapshotName);
	}
}
