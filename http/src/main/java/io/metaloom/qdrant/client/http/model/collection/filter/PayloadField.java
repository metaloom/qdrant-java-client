package io.metaloom.qdrant.client.http.model.collection.filter;

import io.metaloom.qdrant.client.http.model.RestModel;

public class PayloadField implements RestModel {

	private String key;

	public String getKey() {
		return key;
	}

	public PayloadField setKey(String key) {
		this.key = key;
		return this;
	}
}
