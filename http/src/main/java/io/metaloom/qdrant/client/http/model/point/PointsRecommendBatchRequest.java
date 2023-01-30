package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class PointsRecommendBatchRequest implements RestRequestModel {

	private List<PointsRecommendRequest> searches;

	public List<PointsRecommendRequest> getSearches() {
		return searches;
	}

	public PointsRecommendBatchRequest setSearches(List<PointsRecommendRequest> searches) {
		this.searches = searches;
		return this;
	}
}
