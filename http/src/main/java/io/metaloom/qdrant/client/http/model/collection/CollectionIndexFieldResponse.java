package io.metaloom.qdrant.client.http.model.collection;

import io.metaloom.qdrant.client.http.model.AbstractResponse;
import io.metaloom.qdrant.client.http.model.point.UpdateResult;

public class CollectionIndexFieldResponse extends AbstractResponse {

	private UpdateResult result;

	public UpdateResult getResult() {
		return result;
	}

	public void setResult(UpdateResult result) {
		this.result = result;
	}
}
