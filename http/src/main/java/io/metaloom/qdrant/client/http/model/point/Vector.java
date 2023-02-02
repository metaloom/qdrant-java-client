package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.VectorUtil.toArray;
import static io.metaloom.qdrant.client.util.VectorUtil.toList;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class Vector implements RestModel {

	private List<Float> components;

	public List<Float> getComponents() {
		return components;
	}

	public Vector setComponents(List<Float> components) {
		this.components = components;
		return this;
	}

	public Vector setComponents(float... components) {
		this.components = toList(components);
		return this;
	}

	public float[] array() {
		return toArray(getComponents());
	}

	public static Vector of(float... values) {
		return new Vector().setComponents(values);
	}

}
