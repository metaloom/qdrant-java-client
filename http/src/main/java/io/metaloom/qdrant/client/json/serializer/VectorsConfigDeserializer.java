package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.collection.config.NamedVectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;

public class VectorsConfigDeserializer extends JsonDeserializer<VectorsConfig> {

	@Override
	public VectorsConfig deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

		if (node.has("size") && node.get("size").isNumber()) {
			return mapper.convertValue(node, VectorParams.class);
		} else {
			return mapper.convertValue(node, NamedVectorParams.class);
		}
	}

}
