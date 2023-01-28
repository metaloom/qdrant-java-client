package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.json.Json;
import io.metaloom.qdrant.client.json.JsonException;

public class Payload implements RestModel {

	private JsonNode json;

	public JsonNode getJson() {
		return json;
	}

	public Payload setJson(JsonNode json) {
		this.json = json;
		return this;
	}

	@JsonIgnore
	public String text(String key) throws JsonException {
		if (json == null) {
			return null;
		} else {
			JsonNode element = json.get(key);
			if (element == null) {
				return null;
			} else {
				if (element.isTextual()) {
					return element.asText();
				} else {
					throw new JsonException("The field for key {" + key + "} does not contain a text value. Got: " + element.getNodeType());
				}
			}

		}
	}

	@JsonIgnore
	public Number number(String key) throws JsonException {
		if (json == null) {
			return null;
		} else {
			JsonNode element = json.get(key);
			if (element == null) {
				return null;
			} else {
				if (element.isNumber()) {
					return element.numberValue();
				} else {
					throw new JsonException("The field for key {" + key + "} does not contain a number value. Got: " + element.getNodeType());
				}
			}
		}
	}

	@JsonIgnore
	public Boolean bool(String key) throws JsonException {
		if (json == null) {
			return null;
		} else {
			JsonNode element = json.get(key);
			if (element == null) {
				return null;
			} else {
				if (element.isBoolean()) {
					return element.booleanValue();
				} else {
					throw new JsonException("The field for key {" + key + "} does not contain a boolean value. Got: " + element.getNodeType());
				}
			}
		}
	}

	/**
	 * Construct a new payload using the provided JSON string.
	 * 
	 * @param json
	 * @return
	 * @throws JacksonException
	 */
	public static Payload of(String json) throws JacksonException {
		return new Payload().setJson(Json.toJson(json));
	}
}
