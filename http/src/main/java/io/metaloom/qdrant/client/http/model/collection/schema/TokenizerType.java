package io.metaloom.qdrant.client.http.model.collection.schema;

import com.fasterxml.jackson.annotation.JsonValue;

import io.metaloom.qdrant.client.http.model.RestModel;

public enum TokenizerType implements RestModel {

	PREFIX("prefix"),

	WHITESPACE("whitespace"),

	WORD("word");

	private String name;

	private TokenizerType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
