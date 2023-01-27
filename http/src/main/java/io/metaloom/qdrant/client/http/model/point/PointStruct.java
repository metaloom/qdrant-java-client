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

	public List<Float> getVector() {
		return vector;
	}

	public Payload getPayload() {
		return payload;
	}

}
