package io.metaloom.qdrant.client.http.model.collection.filter.condition;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HasIdCondition implements Condition {

	@JsonProperty("has_id")
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}
}
