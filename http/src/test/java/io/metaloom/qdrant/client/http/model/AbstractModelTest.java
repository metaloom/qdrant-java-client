package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.metaloom.qdrant.json.Json;

public abstract class AbstractModelTest {

	public String load(String modelName) {
		String path = "/models/" + modelName + ".json";
		try (InputStream ins = getClass().getResourceAsStream(path)) {
			if (ins == null) {
				fail("Could not find model file " + path + " in classpath.");
			}
			return new String(ins.readAllBytes(), StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T extends RestModel> T load(String modelName, Class<T> classOfT) {
		return Json.parse(load(modelName), classOfT);
	}
}
