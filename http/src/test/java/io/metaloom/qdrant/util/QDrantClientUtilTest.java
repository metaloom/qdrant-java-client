package io.metaloom.qdrant.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.point.PointIdLong;
import io.metaloom.qdrant.client.util.QDrantClientUtil;

public class QDrantClientUtilTest {

	@Test
	public void testToList() {
		List<Float> list = QDrantClientUtil.toList(0.42f, 0.24f);
		assertEquals(2, list.size());
		assertEquals(0.42f, list.get(0), 0f);
		assertEquals(0.24f, list.get(1), 0f);

		List<Long> list2 = QDrantClientUtil.toList(42L, 24L);
		assertEquals(2, list2.size());
		assertEquals(42, list2.get(0), 0f);
		assertEquals(24, list2.get(1), 0f);
	}

	@Test(expected = NullPointerException.class)
	public void testAssertPoint() {
		QDrantClientUtil.assertPointId(new PointIdLong());
		QDrantClientUtil.assertPointId(null);
	}

	@Test(expected = NullPointerException.class)
	public void testUUID() {
		QDrantClientUtil.assertUuid(UUID.randomUUID());
		QDrantClientUtil.assertUuid(null);
	}

	@Test
	public void testToDoubleArray() {
		List<Float> list = new ArrayList<>();
		list.add(0.42f);
		list.add(0.24f);
		double[] array = QDrantClientUtil.toDoubleArray(list);
		assertEquals(2, array.length);
		assertEquals(0.42f, array[0], 0f);
		assertEquals(0.24f, array[1], 0f);
	}

	@Test
	public void testToArray() {
		List<Float> list = new ArrayList<>();
		list.add(0.42f);
		list.add(0.24f);
		float[] array = QDrantClientUtil.toArray(list);
		assertEquals(2, array.length);
		assertEquals(0.42f, array[0], 0f);
		assertEquals(0.24f, array[1], 0f);
	}
}
