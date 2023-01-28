package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.collection.filter.Filter;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.Condition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.FieldCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.HasIdCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.IsEmptyCondition;

public class ConditionDeserializer extends JsonDeserializer<Condition> {

	@Override
	public Condition deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		if (node.has("is_empty")) {
			return mapper.convertValue(node, IsEmptyCondition.class);
		}
		if (node.has("has_id")) {
			return mapper.convertValue(node, HasIdCondition.class);
		}
		if (node.has("key")) {
			return mapper.convertValue(node, FieldCondition.class);
		}
		if (node.has("should") || node.has("must") || node.has("must_not")) {
			return mapper.convertValue(node, Filter.class);
		}
		throw new RuntimeException("Error while deserializing JSON. Unable to find condition implementation for " + node.toPrettyString());
	}

}
