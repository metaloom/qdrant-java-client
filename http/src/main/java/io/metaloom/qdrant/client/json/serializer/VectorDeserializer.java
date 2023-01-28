package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.point.Vector;

public class VectorDeserializer extends JsonDeserializer<Vector> {

	@Override
	public Vector deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		if (node.isArray()) {
			Vector vector = new Vector();
			List<Float> components = new ArrayList<>();
			for (int i = 0; i < node.size(); i++) {
				JsonNode element = node.get(i);
				if (element.isFloatingPointNumber()) {
					components.add((float) element.asDouble());
				}
			}
			vector.setComponents(components);
			return vector;
		}
		return null;
	}

}
