package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionParams implements RestModel {

	private VectorParams vectors;

	@JsonProperty("shard_number")
	private Integer shardNumber;

	@JsonProperty("replication_factor")
	private Integer replicationFactor;

	@JsonProperty("write_consistency_factor")
	private Integer writeConsistencyFactor;

	@JsonProperty("on_disk_payload")
	private Boolean onDiskPayload;

	public VectorParams getVectors() {
		return vectors;
	}

	public Integer getShardNumber() {
		return shardNumber;
	}

	public Integer getReplicationFactor() {
		return replicationFactor;
	}

	public Integer getWriteConsistencyFactor() {
		return writeConsistencyFactor;
	}

	public Boolean getOnDiskPayload() {
		return onDiskPayload;
	}
}
