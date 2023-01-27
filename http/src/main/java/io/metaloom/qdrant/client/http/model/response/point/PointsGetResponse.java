package io.metaloom.qdrant.client.http.model.response.point;

import java.util.List;
import io.metaloom.qdrant.client.http.model.response.AbstractResponse;
import io.metaloom.qdrant.client.http.model.point.Record;

public class PointsGetResponse extends AbstractResponse {

	private List<Record> result;

	public List<Record> getResult() {
		return result;
	}
}
