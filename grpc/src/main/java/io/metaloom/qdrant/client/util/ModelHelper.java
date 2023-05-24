package io.metaloom.qdrant.client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.metaloom.qdrant.client.grpc.proto.JsonWithInt;
import io.metaloom.qdrant.client.grpc.proto.JsonWithInt.Value;
import io.metaloom.qdrant.client.grpc.proto.Points.NamedVectors;
import io.metaloom.qdrant.client.grpc.proto.Points.PointId;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct.Builder;
import io.metaloom.qdrant.client.grpc.proto.Points.PointVectors;
import io.metaloom.qdrant.client.grpc.proto.Points.PointsIdsList;
import io.metaloom.qdrant.client.grpc.proto.Points.PointsSelector;
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
	public static Vector vector(float[] vector) {
		Vector.Builder builder = Vector.newBuilder();
		for (int i = 0; i < vector.length; i++) {
			builder.addData(vector[i]);
		}
		return builder.build();
	}

	public static List<Float> vectorList(Float... vectors) {
		List<Float> vectorList = new ArrayList<>(vectors.length);
		for (float f : vectors) {
			vectorList.add(Float.valueOf(f));
		}
		return vectorList;
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

	/**
	 * Construct a {@link PointId} from the provided uuid.
	 * 
	 * @param uuid
	 * @return
	 */
	public static PointId pointId(UUID uuid) {
		Objects.requireNonNull(uuid, "The provided uuid must not be null");
		return PointId.newBuilder().setUuid(uuid.toString()).build();
	}

	/**
	 * Construct a {@link PointId} from the provided uuid string.
	 * 
	 * @param uuid
	 * @return
	 */
	public static PointId pointId(String uuid) {
		Objects.requireNonNull(uuid, "The provided uuid must not be null");
		return PointId.newBuilder().setUuid(UUID.fromString(uuid).toString()).build();
	}

	/**
	 * Create a list of {@link PointId}d
	 * 
	 * @param ids
	 * @return
	 */
	public static List<PointId> pointIds(long... ids) {
		List<PointId> list = new ArrayList<>();
		for (long id : ids) {
			list.add(pointId(id));
		}
		return list;
	}

	public static PointStruct point(String uuid, float[] vectorData, Map<String, Value> payload) {
		return point(pointId(uuid), vectorData, payload);
	}

	public static PointStruct point(UUID uuid, float[] vectorData, Map<String, Value> payload) {
		return point(pointId(uuid), vectorData, payload);
	}

	public static PointStruct point(long id, float[] vectorData, Map<String, Value> payload) {
		return point(pointId(id), vectorData, payload);
	}

	/**
	 * Construct a new {@link PointStruct} using the provided data.
	 * 
	 * @param id
	 * @param vectorData
	 * @param payload
	 * @return
	 */
	public static PointStruct point(PointId id, float[] vectorData, Map<String, Value> payload) {
		Objects.requireNonNull(id, "A pointId must be provided.");
		Vector vector = vector(vectorData);
		Builder builder = PointStruct.newBuilder()
			.setId(id)
			.setVectors(Vectors.newBuilder().setVector(vector));
		if (payload != null) {
			builder.putAllPayload(payload);
		}
		return builder.build();
	}

	public static PointStruct namedPoint(Long id, String vectorName, float[] vectorData, Map<String, Value> payload) {
		return namedPoint(pointId(id), vectorName, vectorData, payload);
	}

	public static PointStruct namedPoint(PointId id, String vectorName, float[] vectorData, Map<String, Value> payload) {
		Objects.requireNonNull(id, "A pointId must be provided.");
		NamedVectors vectors = namedVector(vectorName, vectorData);
		Builder builder = PointStruct.newBuilder()
			.setId(id)
			.setVectors(Vectors.newBuilder().setVectors(vectors));
		if (payload != null) {
			builder.putAllPayload(payload);
		}
		return builder.build();
	}

	public static PointsSelector selectByIds(Long... ids) {
		PointsIdsList.Builder pointList = PointsIdsList.newBuilder();
		for (Long id : ids) {
			pointList.addIds(pointId(id));
		}

		return PointsSelector.newBuilder().setPoints(pointList.build()).build();
	}

	public static WithPayloadSelector withPayload() {
		return WithPayloadSelector.newBuilder().setEnable(true).build();
	}

	public static WithVectorsSelector withVector() {
		return WithVectorsSelector.newBuilder().setEnable(true).build();
	}

	public static PointVectors pointVector(long id, String name, float[] vector) {
		Vectors vectors = Vectors.newBuilder()
			.setVectors(namedVector(name, vector))
			.build();
		return PointVectors.newBuilder()
			.setId(pointId(id))
			.setVectors(vectors)
			.build();
	}

	public static NamedVectors namedVector(String name, float[] vector) {
		return NamedVectors.newBuilder()
			.putVectors(name, vector(vector))
			.build();
	}
}
