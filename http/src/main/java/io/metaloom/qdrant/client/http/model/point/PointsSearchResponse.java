package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsSearchResponse extends AbstractResponse {

	private List<ScoredPoint> result;

	public List<ScoredPoint> getResult() {
		return result;
	}

	public PointsSearchResponse setResult(List<ScoredPoint> result) {
		this.result = result;
		return this;
	}

}
