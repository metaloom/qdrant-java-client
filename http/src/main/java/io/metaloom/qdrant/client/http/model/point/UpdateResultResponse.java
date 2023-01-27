package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class UpdateResultResponse extends AbstractResponse {

	private UpdateResult result;

	public UpdateResult getResult() {
		return result;
	}
}
