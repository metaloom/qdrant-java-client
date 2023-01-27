package io.metaloom.qdrant.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.point.Payload;

public class PayloadDeserializer extends JsonDeserializer<Payload> {

	@Override
	public Payload deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		Payload payload = new Payload();
		payload.setJson(node);
		return payload;
	}
}
