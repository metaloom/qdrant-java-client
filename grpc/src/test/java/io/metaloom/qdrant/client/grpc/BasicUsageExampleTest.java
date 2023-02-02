package io.metaloom.qdrant.client.grpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
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

			// Define the collection to store vectors
			VectorParams params = VectorParams.newBuilder()
				.setSize(4)
				.setDistance(Distance.Euclid)
				.build();

			// Create new collections - blocking
			client.createCollection("test1", params).sync();
			// .. or via Future API
			client.createCollection("test2", params).async().get();
			// .. or via RxJava API
			client.createCollection("test3", params).rx().blockingGet();

			// Insert a new vectors
			for (int i = 0; i < 10; i++) {

				// Vector of the point
				float[] vector = new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f - i };

				// Payload of the point
				Map<String, Value> payload = new HashMap<>();
				payload.put("color", ModelHelper.value("blue"));

				// Now construct the point
				PointStruct point = ModelHelper.point(42L + i, vector, payload);
				// .. and insert it
				client.upsertPoint("test1", point, true).sync();
			}

			// Count points
			long nPoints = client.countPoints("test1", null, true).sync().getResult().getCount();

			// Now run KNN search
			float[] searchVector = new float[] { 0.43f, 0.09f, 0.41f, 1.35f };
			List<ScoredPoint> searchResults = client.searchPoints("test1", searchVector, 2, null).sync().getResultList();
			for (ScoredPoint result : searchResults) {
				System.out.println("Found: [" + result.getId().getNum() + "] " + result.getScore());
			}

			// Invoke backup via Snapshot API
			client.createSnapshot("test1").sync();
		}
	}
}
