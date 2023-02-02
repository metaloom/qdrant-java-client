package io.metaloom.qdrant.client.http;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointStruct;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;

public class BasicUsageExampleTest extends AbstractContainerTest {

	@Test
	public void testExample() throws Exception {

		int port = qdrant.httpPort();

		try (QDrantHttpClient client = QDrantHttpClient.builder()
			.setHostname("localhost")
			.setPort(port)
			.build()) {

			// Create a collection
			CollectionCreateRequest req = new CollectionCreateRequest();
			req.setVectors("colors", 4, Distance.EUCLID);
			client.createCollection("the-collection-name", req).sync();

			// Now add some points
			PointStruct p1 = PointStruct.of("colors", 0.42f, 0.33f, 42.15f, 68.72f)
				.setPayload("{\"name\": \"first\"}")
				.setId(1);
			PointStruct p2 = PointStruct.of("colors", 0.76f, 0.43f, 63.45f, 22.10f)
				.setPayload("{ \"color\": \"red\"}")
				.setId(2);
			PointStruct p3 = PointStruct.of("colors", 0.41f, 0.32f, 42.11f, 68.71f).setId(3);
			PointStruct p4 = PointStruct.of("colors", 0.12f, 0.23f, 12.46f, 47.17f).setId(4);

			PointsListUpsertRequest pointsRequest = new PointsListUpsertRequest();
			pointsRequest.setPoints(p1, p2, p3, p4);
			client.upsertPoints("the-collection-name", pointsRequest, false).async().blockingGet();

			// List the collections
			client.listCollections().async().blockingGet();

			// Count the points in the collection
			client.countPoints("the-collection-name", new PointCountRequest().setExact(true)).sync();
		}
	}
}
