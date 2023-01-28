package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ShardTransferInfo implements RestModel {

	@JsonProperty("shard_id")
	private int shardId;
	private long from;
	private long to;
	private boolean sync;

	public int getShardId() {
		return shardId;
	}

	public ShardTransferInfo setShardId(int shardId) {
		this.shardId = shardId;
		return this;
	}

	public long getFrom() {
		return from;
	}

	public ShardTransferInfo setFrom(long from) {
		this.from = from;
		return this;
	}

	public long getTo() {
		return to;
	}

	public ShardTransferInfo setTo(long to) {
		this.to = to;
		return this;
	}

	public boolean isSync() {
		return sync;
	}

	public ShardTransferInfo setSync(boolean sync) {
		this.sync = sync;
		return this;
	}

}
