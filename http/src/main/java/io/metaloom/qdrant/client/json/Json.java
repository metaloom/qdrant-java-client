package io.metaloom.qdrant.client.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.AliasOperation;
import io.metaloom.qdrant.client.http.model.collection.config.QuantizationConfig;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.Condition;
import io.metaloom.qdrant.client.http.model.collection.filter.match.Match;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadFieldSchema;
import io.metaloom.qdrant.client.http.model.point.BatchVectorData;
import io.metaloom.qdrant.client.http.model.point.NamedVector;
import io.metaloom.qdrant.client.http.model.point.Payload;
import io.metaloom.qdrant.client.http.model.point.PointId;
import io.metaloom.qdrant.client.http.model.point.Vector;
import io.metaloom.qdrant.client.http.model.point.VectorData;
import io.metaloom.qdrant.client.http.model.telemetry.CollectionTelemetry;
import io.metaloom.qdrant.client.json.serializer.AliasOperationDeserializer;
import io.metaloom.qdrant.client.json.serializer.BatchVectorDataDeserializer;
import io.metaloom.qdrant.client.json.serializer.BatchVectorDataSerializer;
import io.metaloom.qdrant.client.json.serializer.CollectionTelemetryDeserializer;
import io.metaloom.qdrant.client.json.serializer.ConditionDeserializer;
import io.metaloom.qdrant.client.json.serializer.MatchDeserializer;
import io.metaloom.qdrant.client.json.serializer.NamedVectorDeserializer;
import io.metaloom.qdrant.client.json.serializer.PayloadDeserializer;
import io.metaloom.qdrant.client.json.serializer.PayloadFieldSchemaDeserializer;
import io.metaloom.qdrant.client.json.serializer.PayloadFieldSchemaSerializer;
import io.metaloom.qdrant.client.json.serializer.PayloadSerializer;
import io.metaloom.qdrant.client.json.serializer.PointIdDeserializer;
import io.metaloom.qdrant.client.json.serializer.PointIdSerializer;
import io.metaloom.qdrant.client.json.serializer.QuantizationConfigDeserializer;
import io.metaloom.qdrant.client.json.serializer.VectorDataDeserializer;
import io.metaloom.qdrant.client.json.serializer.VectorDataSerializer;
import io.metaloom.qdrant.client.json.serializer.VectorDeserializer;
import io.metaloom.qdrant.client.json.serializer.VectorSerializer;
import io.metaloom.qdrant.client.json.serializer.VectorsConfigDeserializer;

/**
 * Helper which manages JSON handling.
 */
public final class Json {

	private static final String PARSE_ERROR = "Error while parsing model to JSON.";

	public static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL);

		SimpleModule module = new SimpleModule();
		module.addDeserializer(NamedVector.class, new NamedVectorDeserializer());
		module.addDeserializer(Condition.class, new ConditionDeserializer());
		module.addDeserializer(Match.class, new MatchDeserializer());
		module.addDeserializer(CollectionTelemetry.class, new CollectionTelemetryDeserializer());
		module.addDeserializer(AliasOperation.class, new AliasOperationDeserializer());
		module.addDeserializer(VectorsConfig.class, new VectorsConfigDeserializer());

		module.addDeserializer(Payload.class, new PayloadDeserializer());
		module.addSerializer(Payload.class, new PayloadSerializer());

		module.addSerializer(Vector.class, new VectorSerializer());
		module.addDeserializer(Vector.class, new VectorDeserializer());

		module.addSerializer(VectorData.class, new VectorDataSerializer());
		module.addDeserializer(VectorData.class, new VectorDataDeserializer());

		module.addSerializer(PointId.class, new PointIdSerializer());
		module.addDeserializer(PointId.class, new PointIdDeserializer());

		module.addSerializer(BatchVectorData.class, new BatchVectorDataSerializer());
		module.addDeserializer(BatchVectorData.class, new BatchVectorDataDeserializer());

		module.addSerializer(PayloadFieldSchema.class, new PayloadFieldSchemaSerializer());
		module.addDeserializer(PayloadFieldSchema.class, new PayloadFieldSchemaDeserializer());

		module.addDeserializer(QuantizationConfig.class, new QuantizationConfigDeserializer());
		mapper.registerModule(module);
	}

	private Json() {
	}

	public static JsonNode toJson(String content) throws JsonProcessingException {
		JsonNode json = mapper.readTree(content);
		if (json == null) {
			return null;
		}
		return json;
	}

	public static String parse(RestModel model) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(PARSE_ERROR, e);
		}
	}

	public static String parseCompact(RestModel model) {
		try {
			return mapper.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(PARSE_ERROR, e);
		}
	}

	public static <T extends RestModel> T parse(String json, Class<T> modelClass) {
		try {
			return mapper.readValue(json, modelClass);
		} catch (JacksonException e) {
			throw new RuntimeException(PARSE_ERROR, e);
		}
	}

}
