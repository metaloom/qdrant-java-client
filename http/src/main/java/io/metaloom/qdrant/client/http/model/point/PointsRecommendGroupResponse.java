package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsRecommendGroupResponse extends AbstractResponse {

	private GroupsResult result;

	public GroupsResult getResult() {
		return result;
	}

	public PointsRecommendGroupResponse setResult(GroupsResult result) {
		this.result = result;
		return this;
	}
}
