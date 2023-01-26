package io.metaloom.qdrant.client.http;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.json.Json;

public class QDrantHttpClientTest extends AbstractContainerTest {

	@Test
	public void testClient() throws Exception {
		QDrantHttpClient client = QDrantHttpClient.builder()
			.setScheme("http")
			.setHostname("localhost")
			.setPort(qdrant.httpPort())
			.build();

		JSONObject collection = Json.toJson("""
			{
				"name": "example_collection",
				"vectors": {
					"size": 300,
					"distance": "Cosine"
				}
			}
			""");

		// Assert initial status
		assertEquals("There should be no collections stored", 0, client.listCollections().sync().getJSONObject("result").getJSONArray("collections").length());

		// Create a collection
		client.createCollection("test", collection).sync();

		// Now load the collection
		JSONObject collections = client.listCollections().sync();
		assertEquals("test", collections.getJSONObject("result").getJSONArray("collections").getJSONObject(0).getString("name"));
		System.out.println(collections.toString());

	}
}
