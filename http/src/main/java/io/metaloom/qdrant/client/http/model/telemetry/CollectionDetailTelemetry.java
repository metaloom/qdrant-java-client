package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionConfig;
import io.metaloom.qdrant.client.http.model.collection.config.ShardTransferInfo;

public class CollectionDetailTelemetry implements CollectionTelemetry {

	private String id;

	@JsonProperty("init_time_ms")
	private long initTimeMs;

	private CollectionConfig config;

	private List<ReplicaSetTelemetry> shards;

	private List<ShardTransferInfo> transfers;

	public String getId() {
		return id;
	}

	public CollectionDetailTelemetry setId(String id) {
		this.id = id;
		return this;
	}

	public long getInitTimeMs() {
		return initTimeMs;
	}

	public CollectionDetailTelemetry setInitTimeMs(long initTimeMs) {
		this.initTimeMs = initTimeMs;
		return this;
	}

	public CollectionConfig getConfig() {
		return config;
	}

	public CollectionDetailTelemetry setConfig(CollectionConfig config) {
		this.config = config;
		return this;
	}

	public List<ReplicaSetTelemetry> getShards() {
		return shards;
	}

	public CollectionDetailTelemetry setShards(List<ReplicaSetTelemetry> shards) {
		this.shards = shards;
		return this;
	}

	public List<ShardTransferInfo> getTransfers() {
		return transfers;
	}

	public CollectionDetailTelemetry setTransfers(List<ShardTransferInfo> transfers) {
		this.transfers = transfers;
		return this;
	}

}
