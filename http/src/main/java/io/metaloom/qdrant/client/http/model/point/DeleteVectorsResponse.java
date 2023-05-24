package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class DeleteVectorsResponse extends AbstractResponse {

	private UpdateResult result;

	public UpdateResult getResult() {
		return result;
	}

	public DeleteVectorsResponse setResult(UpdateResult result) {
		this.result = result;
		return this;
	}

}
