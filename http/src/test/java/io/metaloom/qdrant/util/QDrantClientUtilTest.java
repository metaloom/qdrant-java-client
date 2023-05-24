package io.metaloom.qdrant.util;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.http.model.point.PointIdLong;
import io.metaloom.qdrant.client.util.QDrantClientUtil;

public class QDrantClientUtilTest {

	@Test
	public void testAssertPoint() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			QDrantClientUtil.assertPointId(new PointIdLong());
			QDrantClientUtil.assertPointId(null);
		});
	}

	@Test
	public void testUUID() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			QDrantClientUtil.assertUuid(UUID.randomUUID());
			QDrantClientUtil.assertUuid(null);
		});
	}

}
