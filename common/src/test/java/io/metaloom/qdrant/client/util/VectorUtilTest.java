package io.metaloom.qdrant.client.util;

import static io.metaloom.qdrant.client.util.VectorUtil.toArray;
import static io.metaloom.qdrant.client.util.VectorUtil.toDoubleArray;
import static io.metaloom.qdrant.client.util.VectorUtil.toList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VectorUtilTest {

	@Test
	public void testToList() {
		List<Float> list = toList(0.42f, 0.24f);
		assertEquals(2, list.size());
		assertEquals(0.42f, list.get(0), 0f);
		assertEquals(0.24f, list.get(1), 0f);

		List<Long> list2 = toList(42L, 24L);
		assertEquals(2, list2.size());
		assertEquals(42, list2.get(0), 0f);
		assertEquals(24, list2.get(1), 0f);
	}

	@Test
	public void testToDoubleArray() {
		List<Float> list = new ArrayList<>();
		list.add(0.42f);
		list.add(0.24f);
		double[] array = toDoubleArray(list);
		assertEquals(2, array.length);
		assertEquals(0.42f, array[0], 0f);
		assertEquals(0.24f, array[1], 0f);
	}

	@Test
	public void testToArray() {
		List<Float> list = new ArrayList<>();
		list.add(0.42f);
		list.add(0.24f);
		float[] array = toArray(list);
		assertEquals(2, array.length);
		assertEquals(0.42f, array[0], 0f);
		assertEquals(0.24f, array[1], 0f);
	}
}
