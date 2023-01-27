package io.metaloom.qdrant.client.http.model.response.point;

import io.metaloom.qdrant.client.http.model.response.AbstractResponse;
import io.metaloom.qdrant.client.http.model.point.Record;

public class PointGetResponse extends AbstractResponse {

	private Record result;

	public Record getResult() {
		return result;
	}
}
