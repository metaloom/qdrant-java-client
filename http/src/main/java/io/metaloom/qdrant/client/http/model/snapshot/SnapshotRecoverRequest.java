package io.metaloom.qdrant.client.http.model.snapshot;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class SnapshotRecoverRequest implements RestRequestModel {

	private String location;

	private SnapshotPriority priority;

	public String getLocation() {
		return location;
	}

	public SnapshotRecoverRequest setLocation(String location) {
		this.location = location;
		return this;
	}

	public SnapshotPriority getPriority() {
		return priority;
	}

	public SnapshotRecoverRequest setPriority(SnapshotPriority priority) {
		this.priority = priority;
		return this;
	}
}
