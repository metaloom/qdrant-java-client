package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.collection.schema.FullTextIndexFieldSchema;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadFieldSchema;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadIndexSchemaType;

public class PayloadFieldSchemaDeserializer extends JsonDeserializer<PayloadFieldSchema> {

	@Override
	public PayloadFieldSchema deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		if (node.isObject()) {
			return mapper.convertValue(node, FullTextIndexFieldSchema.class);
		} else {
			String type = node.textValue();
			return PayloadIndexSchemaType.fromString(type);
		}
	}

}
