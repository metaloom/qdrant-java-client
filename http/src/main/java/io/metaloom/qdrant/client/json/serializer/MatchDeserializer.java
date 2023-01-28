package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.collection.filter.match.Match;
import io.metaloom.qdrant.client.http.model.collection.filter.match.MatchText;
import io.metaloom.qdrant.client.http.model.collection.filter.match.MatchValue;

public class MatchDeserializer extends JsonDeserializer<Match> {

	@Override
	public Match deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		if (node.has("value")) {
			return mapper.convertValue(node, MatchValue.class);
		}
		if (node.has("text")) {
			return mapper.convertValue(node, MatchText.class);
		}
		throw new RuntimeException("Error while deserializing JSON. Unable to find match implementation for " + node.toPrettyString());
	}

}
