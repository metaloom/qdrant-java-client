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

	public long getFrom() {
		return from;
	}

	public long getTo() {
		return to;
	}

	public boolean isSync() {
		return sync;
	}

}
