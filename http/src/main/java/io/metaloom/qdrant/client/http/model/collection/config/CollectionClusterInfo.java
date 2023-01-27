package io.metaloom.qdrant.client.http.model.collection.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionClusterInfo {

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

	public int getShardCount() {
		return shardCount;
	}

	public List<LocalShardInfo> getLocalShards() {
		return localShards;
	}

	public List<RemoteShardInfo> getRemoteShards() {
		return remoteShards;
	}

	public List<ShardTransferInfo> getShardTransfers() {
		return shardTransfers;
	}

}
