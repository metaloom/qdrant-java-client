package io.metaloom.qdrant.client.http.model.collection.config;

import java.util.HashMap;

public class NamedVectorParams extends HashMap<String, VectorParams> implements VectorsConfig {

	private static final long serialVersionUID = -825442098982330992L;

	public static NamedVectorParams of(String name, int size, Distance distance) {
		NamedVectorParams params = new NamedVectorParams();
		params.put(name, VectorParams.of(size, distance));
		return params;
	}

}
