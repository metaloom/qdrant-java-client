package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.point.BatchVectorData;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataMap;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataPlain;

public class BatchVectorDataDeserializer extends JsonDeserializer<BatchVectorData> {

	@Override
	public BatchVectorData deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

		if (node.isArray()) {
			return mapper.convertValue(node, BatchVectorDataPlain.class);
		} else {
			return mapper.convertValue(node, BatchVectorDataMap.class);
		}
	}
}
