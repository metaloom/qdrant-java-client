package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointGetResponse extends AbstractResponse {

	private Record result;

	public Record getResult() {
		return result;
	}

	public PointGetResponse setResult(Record result) {
		this.result = result;
		return this;
	}
}
