package io.metaloom.qdrant.client.http.model.collection.filter.condition;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.point.PointId;

public class HasIdCondition implements Condition {

	@JsonProperty("has_id")
	private List<PointId> ids;

	public List<PointId> getIds() {
		return ids;
	}

	public HasIdCondition setIds(List<PointId> ids) {
		this.ids = ids;
		return this;
	}

	@JsonIgnore
	public HasIdCondition setIds(long... ids) {
		this.ids = PointId.list(ids);
		return this;
	}

	public static HasIdCondition of(long... ids) {
		return new HasIdCondition().setIds(ids);
	}
}
