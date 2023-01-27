package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequest;
import io.metaloom.qdrant.client.http.model.collection.config.HnswConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.collection.config.WalConfigDiff;

public class CollectionCreateRequest implements RestRequest {

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

	public Integer getShardNumber() {
		return shardNumber;
	}

	public Integer getWriteConsistencyFactor() {
		return writeConsistencyFactor;
	}

	public Boolean getOnDiskPayload() {
		return onDiskPayload;
	}

	public HnswConfigDiff getHnswConfig() {
		return hnswConfig;
	}

	public WalConfigDiff getWalConfig() {
		return walConfig;
	}

	public OptimizersConfigDiff getOptimizersConfig() {
		return optimizersConfig;
	}

}
