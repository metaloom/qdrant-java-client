package io.metaloom.qdrant.client.http.model.cluster;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class ClusterStatusResponse extends AbstractResponse {

	private ClusterStatus result;

	public ClusterStatus getResult() {
		return result;
	}
	
	public ClusterStatusResponse setResult(ClusterStatus result) {
		this.result = result;
		return this;
	}
}
