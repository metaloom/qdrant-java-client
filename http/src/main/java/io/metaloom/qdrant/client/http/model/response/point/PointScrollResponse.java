package io.metaloom.qdrant.client.http.model.response.point;

import io.metaloom.qdrant.client.http.model.point.ScrollResult;
import io.metaloom.qdrant.client.http.model.response.AbstractResponse;

public class PointScrollResponse extends AbstractResponse {

	private ScrollResult result;

	public ScrollResult getResult() {
		return result;
	}

}
