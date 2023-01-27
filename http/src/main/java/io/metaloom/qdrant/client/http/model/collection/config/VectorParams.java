package io.metaloom.qdrant.client.http.model.collection.config;

import io.metaloom.qdrant.client.http.model.RestModel;

public class VectorParams implements RestModel {

	private long size;

	private Distance distance;

	public long getSize() {
		return size;
	}

	public Distance getDistance() {
		return distance;
	}
}
