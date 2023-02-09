package io.metaloom.qdrant.client.http.model.collection.schema;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PayloadIndexSchemaType implements PayloadFieldSchema {

	KEYWORD("keyword"),

	INTEGER("integer"),

	FLOAT("float"),

	GEO("geo"),

	TEXT("text");

	String name;

	private PayloadIndexSchemaType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	public static PayloadIndexSchemaType fromString(String text) {
		for (PayloadIndexSchemaType b : PayloadIndexSchemaType.values()) {
			if (b.name.equals(text)) {
				return b;
			}
		}
		return null;
	}
}
