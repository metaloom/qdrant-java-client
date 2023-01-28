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

	public RemoteShardInfo setShardId(int shardId) {
		this.shardId = shardId;
		return this;
	}

	public long getPeerId() {
		return peerId;
	}

	public RemoteShardInfo setPeerId(long peerId) {
		this.peerId = peerId;
		return this;
	}

	public ReplicaState getState() {
		return state;
	}

	public RemoteShardInfo setState(ReplicaState state) {
		this.state = state;
		return this;
	}

}
