package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.cluster.ReplicaState;

public class RemoteShardInfo implements RestModel {

	@JsonProperty("shard_id")
	private int shardId;

	@JsonProperty("peer_id")
	private long peerId;

	@JsonProperty("state")
	private ReplicaState state;

	public int getShardId() {
		return shardId;
	}

	public long getPeerId() {
		return peerId;
	}

	public ReplicaState getState() {
		return state;
	}

}
