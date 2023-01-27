package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class Record implements RestModel {

	private Long id;
	private Payload payload;
	private List<Float> vector;

	public Long getId() {
		return id;
	}

	public Payload getPayload() {
		return payload;
	}

	public List<Float> getVector() {
		return vector;
	}

}
