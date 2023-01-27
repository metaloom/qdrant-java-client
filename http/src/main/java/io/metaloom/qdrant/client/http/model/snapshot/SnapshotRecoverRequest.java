package io.metaloom.qdrant.client.http.model.snapshot;

import io.metaloom.qdrant.client.http.model.RestRequest;

public class SnapshotRecoverRequest implements RestRequest {

	private String location;

	private SnapshotPriority priority;

	public String getLocation() {
		return location;
	}

	public SnapshotPriority getPriority() {
		return priority;
	}
}
