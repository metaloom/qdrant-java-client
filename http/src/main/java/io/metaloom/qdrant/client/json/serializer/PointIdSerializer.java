package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.metaloom.qdrant.client.http.model.point.PointId;
import io.metaloom.qdrant.client.http.model.point.PointIdLong;
import io.metaloom.qdrant.client.http.model.point.PointIdUUID;

public class PointIdSerializer extends JsonSerializer<PointId> {

	@Override
	public void serialize(PointId value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value == null) {
			gen.writeNull();
		} else {
			if (value instanceof PointIdLong) {
				gen.writeNumber(((PointIdLong) value).getId());
			} else if (value instanceof PointIdUUID) {
				gen.writeString(((PointIdUUID) value).getId().toString());
			} else {
				throw new RuntimeException("Encountered unknown pointId type. Got: " + value.getClass().getSimpleName());
			}
		}
	}

}
