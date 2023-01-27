package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Distance {

	UNKNOWN_DISTANCE("Unknown"),

	COSINE("Cosine"),

	EUCLID("Euclid"),

	DOT("Dot"),

	UNRECOGNIZED("Unrecognized");

	String name;

	Distance(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

}
