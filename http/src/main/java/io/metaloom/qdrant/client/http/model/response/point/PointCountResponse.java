package io.metaloom.qdrant.client.http.model.response.point;

import io.metaloom.qdrant.client.http.model.point.CountResult;
import io.metaloom.qdrant.client.http.model.response.AbstractResponse;

public class PointCountResponse extends AbstractResponse {

	private CountResult result;

	public CountResult getResult() {
		return result;
	}
}
