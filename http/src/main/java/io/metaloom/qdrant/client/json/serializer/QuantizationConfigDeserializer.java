package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.collection.config.QuantizationConfig;
import io.metaloom.qdrant.client.http.model.collection.config.ScalarQuantization;

public class QuantizationConfigDeserializer extends JsonDeserializer<QuantizationConfig> {

	@Override
	public QuantizationConfig deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		if (node.has("scalar")) {
			return mapper.convertValue(node, ScalarQuantization.class);
		}
		throw new RuntimeException("Error while deserializing JSON. Unable to find match implementation for " + node.toPrettyString());
	}

}
