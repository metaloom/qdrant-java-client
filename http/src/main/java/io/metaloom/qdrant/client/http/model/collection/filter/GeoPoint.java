package io.metaloom.qdrant.client.http.model.collection.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class GeoPoint implements RestModel {

	@JsonProperty("lon")
	private double longitude;

	@JsonProperty("lat")
	private double latitude;

	public double getLatitude() {
		return latitude;
	}

	public GeoPoint setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public double getLongitude() {
		return longitude;
	}

	public GeoPoint setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}

}
