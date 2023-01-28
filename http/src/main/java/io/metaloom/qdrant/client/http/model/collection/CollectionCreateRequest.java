package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.collection.config.HnswConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.NamedVectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
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

	public CollectionCreateRequest setVectors(VectorsConfig vectors) {
		this.vectors = vectors;
		return this;
	}

	public CollectionCreateRequest setVectors(int size, Distance distance) {
		return setVectors(new VectorParams().setSize(size).setDistance(distance));
	}

	/**
	 * Set a named vector parameter to the create request.
	 * 
	 * @param name
	 *            Name of the configuration
	 * @param size
	 * @param distance
	 * @return parameters object to which more configurations can be added
	 */
	public NamedVectorParams setVectors(String name, int size, Distance distance) {
		NamedVectorParams params = NamedVectorParams.of(name, size, distance);
		setVectors(params);
		return params;
	}

	public Integer getShardNumber() {
		return shardNumber;
	}

	public CollectionCreateRequest setShardNumber(Integer shardNumber) {
		this.shardNumber = shardNumber;
		return this;
	}

	public Integer getWriteConsistencyFactor() {
		return writeConsistencyFactor;
	}

	public CollectionCreateRequest setWriteConsistencyFactor(Integer writeConsistencyFactor) {
		this.writeConsistencyFactor = writeConsistencyFactor;
		return this;
	}

	public Boolean getOnDiskPayload() {
		return onDiskPayload;
	}

	public CollectionCreateRequest setOnDiskPayload(Boolean onDiskPayload) {
		this.onDiskPayload = onDiskPayload;
		return this;
	}

	public HnswConfigDiff getHnswConfig() {
		return hnswConfig;
	}

	public CollectionCreateRequest setHnswConfig(HnswConfigDiff hnswConfig) {
		this.hnswConfig = hnswConfig;
		return this;
	}

	public WalConfigDiff getWalConfig() {
		return walConfig;
	}

	public CollectionCreateRequest setWalConfig(WalConfigDiff walConfig) {
		this.walConfig = walConfig;
		return this;
	}

	public OptimizersConfigDiff getOptimizersConfig() {
		return optimizersConfig;
	}

	public CollectionCreateRequest setOptimizersConfig(OptimizersConfigDiff optimizersConfig) {
		this.optimizersConfig = optimizersConfig;
		return this;
	}

}
