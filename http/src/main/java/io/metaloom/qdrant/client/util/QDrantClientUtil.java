package io.metaloom.qdrant.client.util;

import java.util.ArrayList;
import java.util.List;

public final class QDrantClientUtil {

	private QDrantClientUtil() {
	}

	public static float[] toArray(List<Float> list) {
		float[] floatArray = new float[list.size()];
		int i = 0;
		for (Float f : list) {
			floatArray[i++] = (f != null ? f : Float.NaN);
		}
		return floatArray;
	}

	public static double[] toDoubleArray(List<Float> list) {
		double[] doubleArray = new double[list.size()];
		int i = 0;
		for (Float f : list) {
			doubleArray[i++] = (f != null ? f : Double.NaN);
		}
		return doubleArray;
	}

	public static List<Float> toList(float... values) {
		List<Float> result = new ArrayList<>(values.length);
		for (float id : values) {
			result.add(id);
		}
		return result;
	}

	public static List<Long> toList(long... values) {
		List<Long> result = new ArrayList<>(values.length);
		for (long id : values) {
			result.add(id);
		}
		return result;
	}
}
