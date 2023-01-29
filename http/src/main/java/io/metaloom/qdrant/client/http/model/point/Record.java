package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class Record implements RestModel {

	@JsonProperty("id")
	private PointId id;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("vector")
	private VectorData vector;

	public PointId getId() {
		return id;
	}

	public Record setId(PointId id) {
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

	public VectorData getVector() {
		return vector;
	}

	public Record setVector(VectorData vector) {
		this.vector = vector;
		return this;
	}
}
