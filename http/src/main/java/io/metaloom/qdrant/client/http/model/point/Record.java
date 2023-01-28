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
	
	public Record setId(Long id) {
		this.id = id;
		return this;
	}

	public Payload getPayload() {
		return payload;
	}
	
	public Record setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	public List<Float> getVector() {
		return vector;
	}

	public Record setVector(List<Float> vector) {
		this.vector = vector;
		return this;
	}
}
