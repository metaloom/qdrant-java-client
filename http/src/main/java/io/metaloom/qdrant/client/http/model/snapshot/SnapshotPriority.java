package io.metaloom.qdrant.client.http.model.snapshot;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SnapshotPriority {

	SNAPSHOT("snapshot"), REPLICA("replica");

	private String name;

	SnapshotPriority(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
