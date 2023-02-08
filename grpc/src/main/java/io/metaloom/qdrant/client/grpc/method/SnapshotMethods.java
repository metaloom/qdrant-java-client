package io.metaloom.qdrant.client.grpc.method;

import static io.metaloom.qdrant.client.grpc.InternalGrpcUtil.assertCollectionName;
import static io.metaloom.qdrant.client.grpc.InternalGrpcUtil.snapshotsAsyncStub;
import static io.metaloom.qdrant.client.grpc.InternalGrpcUtil.snapshotsStub;

import java.util.Objects;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateFullSnapshotRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateSnapshotRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateSnapshotResponse;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.DeleteFullSnapshotRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.DeleteSnapshotRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.DeleteSnapshotResponse;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListFullSnapshotsRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListSnapshotsRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListSnapshotsResponse;

public interface SnapshotMethods extends ClientSettings {

	/**
	 * Create new snapshot of the whole storage.
	 */
	default GrpcClientRequest<CreateSnapshotResponse> createSnapshot() {

		CreateFullSnapshotRequest request = CreateFullSnapshotRequest.newBuilder()
			.build();

		return request(
			() -> snapshotsStub(this).createFull(request),
			() -> snapshotsAsyncStub(this).createFull(request));
	}

	/**
	 * Get list of snapshots of the whole storage
	 * 
	 * @return
	 */
	default GrpcClientRequest<ListSnapshotsResponse> listSnapshots() {
		ListFullSnapshotsRequest request = ListFullSnapshotsRequest.newBuilder()
			.build();

		return request(
			() -> snapshotsStub(this).listFull(request),
			() -> snapshotsAsyncStub(this).listFull(request));
	}

	/**
	 * Delete a storage snapshot.
	 * 
	 * @param snapshotName
	 * @return
	 */
	default GrpcClientRequest<DeleteSnapshotResponse> deleteSnapshot(String snapshotName) {
		Objects.requireNonNull(snapshotName, "A valid snapshot name must be provided");

		DeleteFullSnapshotRequest request = DeleteFullSnapshotRequest.newBuilder()
			.setSnapshotName(snapshotName)
			.build();

		return request(
			() -> snapshotsStub(this).deleteFull(request),
			() -> snapshotsAsyncStub(this).deleteFull(request));
	}

	/**
	 * Create new snapshot for a collection.
	 * 
	 * @param collectionName
	 *            Name of the collection for which to create a snapshot
	 */
	default GrpcClientRequest<CreateSnapshotResponse> createSnapshot(String collectionName) {
		assertCollectionName(collectionName);

		CreateSnapshotRequest request = CreateSnapshotRequest.newBuilder()
			.setCollectionName(collectionName)
			.build();

		return request(
			() -> snapshotsStub(this).create(request),
			() -> snapshotsAsyncStub(this).create(request));
	}

	/**
	 * Get list of snapshots for a collection.
	 * 
	 * @param collectionName
	 *            Name of the collection
	 * @return
	 */
	default GrpcClientRequest<ListSnapshotsResponse> listSnapshots(String collectionName) {
		assertCollectionName(collectionName);

		ListSnapshotsRequest request = ListSnapshotsRequest.newBuilder()
			.setCollectionName(collectionName)
			.build();

		return request(
			() -> snapshotsStub(this).list(request),
			() -> snapshotsAsyncStub(this).list(request));
	}

	/**
	 * Delete a collection snapshot.
	 * 
	 * @param collectionName
	 * @param snapshotName
	 * @return
	 */
	default GrpcClientRequest<DeleteSnapshotResponse> deleteCollectionSnapshot(String collectionName, String snapshotName) {
		assertCollectionName(collectionName);
		Objects.requireNonNull(snapshotName, "A valid snapshot name must be provided");

		DeleteSnapshotRequest request = DeleteSnapshotRequest.newBuilder()
			.setCollectionName(collectionName)
			.setSnapshotName(snapshotName)
			.build();

		return request(
			() -> snapshotsStub(this).delete(request),
			() -> snapshotsAsyncStub(this).delete(request));
	}
}
