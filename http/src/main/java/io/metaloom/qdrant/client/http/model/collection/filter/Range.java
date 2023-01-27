package io.metaloom.qdrant.client.http.model.collection.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class Range implements RestModel {

	@JsonProperty("lt")
	private Double lessThan;

	@JsonProperty("gt")
	private Double greaterThan;

	@JsonProperty("gte")
	private Double greaterThanEqual;

	@JsonProperty("lte")
	private Double lessThanEqual;

	public Double getGreaterThan() {
		return greaterThan;
	}

	public Double getGreaterThanEqual() {
		return greaterThanEqual;
	}

	public Double getLessThan() {
		return lessThan;
	}

	public Double getLessThanEqual() {
		return lessThanEqual;
	}

}
