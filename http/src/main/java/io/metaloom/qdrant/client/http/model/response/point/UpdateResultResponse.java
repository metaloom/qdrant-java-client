package io.metaloom.qdrant.client.http.model.response.point;

import io.metaloom.qdrant.client.http.model.point.UpdateResult;
import io.metaloom.qdrant.client.http.model.response.AbstractResponse;

public class UpdateResultResponse extends AbstractResponse {

	private UpdateResult result;

	public UpdateResult getResult() {
		return result;
	}
}
