package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionConfig implements RestModel {

	private CollectionParams params;

	@JsonProperty("hnsw_config")
	private HnswConfig hnswConfig;

	@JsonProperty("optimizer_config")
	private OptimizersConfig optimizerConfig;

	@JsonProperty("wal_config")
	private WalConfig walConfig;

	public CollectionParams getParams() {
		return params;
	}

	public HnswConfig getHnswConfig() {
		return hnswConfig;
	}

	public OptimizersConfig getOptimizerConfig() {
		return optimizerConfig;
	}

	public WalConfig getWalConfig() {
		return walConfig;
	}
}
