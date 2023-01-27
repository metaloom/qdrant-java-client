package io.metaloom.qdrant.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonTest {

	@Test
	public void testJson() throws Exception {
		JsonNode collection = Json.toJson("""
			{
				"name": "example_collection",
				"vectors": {
					"size": 300,
					"distance": "Cosine"
				}
			}
			""");

		JsonNode json = Json.toJson(collection.toString());
		assertNotNull(json);

		assertEquals("example_collection", json.get("name").asText());
		JsonNode vectorConfig = json.get("vectors");
		assertEquals(300, vectorConfig.get("size").asInt());
		assertEquals("Cosine", vectorConfig.get("distance").asText());

		System.out.println(json.toString());

	}
}
