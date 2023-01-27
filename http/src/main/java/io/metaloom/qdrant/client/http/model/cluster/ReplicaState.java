package io.metaloom.qdrant.client.http.model.cluster;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReplicaState {

	ACTIVE("Active"), DEAD("Dead"), PARTIAL("Partial");

	private String name;

	private ReplicaState(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
