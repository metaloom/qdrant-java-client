package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsGetResponse extends AbstractResponse {

	private List<Record> result;

	public List<Record> getResult() {
		return result;
	}
}
