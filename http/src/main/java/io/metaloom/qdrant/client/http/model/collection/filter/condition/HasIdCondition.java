package io.metaloom.qdrant.client.http.model.collection.filter.condition;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HasIdCondition implements Condition {

	@JsonProperty("has_id")
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public HasIdCondition setIds(List<Long> ids) {
		this.ids = ids;
		return this;
	}

	@JsonIgnore
	public HasIdCondition setIds(long... ids) {
		this.ids = toList(ids);
		return this;
	}

	public static HasIdCondition of(long... ids) {
		return new HasIdCondition().setIds(ids);
	}
}
