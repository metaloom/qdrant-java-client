package io.metaloom.qdrant.client.http.model.collection.filter;

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

	public List<? extends Condition> getMust() {
		return must;
	}

	public List<? extends Condition> getMustNot() {
		return mustNot;
	}
}
