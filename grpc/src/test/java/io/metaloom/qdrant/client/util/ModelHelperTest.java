package io.metaloom.qdrant.client.util;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

public class ModelHelperTest {

	@Test
	public void testToPointId() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid.toString(), ModelHelper.pointId(uuid).getUuid());
		assertEquals(uuid.toString(), ModelHelper.pointId(uuid.toString()).getUuid());
	}
}
