package io.metaloom.qdrant.client.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.AliasOperation;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.Condition;
import io.metaloom.qdrant.client.http.model.collection.filter.match.Match;
import io.metaloom.qdrant.client.http.model.point.NamedVector;
import io.metaloom.qdrant.client.http.model.point.Payload;
import io.metaloom.qdrant.client.http.model.point.Vector;
import io.metaloom.qdrant.client.http.model.point.VectorData;
import io.metaloom.qdrant.client.http.model.telemetry.CollectionTelemetry;
import io.metaloom.qdrant.client.json.serializer.AliasOperationDeserializer;
import io.metaloom.qdrant.client.json.serializer.CollectionTelemetryDeserializer;
import io.metaloom.qdrant.client.json.serializer.ConditionDeserializer;
import io.metaloom.qdrant.client.json.serializer.MatchDeserializer;
import io.metaloom.qdrant.client.json.serializer.NamedVectorDeserializer;
import io.metaloom.qdrant.client.json.serializer.PayloadDeserializer;
import io.metaloom.qdrant.client.json.serializer.PayloadSerializer;
import io.metaloom.qdrant.client.json.serializer.VectorDataDeserializer;
import io.metaloom.qdrant.client.json.serializer.VectorDataSerializer;
import io.metaloom.qdrant.client.json.serializer.VectorDeserializer;
import io.metaloom.qdrant.client.json.serializer.VectorSerializer;
import io.metaloom.qdrant.client.json.serializer.VectorsConfigDeserializer;

/**
 * Helper which manages JSON handling.
 */
public final class Json {

	public static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper()
			.enable(SerializationFeature.INDENT_OUTPUT)
			.setSerializationInclusion(Include.NON_NULL);

		SimpleModule module = new SimpleModule();
		module.addDeserializer(Condition.class, new ConditionDeserializer());
		module.addDeserializer(Match.class, new MatchDeserializer());
		module.addDeserializer(Payload.class, new PayloadDeserializer());
		module.addSerializer(Payload.class, new PayloadSerializer());
		module.addDeserializer(NamedVector.class, new NamedVectorDeserializer());
		module.addDeserializer(CollectionTelemetry.class, new CollectionTelemetryDeserializer());
		module.addDeserializer(AliasOperation.class, new AliasOperationDeserializer());
		module.addDeserializer(VectorsConfig.class, new VectorsConfigDeserializer());

		module.addSerializer(Vector.class, new VectorSerializer());
		module.addDeserializer(Vector.class, new VectorDeserializer());

		//module.addSerializer(VectorData.class, new VectorDataSerializer());
		module.addDeserializer(VectorData.class, new VectorDataDeserializer());
		mapper.registerModule(module);
	}

	private Json() {
	}

	public static JsonNode toJson(String content) throws JsonMappingException, JsonProcessingException {
		JsonNode json = mapper.readTree(content);
		if (json == null) {
			return null;
		}
		return json;
	}

	public static String parse(RestModel model) {
		try {
			return mapper.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error while parsing model to JSON.", e);
		}
	}

	public static <T extends RestModel> T parse(String json, Class<T> modelClass) {
		try {
			return mapper.readValue(json, modelClass);
		} catch (JacksonException e) {
			throw new RuntimeException("Error while parsing model to JSON.", e);
		}
	}

}
