package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class PointsUpsertRequest implements RestRequestModel {

	private PointsBatch batch;

	private List<PointStruct> points;

	public PointsBatch getBatch() {
		return batch;
	}

	public PointsUpsertRequest setBatch(PointsBatch batch) {
		this.batch = batch;
		return this;
	}

	public List<PointStruct> getPoints() {
		return points;
	}

	public PointsUpsertRequest setPoints(List<PointStruct> points) {
		this.points = points;
		return this;
	}

}
