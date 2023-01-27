package io.metaloom.qdrant.client.http.model.cluster;

import io.metaloom.qdrant.client.http.model.RestModel;

public interface ShardOperation extends RestModel {

	int getShardId();

}
