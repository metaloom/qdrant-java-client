package io.metaloom.qdrant.client.http.model.collection.filter;

import io.metaloom.qdrant.client.http.model.RestModel;

public class GeoRadius implements RestModel {

	private GeoPoint center;
	private double radius;

	public GeoPoint getCenter() {
		return center;
	}

	public GeoRadius setCenter(GeoPoint center) {
		this.center = center;
		return this;
	}

	public double getRadius() {
		return radius;
	}

	public GeoRadius setRadius(double radius) {
		this.radius = radius;
		return this;
	}

}
