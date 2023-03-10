package io.metaloom.qdrant.client.http.model.collection;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class CollectionResponse extends AbstractResponse {

	private CollectionInfo result;

	public CollectionInfo getResult() {
		return result;
	}

	public CollectionResponse setResult(CollectionInfo result) {
		this.result = result;
		return this;
	}
}
