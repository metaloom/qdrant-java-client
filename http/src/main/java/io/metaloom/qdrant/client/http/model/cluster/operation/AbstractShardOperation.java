package io.metaloom.qdrant.client.http.model.cluster.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.cluster.ShardOperation;

public abstract class AbstractShardOperation implements ShardOperation {

	@JsonProperty("shard_id")
	int shardId;

	@Override
	public int getShardId() {
		return shardId;
	}

	public AbstractShardOperation setShardId(int shardId) {
		this.shardId = shardId;
		return this;
	}

}
