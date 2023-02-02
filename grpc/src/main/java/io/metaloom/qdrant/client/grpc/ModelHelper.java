package io.metaloom.qdrant.client.grpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.metaloom.qdrant.client.grpc.proto.JsonWithInt;
import io.metaloom.qdrant.client.grpc.proto.JsonWithInt.Value;
import io.metaloom.qdrant.client.grpc.proto.Points.PointId;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct.Builder;
import io.metaloom.qdrant.client.grpc.proto.Points.Vector;
import io.metaloom.qdrant.client.grpc.proto.Points.Vectors;
import io.metaloom.qdrant.client.grpc.proto.Points.WithPayloadSelector;
import io.metaloom.qdrant.client.grpc.proto.Points.WithVectorsSelector;

public final class ModelHelper {

	private ModelHelper() {
	}

	/**
	 * Convert the float array into a vector model.
	 * 
	 * @param vector
	 * @return
	 */
	public static Vector toVector(float[] vector) {
		Vector.Builder builder = Vector.newBuilder();
		for (int i = 0; i < vector.length; i++) {
			builder.addData(vector[i]);
		}
		return builder.build();
	}

	/**
	 * Convert the string into a value model.
	 * 
	 * @param text
	 * @return
	 */
	public static Value value(String text) {
		return JsonWithInt.Value.newBuilder().setStringValue(text).build();
	}

	/**
	 * Convert the number into a {@link PointId} object.
	 * 
	 * @param id
	 * @return
	 */
	public static PointId pointId(long id) {
		return PointId.newBuilder().setNum(id).build();
	}

	public static PointId pointId(UUID uuid) {
		Objects.requireNonNull(uuid, "The provided uuid must not be null");
		return PointId.newBuilder().setUuid(uuid.toString()).build();
	}

	public static PointId pointId(String uuid) {
		Objects.requireNonNull(uuid, "The provided uuid must not be null");
		return PointId.newBuilder().setUuid(UUID.fromString(uuid).toString()).build();
	}

	public static PointStruct point(String uuid, float[] vectorData, Map<String, Value> payload) {
		return toPointStruct(pointId(uuid), vectorData, payload);
	}

	public static PointStruct point(UUID uuid, float[] vectorData, Map<String, Value> payload) {
		return toPointStruct(pointId(uuid), vectorData, payload);
	}

	public static PointStruct point(long id, float[] vectorData, Map<String, Value> payload) {
		return toPointStruct(pointId(id), vectorData, payload);
	}

	public static List<PointId> pointIds(long... ids) {
		List<PointId> list = new ArrayList<>();
		for (long id : ids) {
			list.add(pointId(id));
		}
		return list;
	}

	public static PointStruct toPointStruct(PointId id, float[] vectorData, Map<String, Value> payload) {
		Objects.requireNonNull(id, "A pointId must be provided.");
		Vector vector = ModelHelper.toVector(vectorData);
		Builder builder = PointStruct.newBuilder()
			.setId(id)
			.setVectors(Vectors.newBuilder().setVector(vector));
		if (payload != null) {
			builder.putAllPayload(payload);
		}
		return builder.build();
	}

	public static WithPayloadSelector withPayload() {
		return WithPayloadSelector.newBuilder().setEnable(true).build();
	}

	public static WithVectorsSelector withVector() {
		return WithVectorsSelector.newBuilder().setEnable(true).build();
	}
}
