package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.metaloom.qdrant.client.http.model.point.VectorData;
import io.metaloom.qdrant.client.http.model.point.VectorDataMap;
import io.metaloom.qdrant.client.http.model.point.VectorDataPlain;

public class VectorDataSerializer extends JsonSerializer<VectorData> {

	@Override
	public void serialize(VectorData value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value instanceof VectorDataMap) {
//			gen.writeTree(value);
		} else if (value instanceof VectorDataPlain) {
//			gen.writeTree(value);
		} else {
			throw new RuntimeException("Enountered unknown vector data type. Got: " + value.getClass().getSimpleName());
		}

	}

}
