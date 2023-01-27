package io.metaloom.qdrant.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.metaloom.qdrant.client.http.model.RestModel;

/**
 * Helper which manages JSON handling.
 */
public final class Json {

	public static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper()
			.enable(SerializationFeature.INDENT_OUTPUT);
	}

	private Json() {
	}

	public static JsonNode toJson(String content) throws JsonMappingException, JsonProcessingException {
		JsonNode json = mapper.readTree(content);
		if (json == null) {
			return null;
		}
		return json;
	}

	public static String parse(RestModel model) {
		try {
			return mapper.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error while parsing model to JSON.", e);
		}
	}

	public static <T extends RestModel> T parse(String json, Class<T> modelClass) {
		try {
			return mapper.readValue(json, modelClass);
		} catch (JacksonException e) {
			throw new RuntimeException("Error while parsing model to JSON.", e);
		}
	}
}
