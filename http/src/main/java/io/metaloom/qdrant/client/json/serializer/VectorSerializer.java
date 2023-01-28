package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.metaloom.qdrant.client.http.model.point.Vector;

public class VectorSerializer extends JsonSerializer<Vector> {

	@Override
	public void serialize(Vector vector, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (vector != null) {
			float[] data = vector.array();
			double[] doubleArray = new double[data.length];
			for (int i = 0; i < data.length; i++) {
				doubleArray[i] = data[i];
			}
			gen.writeArray(doubleArray, 0, data.length);
		}

	}

}
