package io.metaloom.qdrant.client.http.model.collection.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionClusterInfo implements RestModel {

	@JsonProperty("peer_id")
	private int peerId;

	@JsonProperty("shard_count")
	private int shardCount;

	@JsonProperty("local_shards")
	private List<LocalShardInfo> localShards;

	@JsonProperty("remote_shards")
	private List<RemoteShardInfo> remoteShards;

	@JsonProperty("shard_transfers")
	private List<ShardTransferInfo> shardTransfers;

	public int getPeerId() {
		return peerId;
	}

	public CollectionClusterInfo setPeerId(int peerId) {
		this.peerId = peerId;
		return this;
	}

	public int getShardCount() {
		return shardCount;
	}

	public CollectionClusterInfo setShardCount(int shardCount) {
		this.shardCount = shardCount;
		return this;
	}

	public List<LocalShardInfo> getLocalShards() {
		return localShards;
	}

	public CollectionClusterInfo setLocalShards(List<LocalShardInfo> localShards) {
		this.localShards = localShards;
		return this;
	}

	public List<RemoteShardInfo> getRemoteShards() {
		return remoteShards;
	}

	public CollectionClusterInfo setRemoteShards(List<RemoteShardInfo> remoteShards) {
		this.remoteShards = remoteShards;
		return this;
	}

	public List<ShardTransferInfo> getShardTransfers() {
		return shardTransfers;
	}

	public CollectionClusterInfo setShardTransfers(List<ShardTransferInfo> shardTransfers) {
		this.shardTransfers = shardTransfers;
		return this;
	}

}
