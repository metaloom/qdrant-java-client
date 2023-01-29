package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

public class VectorDataPlain implements VectorData {

	private List<Float> vector;

	public List<Float> getVector() {
		return vector;
	}

	public void setVector(List<Float> vector) {
		this.vector = vector;
	}

}
