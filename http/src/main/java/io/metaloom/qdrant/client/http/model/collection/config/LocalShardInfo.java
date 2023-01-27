package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.cluster.ReplicaState;

public class LocalShardInfo implements RestModel {

	@JsonProperty("shard_id")
	private int shardId;

	@JsonProperty("points_count")
	private int pointsCount;

	@JsonProperty("state")
	private ReplicaState state;

	public int getShardId() {
		return shardId;
	}

	public int getPointsCount() {
		return pointsCount;
	}

	public ReplicaState getState() {
		return state;
	}

}
