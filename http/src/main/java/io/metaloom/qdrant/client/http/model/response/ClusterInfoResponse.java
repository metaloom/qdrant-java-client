package io.metaloom.qdrant.client.http.model.response;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionClusterInfo;

public class ClusterInfoResponse {
	float time;
	String status;
	CollectionClusterInfo result;
}
