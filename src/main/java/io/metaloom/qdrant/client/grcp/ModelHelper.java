package io.metaloom.qdrant.client.grcp;

import qdrant.JsonWithInt;
import qdrant.JsonWithInt.Value;
import qdrant.Points.PointId;
import qdrant.Points.Vector;
import qdrant.Points.Vector.Builder;

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
		Builder builder = Vector.newBuilder();
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
