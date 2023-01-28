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

	public Range setGreaterThan(Double greaterThan) {
		this.greaterThan = greaterThan;
		return this;
	}

	public Double getGreaterThanEqual() {
		return greaterThanEqual;
	}

	public Range setGreaterThanEqual(Double greaterThanEqual) {
		this.greaterThanEqual = greaterThanEqual;
		return this;
	}

	public Double getLessThan() {
		return lessThan;
	}

	public Range setLessThan(Double lessThan) {
		this.lessThan = lessThan;
		return this;
	}

	public Double getLessThanEqual() {
		return lessThanEqual;
	}

	public Range setLessThanEqual(Double lessThanEqual) {
		this.lessThanEqual = lessThanEqual;
		return this;
	}
}
