package io.metaloom.qdrant.client.http.model;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionClusterInfo;

public class ClusterInfoResponse {
	float time;
	String status;
	CollectionClusterInfo result;
}
