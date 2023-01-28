package io.metaloom.qdrant.client.http.model.collection.config;

import io.metaloom.qdrant.client.http.model.RestModel;

public class VectorParams implements RestModel {

	private long size;

	private Distance distance;

	public long getSize() {
		return size;
	}

	public VectorParams setSize(long size) {
		this.size = size;
		return this;
	}

	public Distance getDistance() {
		return distance;
	}

	public VectorParams setDistance(Distance distance) {
		this.distance = distance;
		return this;
	}
}
