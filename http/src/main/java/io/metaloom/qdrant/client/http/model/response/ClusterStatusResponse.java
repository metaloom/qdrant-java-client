package io.metaloom.qdrant.client.http.model.response;

import io.metaloom.qdrant.client.http.model.cluster.ClusterStatus;

public class ClusterStatusResponse extends AbstractResponse {

	private ClusterStatus result;

	public ClusterStatus getResult() {
		return result;
	}
}