package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsScrollResponse extends AbstractResponse {

	private ScrollResult result;

	public ScrollResult getResult() {
		return result;
	}

}
