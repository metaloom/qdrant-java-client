package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class PointStruct implements RestModel {

	private int id;
	private List<Float> vector;
	private Payload payload;

	public int getId() {
		return id;
	}

	public PointStruct setId(int id) {
		this.id = id;
		return this;
	}

	public List<Float> getVector() {
		return vector;
	}

	public PointStruct setVector(List<Float> vector) {
		this.vector = vector;
		return this;
	}

	public Payload getPayload() {
		return payload;
	}

	public PointStruct setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

}
