package io.metaloom.qdrant.client.http.model.request.snapshot;

import io.metaloom.qdrant.client.http.model.snapshot.SnapshotPriority;

public class SnapshotRecoverRequest {
	String location;
	SnapshotPriority priority;
}
