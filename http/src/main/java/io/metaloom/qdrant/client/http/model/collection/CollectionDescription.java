package io.metaloom.qdrant.client.http.model.collection;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionDescription implements RestModel {

	private String name;

	public String getName() {
		return name;
	}

	public CollectionDescription setName(String name) {
		this.name = name;
		return this;
	}
}
