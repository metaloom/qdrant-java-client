package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

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
			VectorDataMap vectorData = (VectorDataMap) value;
			gen.writeStartObject();
			for (Entry<String, List<Float>> entry : vectorData.entrySet()) {
				gen.writeArrayFieldStart(entry.getKey());
				for (float f : entry.getValue()) {
					gen.writeNumber(f);
				}
				gen.writeEndArray();
			}
			gen.writeEndObject();
		} else if (value instanceof VectorDataPlain) {
			VectorDataPlain vectorData = (VectorDataPlain) value;
			List<Float> vector = vectorData.getVector();
			gen.writeStartArray();
			for(float f : vector) {
				gen.writeNumber(f);
			}
			gen.writeEndArray();
		} else {
			throw new RuntimeException("Enountered unknown vector data type. Got: " + value.getClass().getSimpleName());
		}

	}

}
