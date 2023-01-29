package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.RestModel;

public class LookupLocation implements RestModel {

	private String collection;

	private String vector;

	public String getCollection() {
		return collection;
	}

	public LookupLocation setCollection(String collection) {
		this.collection = collection;
		return this;
	}

	public String getVector() {
		return vector;
	}

	public LookupLocation setVector(String vector) {
		this.vector = vector;
		return this;
	}
}
