package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PayloadSchemaParams {

	KEYWORD("keyword"),

	INTEGER("integer"),

	FLOAT("float"),

	GEO("geo"),

	TEXT("text");

	String name;

	private PayloadSchemaParams(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

}
