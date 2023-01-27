package io.metaloom.qdrant.client.http.model.collection.filter.condition;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.collection.filter.PayloadField;

public class IsEmptyCondition implements Condition {

	@JsonProperty("is_empty")
	private PayloadField isEmpty;

	public PayloadField getIsEmpty() {
		return isEmpty;
	}
}
