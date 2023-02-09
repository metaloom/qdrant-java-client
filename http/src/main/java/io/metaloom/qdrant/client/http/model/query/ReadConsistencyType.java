package io.metaloom.qdrant.client.http.model.query;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReadConsistencyType {

	MAJORITY("majority"),

	QUORUM("quorum"),

	ALL("all");

	String name;

	private ReadConsistencyType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
