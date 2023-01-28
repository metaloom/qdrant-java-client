package io.metaloom.qdrant.client.http.model.collection.config;

public class VectorParams implements VectorsConfig {

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

	public static VectorParams of(long size, Distance distance) {
		return new VectorParams().setSize(size).setDistance(distance);
	}
}
