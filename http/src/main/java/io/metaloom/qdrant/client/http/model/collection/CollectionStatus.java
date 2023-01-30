package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CollectionStatus {

	GREEN("green"), YELLOW("yellow"), RED("red");

	private String name;

	private CollectionStatus(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
