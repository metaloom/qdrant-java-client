package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.metaloom.qdrant.client.http.model.point.Payload;

public class PayloadSerializer extends JsonSerializer<Payload> {

	@Override
	public void serialize(Payload value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value != null && value.getJson() != null) {
			gen.writeTree(value.getJson());
		}
	}

}
