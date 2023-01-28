package io.metaloom.qdrant.client.http.model.collection.filter;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.Condition;

public class Filter implements RestModel, Condition {

	/**
	 * At least one of those conditions should match.
	 */
	@JsonProperty("should")
	private List<? extends Condition> should;

	/**
	 * All conditions must match.
	 */
	@JsonProperty("must")
	private List<? extends Condition> must;

	/**
	 * All conditions must NOT match.
	 */
	@JsonProperty("must_not")
	private List<? extends Condition> mustNot;

	public List<? extends Condition> getShould() {
		return should;
	}

	public Filter setShould(List<? extends Condition> should) {
		this.should = should;
		return this;
	}

	public Filter setShould(Condition... should) {
		this.should = Arrays.asList(should);
		return this;
	}

	public List<? extends Condition> getMust() {
		return must;
	}

	public Filter setMust(List<? extends Condition> must) {
		this.must = must;
		return this;
	}

	public Filter setMust(Condition... must) {
		this.must = Arrays.asList(must);
		return this;
	}

	public List<? extends Condition> getMustNot() {
		return mustNot;
	}

	public Filter setMustNot(List<? extends Condition> mustNot) {
		this.mustNot = mustNot;
		return this;
	}

	public Filter setMustNot(Condition... mustNot) {
		this.mustNot = Arrays.asList(mustNot);
		return this;
	}
}
