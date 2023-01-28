package io.metaloom.qdrant.client.http.model.snapshot;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class SnapshotRecoverRequest implements RestRequestModel {

	private String location;

	private SnapshotPriority priority;

	public String getLocation() {
		return location;
	}

	public SnapshotPriority getPriority() {
		return priority;
	}
}
