package io.metaloom.qdrant.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.telemetry.CollectionDetailTelemetry;
import io.metaloom.qdrant.client.http.model.telemetry.CollectionTelemetry;
import io.metaloom.qdrant.client.http.model.telemetry.CollectionsAggregatedTelemetry;

public class CollectionTelemetryDeserializer extends JsonDeserializer<CollectionTelemetry> {

	@Override
	public CollectionTelemetry deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

		if (node.has("id")) {
			return mapper.convertValue(node, CollectionDetailTelemetry.class);
		} else {
			return mapper.convertValue(node, CollectionsAggregatedTelemetry.class);
		}
	}

}
