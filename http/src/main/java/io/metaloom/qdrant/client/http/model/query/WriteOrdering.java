package io.metaloom.qdrant.client.http.model.query;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WriteOrdering {

	WEAK("weak"),

	MEDIUM("medium"),

	STRONG("strong");

	String name;

	private WriteOrdering(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
