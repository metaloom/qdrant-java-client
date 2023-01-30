package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.metaloom.qdrant.client.http.model.point.BatchVectorData;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataMap;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataPlain;

public class BatchVectorDataSerializer extends JsonSerializer<BatchVectorData> {

	@Override
	public void serialize(BatchVectorData value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value instanceof BatchVectorDataMap) {
			BatchVectorDataMap mapData = (BatchVectorDataMap) value;
			gen.writeStartObject();
			for (Entry<String, List<List<Float>>> entry : mapData.entrySet()) {
				String name = entry.getKey();
				gen.writeArrayFieldStart(name);
				List<List<Float>> listData = entry.getValue();
				for (List<Float> vectorList : listData) {
					gen.writeStartArray();
					for (float f : vectorList) {
						gen.writeNumber(f);
					}
					gen.writeEndArray();
				}
				gen.writeEndArray();
			}
			gen.writeEndObject();
		} else if (value instanceof BatchVectorDataPlain) {
			BatchVectorDataPlain plainData = (BatchVectorDataPlain) value;
			gen.writeStartArray();
			for (List<Float> listData : plainData) {
				gen.writeStartArray();
				for (float f : listData) {
					gen.writeNumber(f);
				}
				gen.writeEndArray();
			}
			gen.writeEndArray();
		} else {
			throw new RuntimeException("Enountered unknown vector data type. Got: " + value.getClass().getSimpleName());
		}
	}

}
