package io.metaloom.qdrant.client.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionListResponse;

public class QDrantHttpClientTest extends AbstractContainerTest {

	@Test
	public void testClient() throws Exception {
		QDrantHttpClient client = QDrantHttpClient.builder()
			.setScheme("http")
			.setHostname("localhost")
			.setPort(qdrant.httpPort())
			.build();

		CollectionCreateRequest req = new CollectionCreateRequest();
		// Assert initial status
		assertEquals("There should be no collections stored", 0, client.listCollections().sync().getResult().getCollections().size());

		// Create a collection
		client.createCollection("test", req).sync();

		CollectionListResponse list = client.listCollections().sync();
		System.out.println(list.getResult().getCollections().get(0).getName());

		// // Now load the collection
		// JSONObject collections = client.listCollections().sync();
		// assertEquals("test", collections.getJSONObject("result").getJSONArray("collections").getJSONObject(0).getString("name"));
		// System.out.println(collections.toString());

	}
}
