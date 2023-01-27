package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class NamedVector implements RestModel {

	private String name;

	private List<Float> vector;

	public String getName() {
		return name;
	}

	public List<Float> getVector() {
		return vector;
	}
	
	public void setVector(List<Float> vector) {
		this.vector = vector;
	}
}
