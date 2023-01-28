package io.metaloom.qdrant.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.point.Vector;
import io.metaloom.qdrant.client.json.Json;

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
	}

	@Test
	public void testVector() {
		Vector vector = Vector.of(52.0f, 12f);
		String json = Json.parse(vector);
		assertEquals("[ 52.0, 12.0 ]", json);

		Vector vector2 = Json.parse(json, Vector.class);
		assertEquals(52.0f, vector2.array()[0], 0f);

		assertTrue("The components should be empty.", Json.parse("[]", Vector.class).getComponents().isEmpty());
		assertTrue("The components should be empty.", Json.parse("[null]", Vector.class).getComponents().isEmpty());
	}

}
