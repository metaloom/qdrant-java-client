package io.metaloom.qdrant.json;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class Json {

	public static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper()
			.enable(SerializationFeature.INDENT_OUTPUT);
	}

	private Json() {
	}

	public static JSONObject toJson(String content) throws JsonMappingException, JsonProcessingException {
		JsonNode json = mapper.readTree(content);
		if (json == null) {
			return null;
		}
		return new JSONObject(content);
	}
}
