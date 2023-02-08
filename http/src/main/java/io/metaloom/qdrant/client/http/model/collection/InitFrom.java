package io.metaloom.qdrant.client.http.model.collection;

import io.metaloom.qdrant.client.http.model.RestModel;

public class InitFrom implements RestModel {

	private String collection;

	public String getCollection() {
		return collection;
	}

	public InitFrom setCollection(String collection) {
		this.collection = collection;
		return this;
	}

}
