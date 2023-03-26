package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ScoredPoint implements RestModel {

	private PointId id;
	private long version;
	private float score;
	private Payload payload;
	private Vector vector;

	public PointId getId() {
		return id;
	}

	public ScoredPoint setId(PointId id) {
		this.id = id;
		return this;
	}

	public long getVersion() {
		return version;
	}

	public ScoredPoint setVersion(long version) {
		this.version = version;
		return this;
	}

	public float getScore() {
		return score;
	}

	public ScoredPoint setScore(float score) {
		this.score = score;
		return this;
	}

	public Payload getPayload() {
		return payload;
	}

	public ScoredPoint setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	public Vector getVector() {
		return vector;
	}

	public ScoredPoint setVector(Vector vector) {
		this.vector = vector;
		return this;
	}
}
