package io.metaloom.qdrant.client.grpc.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.grpc.QDrantGRPCClient;
import io.metaloom.qdrant.client.grpc.proto.Collections.Distance;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;
import io.metaloom.qdrant.client.grpc.proto.JsonWithInt.Value;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.ScoredPoint;
import io.metaloom.qdrant.client.util.ModelHelper;

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
				float[] vector = new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f - i };
				Map<String, Value> payload = new HashMap<>();
				payload.put("color", ModelHelper.value("blue"));
				PointStruct point = ModelHelper.point(42L + i, vector, payload);
				System.out.println("[" + point.getId().getNum() + "] => " + client.upsertPoint("test1", point, true).sync().getResult().getStatus());
			}

			assertEquals(10, client.countPoints("test1", null, true).sync().getResult().getCount());

			// Now run KNN search
			float[] searchVector = new float[] { 0.43f, 0.09f, 0.41f, 1.35f };
			List<ScoredPoint> searchResults = client.searchPoints("test1", searchVector, 2, null).sync().getResultList();
			for (ScoredPoint result : searchResults) {
				System.out.println("Found: [" + result.getId().getNum() + "] " + result.getScore());
			}
		}
	}
}
