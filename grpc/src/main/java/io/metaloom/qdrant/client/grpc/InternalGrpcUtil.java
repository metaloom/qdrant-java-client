package io.metaloom.qdrant.client.grpc;

import java.util.Objects;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.proto.CollectionsGrpc;
import io.metaloom.qdrant.client.grpc.proto.CollectionsInternalGrpc;
import io.metaloom.qdrant.client.grpc.proto.PointsGrpc;
import io.metaloom.qdrant.client.grpc.proto.PointsInternalGrpc;
import io.metaloom.qdrant.client.grpc.proto.QdrantGrpc;
import io.metaloom.qdrant.client.grpc.proto.RaftGrpc;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsGrpc;

public final class InternalGrpcUtil {

	private InternalGrpcUtil() {
	}

	public static CollectionsGrpc.CollectionsBlockingStub collectionsStub(ClientSettings client) {
		return CollectionsGrpc.newBlockingStub(client.channel());
	}

	public static CollectionsGrpc.CollectionsFutureStub collectionsAsyncStub(ClientSettings client) {
		return CollectionsGrpc.newFutureStub(client.channel());
	}

	public static CollectionsInternalGrpc.CollectionsInternalBlockingStub collectionsInternalStub(ClientSettings client) {
		return CollectionsInternalGrpc.newBlockingStub(client.channel());
	}

	public static CollectionsInternalGrpc.CollectionsInternalFutureStub collectionsInternalAsyncStub(ClientSettings client) {
		return CollectionsInternalGrpc.newFutureStub(client.channel());
	}

	public static PointsGrpc.PointsBlockingStub pointsStub(ClientSettings client) {
		return PointsGrpc.newBlockingStub(client.channel());
	}

	public static PointsGrpc.PointsFutureStub pointsAsyncStub(ClientSettings client) {
		return PointsGrpc.newFutureStub(client.channel());
	}

	public static PointsInternalGrpc.PointsInternalBlockingStub pointsInternalStub(ClientSettings client) {
		return PointsInternalGrpc.newBlockingStub(client.channel());
	}

	public static PointsInternalGrpc.PointsInternalFutureStub pointsInternalAsyncStub(ClientSettings client) {
		return PointsInternalGrpc.newFutureStub(client.channel());
	}

	public static SnapshotsGrpc.SnapshotsBlockingStub snapshotsStub(ClientSettings client) {
		return SnapshotsGrpc.newBlockingStub(client.channel());
	}

	public static SnapshotsGrpc.SnapshotsFutureStub snapshotsAsyncStub(ClientSettings client) {
		return SnapshotsGrpc.newFutureStub(client.channel());
	}

	public static RaftGrpc.RaftBlockingStub raftStub(ClientSettings client) {
		return RaftGrpc.newBlockingStub(client.channel());
	}

	public static RaftGrpc.RaftFutureStub raftAsyncStub(ClientSettings client) {
		return RaftGrpc.newFutureStub(client.channel());
	}

	public static QdrantGrpc.QdrantBlockingStub qdrantStub(ClientSettings client) {
		return QdrantGrpc.newBlockingStub(client.channel());
	}

	public static QdrantGrpc.QdrantFutureStub qdrantAsyncStub(ClientSettings client) {
		return QdrantGrpc.newFutureStub(client.channel());
	}

	public static void assertCollectionName(String collectionName) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
	}

}