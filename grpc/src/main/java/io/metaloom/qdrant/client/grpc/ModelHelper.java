package io.metaloom.qdrant.client.grpc;

import io.metaloom.qdrant.client.grpc.proto.JsonWithInt;
import io.metaloom.qdrant.client.grpc.proto.JsonWithInt.Value;
import io.metaloom.qdrant.client.grpc.proto.Points.PointId;
import io.metaloom.qdrant.client.grpc.proto.Points.Vector;

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
	public static Value toValue(String text) {
		return JsonWithInt.Value.newBuilder().setStringValue(text).build();
	}

	/**
	 * Convert the number into a {@link PointId} object.
	 * 
	 * @param num
	 * @return
	 */
	public static PointId toPointId(long num) {
		return PointId.newBuilder().setNum(num).build();
	}
}
