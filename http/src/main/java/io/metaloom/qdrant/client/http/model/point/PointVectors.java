package io.metaloom.qdrant.client.http.model.point;

public class PointVectors {

	private PointId id;

	private VectorData vector;

	public PointId getId() {
		return id;
	}

	public PointVectors setId(PointId id) {
		this.id = id;
		return this;
	}

	public VectorData getVector() {
		return vector;
	}

	public PointVectors setVector(VectorData vector) {
		this.vector = vector;
		return this;
	}
}
