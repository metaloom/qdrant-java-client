package io.metaloom.qdrant.client.http.model.snapshot;

public enum SnapshotPriority {

	SNAPSHOT("snapshot"), REPLICA("replica");

	private String name;

	SnapshotPriority(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
