package io.metaloom.qdrant.client.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.http.model.collection.CollectionList;
import io.metaloom.qdrant.json.Json;

public class QDrantHttpClientTest extends AbstractContainerTest {

	@Test
	public void testClient() throws Exception {
		QDrantHttpClient client = QDrantHttpClient.builder()
			.setScheme("http")
			.setHostname("localhost")
			.setPort(qdrant.httpPort())
			.build();

		JsonNode collection = Json.toJson("""
			{
				"name": "example_collection",
				"vectors": {
					"size": 300,
					"distance": "Cosine"
				}
			}
			""");

		// Assert initial status
		assertEquals("There should be no collections stored", 0, client.listCollections().sync().get("result").get("collections").size());

		// Create a collection
		client.createCollection("test", collection).sync();

		String json = client.listCollections().sync().toString();
		CollectionList list = new ObjectMapper().readValue(json, CollectionList.class);
		System.out.println(list.result.collections.get(0).name);

//		// Now load the collection
//		JSONObject collections = client.listCollections().sync();
//		assertEquals("test", collections.getJSONObject("result").getJSONArray("collections").getJSONObject(0).getString("name"));
//		System.out.println(collections.toString());

	}
}
