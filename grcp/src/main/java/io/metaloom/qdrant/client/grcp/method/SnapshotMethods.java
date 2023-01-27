package io.metaloom.qdrant.client.grcp.method;

import static io.metaloom.qdrant.client.grcp.GrcpUtil.snapshotsAsyncStub;
import static io.metaloom.qdrant.client.grcp.GrcpUtil.snapshotsStub;

import java.util.Objects;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateFullSnapshotRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateSnapshotRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateSnapshotResponse;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListFullSnapshotsRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListSnapshotsRequest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListSnapshotsResponse;

public interface SnapshotMethods extends ClientSettings {

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
	 * Get list of snapshots for a collection.
	 * 
	 * @param collectionName Name of the collection
	 * @return
	 */
	default GrpcClientRequest<ListSnapshotsResponse> listSnapshots(String collectionName) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		ListSnapshotsRequest request = ListSnapshotsRequest.newBuilder()
			.setCollectionName(collectionName)
			.build();

		return request(
			() -> snapshotsStub(this).list(request),
			() -> snapshotsAsyncStub(this).list(request));
	}

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
	 * Create new snapshot for a collection.
	 * 
	 * @param collectionName
	 *            Name of the collection for which to create a snapshot
	 */
	default GrpcClientRequest<CreateSnapshotResponse> createSnapshot(String collectionName) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		CreateSnapshotRequest request = CreateSnapshotRequest.newBuilder()
			.setCollectionName(collectionName)
			.build();

		return request(
			() -> snapshotsStub(this).create(request),
			() -> snapshotsAsyncStub(this).create(request));
	}
}
