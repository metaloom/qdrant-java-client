package io.metaloom.qdrant.client.http.model.collection.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class GeoBoundingBox implements RestModel {

	@JsonProperty("top_left")
	private GeoPoint topLeft;

	@JsonProperty("bottom_right")
	private GeoPoint bottomRight;

	public GeoPoint getTopLeft() {
		return topLeft;
	}

	public GeoPoint getBottomRight() {
		return bottomRight;
	}

}
