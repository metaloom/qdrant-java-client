package io.metaloom.qdrant.util;

import java.util.UUID;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.point.PointIdLong;
import io.metaloom.qdrant.client.util.QDrantClientUtil;

public class QDrantClientUtilTest {

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

}
