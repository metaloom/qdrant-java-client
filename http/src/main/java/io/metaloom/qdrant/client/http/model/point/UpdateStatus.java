package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UpdateStatus {

	ACKNOWLEDGED("acknowledged"), COMPLETED("completed");

	private String name;

	UpdateStatus(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

}
