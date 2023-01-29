package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.point.PointId;

public class PointIdDeserializer extends JsonDeserializer<PointId> {

	@Override
	public PointId deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		if (node.isNumber()) {
			return PointId.of(node.longValue());
		} else {
			return PointId.of(node.textValue());
		}
	}

}
