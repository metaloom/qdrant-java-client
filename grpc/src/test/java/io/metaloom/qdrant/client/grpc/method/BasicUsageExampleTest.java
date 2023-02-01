package io.metaloom.qdrant.client.grpc.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.grpc.ModelHelper;
import io.metaloom.qdrant.client.grpc.QDrantGRPCClient;
import io.metaloom.qdrant.client.grpc.proto.Collections.Distance;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.Vector;
import io.metaloom.qdrant.client.grpc.proto.Points.Vectors;

public class BasicUsageExampleTest extends AbstractContainerTest {

	@Test
	public void testExample() throws Exception {

		int port = qdrant.grpcPort(); // Default: 6334

		try (QDrantGRPCClient client = QDrantGRPCClient.builder()
			.setHostname("localhost")
			.setPort(port)
			.build()) {

			VectorParams params = VectorParams.newBuilder()
				.setSize(4)
				.setDistance(Distance.Euclid)
				.build();

			// Create new collections
			assertTrue(client.createCollection("test1", params).sync().getResult());
			assertTrue(client.createCollection("test2", params).async().get().getResult());
			assertTrue(client.createCollection("test3", params).rx().blockingGet().getResult());

			// Count vectors
			assertEquals(0, client.countPoints("test1", null, true).sync().getResult().getCount());

			// Insert a new vector
			for (int i = 0; i < 10; i++) {
				Vector vector = ModelHelper.toVector(new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f });
				PointStruct point = PointStruct.newBuilder()
					.putPayload("color", ModelHelper.value("blue"))
					.setId(ModelHelper.pointId(42L + i))
					.setVectors(Vectors.newBuilder().setVector(vector))
					.build();
				System.out.println(client.upsertPoint("test1", point, true).sync().getResult().getStatus());
			}

			assertEquals(10, client.countPoints("test1", null, true).sync().getResult().getCount());
		}
	}
}
