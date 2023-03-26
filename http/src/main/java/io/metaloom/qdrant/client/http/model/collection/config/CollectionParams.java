package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionParams implements RestModel {

	private VectorsConfig vectors;

	@JsonProperty("shard_number")
	private Integer shardNumber;

	@JsonProperty("replication_factor")
	private Integer replicationFactor;

	@JsonProperty("write_consistency_factor")
	private Integer writeConsistencyFactor;

	@JsonProperty("on_disk_payload")
	private Boolean onDiskPayload;

	public VectorsConfig getVectors() {
		return vectors;
	}

	public CollectionParams setVectors(VectorsConfig vectors) {
		this.vectors = vectors;
		return this;
	}

	public Integer getShardNumber() {
		return shardNumber;
	}

	public CollectionParams setShardNumber(Integer shardNumber) {
		this.shardNumber = shardNumber;
		return this;
	}

	public Integer getReplicationFactor() {
		return replicationFactor;
	}

	public CollectionParams setReplicationFactor(Integer replicationFactor) {
		this.replicationFactor = replicationFactor;
		return this;
	}

	public Integer getWriteConsistencyFactor() {
		return writeConsistencyFactor;
	}

	public CollectionParams setWriteConsistencyFactor(Integer writeConsistencyFactor) {
		this.writeConsistencyFactor = writeConsistencyFactor;
		return this;
	}

	public Boolean getOnDiskPayload() {
		return onDiskPayload;
	}

	public CollectionParams setOnDiskPayload(Boolean onDiskPayload) {
		this.onDiskPayload = onDiskPayload;
		return this;
	}
}
