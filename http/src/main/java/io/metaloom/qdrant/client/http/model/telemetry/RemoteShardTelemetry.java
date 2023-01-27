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

	public Long getPeerId() {
		return peerId;
	}

	public OperationDurationStatistics getSearches() {
		return searches;
	}

	public OperationDurationStatistics getUpdates() {
		return updates;
	}

}
