package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.RestModel;

public class Payload implements RestModel {

	private JsonNode json;

	public JsonNode getJson() {
		return json;
	}

	public Payload setJson(JsonNode json) {
		this.json = json;
		return this;
	}
}
