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

	public LocalShardInfo setShardId(int shardId) {
		this.shardId = shardId;
		return this;
	}

	public int getPointsCount() {
		return pointsCount;
	}

	public LocalShardInfo setPointsCount(int pointsCount) {
		this.pointsCount = pointsCount;
		return this;
	}

	public ReplicaState getState() {
		return state;
	}

	public LocalShardInfo setState(ReplicaState state) {
		this.state = state;
		return this;
	}

}
