package io.metaloom.qdrant.client.http.model.cluster;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ClusterStatus implements RestModel {

	private String status;

	public String getStatus() {
		return status;
	}

	public ClusterStatus setStatus(String status) {
		this.status = status;
		return this;
	}
}
