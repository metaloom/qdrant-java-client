package io.metaloom.qdrant.client.http.model.collection.config;

public class VectorsConfig {

	private int size;

	private Distance distance;

	public int getSize() {
		return size;
	}

	public VectorsConfig setSize(int size) {
		this.size = size;
		return this;
	}

	public Distance getDistance() {
		return distance;
	}

	public VectorsConfig setDistance(Distance distance) {
		this.distance = distance;
		return this;
	}

}
