package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;
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

	public long getInitTimeMs() {
		return initTimeMs;
	}

	public CollectionConfig getConfig() {
		return config;
	}

	public List<ReplicaSetTelemetry> getShards() {
		return shards;
	}

	public List<ShardTransferInfo> getTransfers() {
		return transfers;
	}

}
