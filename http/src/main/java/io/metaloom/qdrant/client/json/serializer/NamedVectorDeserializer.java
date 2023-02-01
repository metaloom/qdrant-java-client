package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.point.NamedVector;

public class NamedVectorDeserializer extends JsonDeserializer<NamedVector> {

	@Override
	public NamedVector deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

		// If the vector is not named we just write the vector data into a empty named vector object
		if (node.isArray()) {
			NamedVector vector = new NamedVector();
			List<Float> vectorData = new ArrayList<>();
			for (int i = 0; i < node.size(); i++) {
				JsonNode number = node.get(i);
				if (number.isNumber()) {
					vectorData.add((float) number.asDouble());
				} else {
					throw new RuntimeException(
						"Vector component could not be parsed. Unsupported value type encountered. Got: " + number.getNodeType());
				}
			}
			vector.setVector(vectorData);
			return vector;
		} else {
			return mapper.convertValue(node, NamedVector.class);
		}
	}

}
