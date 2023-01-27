package io.metaloom.qdrant.client.grcp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.grpc.proto.Collections.Distance;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.Vector;
import io.metaloom.qdrant.client.grpc.proto.Points.Vectors;

public class QDrantGRCPClientTest extends AbstractContainerTest {

	@Test
	public void testClient() throws Exception {
		QDrantGRCPClient client = QDrantGRCPClient.builder()
			.setHostname("localhost")
			.setPort(qdrant.grcpPort())
			.build();

		VectorParams params = VectorParams.newBuilder()
			.setSize(4)
			.setDistance(Distance.Euclid)
			.build();

		// Create new collections
		assertTrue(client.createCollection("test1", params).blocking().getResult());
		assertTrue(client.createCollection("test2", params).future().get().getResult());
		assertTrue(client.createCollection("test3", params).rx().blockingGet().getResult());

		// Count vectors
		assertEquals(0, client.countPoints("test1", null, true).blocking().getResult().getCount());

		// Insert a new vector
		for (int i = 0; i < 10; i++) {
			Vector vector = ModelHelper.toVector(new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f });
			PointStruct point = PointStruct.newBuilder()
				.putPayload("color", ModelHelper.toValue("blue"))
				.setId(ModelHelper.toPointId(42L + i))
				.setVectors(Vectors.newBuilder().setVector(vector))
				.build();
			System.out.println(client.upsertPoint("test1", point, true).blocking().getResult().getStatus());
		}

		assertEquals(10, client.countPoints("test1", null, true).blocking().getResult().getCount());
	}
}
