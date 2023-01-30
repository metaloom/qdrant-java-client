package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class PointsSearchBatchRequest implements RestRequestModel {

	List<PointsSearchRequest> searches;

	public List<PointsSearchRequest> getSearches() {
		return searches;
	}

	public PointsSearchBatchRequest setSearches(List<PointsSearchRequest> searches) {
		this.searches = searches;
		return this;
	}

}
