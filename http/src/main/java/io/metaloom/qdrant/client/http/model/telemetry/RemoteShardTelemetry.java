package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class RemoteShardTelemetry implements RestModel {

	@JsonProperty("shard_id")
	private int shardId;

	@JsonProperty("peer_id")
	private Long peerId;

	private OperationDurationStatistics searches;

	private OperationDurationStatistics updates;

	public int getShardId() {
		return shardId;
	}

	public RemoteShardTelemetry setShardId(int shardId) {
		this.shardId = shardId;
		return this;
	}

	public Long getPeerId() {
		return peerId;
	}

	public RemoteShardTelemetry setPeerId(Long peerId) {
		this.peerId = peerId;
		return this;
	}

	public OperationDurationStatistics getSearches() {
		return searches;
	}

	public RemoteShardTelemetry setSearches(OperationDurationStatistics searches) {
		this.searches = searches;
		return this;
	}

	public OperationDurationStatistics getUpdates() {
		return updates;
	}

	public RemoteShardTelemetry setUpdates(OperationDurationStatistics updates) {
		this.updates = updates;
		return this;
	}

}
