package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.config.HnswConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.collection.config.WalConfigDiff;

public class CollectionCreateRequest implements RestRequestModel {

	private VectorsConfig vectors;

	@JsonProperty("shard_number")
	private Integer shardNumber;

	@JsonProperty("replication_factor")
	private Integer replicationFactor;

	@JsonProperty("write_consistency_factor")
	private Integer writeConsistencyFactor;

	@JsonProperty("on_disk_payload")
	private Boolean onDiskPayload;

	@JsonProperty("hnsw_config")
	private HnswConfigDiff hnswConfig;

	@JsonProperty("wal_config")
	private WalConfigDiff walConfig;

	@JsonProperty("optimizers_config")
	private OptimizersConfigDiff optimizersConfig;

	public VectorsConfig getVectors() {
		return vectors;
	}

	public void setVectors(VectorsConfig vectors) {
		this.vectors = vectors;
	}

	public Integer getShardNumber() {
		return shardNumber;
	}

	public void setShardNumber(Integer shardNumber) {
		this.shardNumber = shardNumber;
	}

	public Integer getWriteConsistencyFactor() {
		return writeConsistencyFactor;
	}

	public void setWriteConsistencyFactor(Integer writeConsistencyFactor) {
		this.writeConsistencyFactor = writeConsistencyFactor;
	}

	public Boolean getOnDiskPayload() {
		return onDiskPayload;
	}

	public void setOnDiskPayload(Boolean onDiskPayload) {
		this.onDiskPayload = onDiskPayload;
	}

	public HnswConfigDiff getHnswConfig() {
		return hnswConfig;
	}

	public void setHnswConfig(HnswConfigDiff hnswConfig) {
		this.hnswConfig = hnswConfig;
	}

	public WalConfigDiff getWalConfig() {
		return walConfig;
	}

	public void setWalConfig(WalConfigDiff walConfig) {
		this.walConfig = walConfig;
	}

	public OptimizersConfigDiff getOptimizersConfig() {
		return optimizersConfig;
	}

	public void setOptimizersConfig(OptimizersConfigDiff optimizersConfig) {
		this.optimizersConfig = optimizersConfig;
	}
}
