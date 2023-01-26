package io.metaloom.qdrant.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Test;

public class JsonTest {

	@Test
	public void testJson() throws Exception {
		JSONObject collection = Json.toJson("""
			{
				"name": "example_collection",
				"vectors": {
					"size": 300,
					"distance": "Cosine"
				}
			}
			""");

		JSONObject json = Json.toJson(collection.toString());
		assertNotNull(json);

		assertEquals("example_collection", json.getString("name"));
		JSONObject vectorConfig = json.getJSONObject("vectors");
		assertEquals(300, vectorConfig.getInt("size"));
		assertEquals("Cosine", vectorConfig.getString("distance"));

		System.out.println(json.toString());

	}
}
