package io.metaloom.qdrant.client.http.model.collection.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ValuesCount implements RestModel {

	@JsonProperty("lt")
	private Integer lessThan;

	@JsonProperty("gt")
	private Integer greaterThan;

	@JsonProperty("gte")
	private Integer greaterThanEqual;

	@JsonProperty("lte")
	private Integer lessThanEqual;

	public Integer getGreaterThan() {
		return greaterThan;
	}

	public ValuesCount setGreaterThan(Integer greaterThan) {
		this.greaterThan = greaterThan;
		return this;
	}

	public Integer getGreaterThanEqual() {
		return greaterThanEqual;
	}

	public ValuesCount setGreaterThanEqual(Integer greaterThanEqual) {
		this.greaterThanEqual = greaterThanEqual;
		return this;
	}

	public Integer getLessThan() {
		return lessThan;
	}

	public ValuesCount setLessThan(Integer lessThan) {
		this.lessThan = lessThan;
		return this;
	}

	public Integer getLessThanEqual() {
		return lessThanEqual;
	}

	public ValuesCount setLessThanEqual(Integer lessThanEqual) {
		this.lessThanEqual = lessThanEqual;
		return this;
	}

}
