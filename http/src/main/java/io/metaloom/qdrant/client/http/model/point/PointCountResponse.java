package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointCountResponse extends AbstractResponse {

	private CountResult result;

	public CountResult getResult() {
		return result;
	}
}
