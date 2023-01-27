package io.metaloom.qdrant.client.http.model.collection.filter;

import io.metaloom.qdrant.client.http.model.RestModel;

public class GeoRadius implements RestModel {

	private GeoPoint center;
	private double radius;

	public GeoPoint getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

}
