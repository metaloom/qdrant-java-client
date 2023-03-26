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

	@JsonProperty("quantization_config")
	private QuantizationConfig quantizationConfig;

	public CollectionParams getParams() {
		return params;
	}

	public CollectionConfig setParams(CollectionParams params) {
		this.params = params;
		return this;
	}

	public HnswConfig getHnswConfig() {
		return hnswConfig;
	}

	public CollectionConfig setHnswConfig(HnswConfig hnswConfig) {
		this.hnswConfig = hnswConfig;
		return this;
	}

	public OptimizersConfig getOptimizerConfig() {
		return optimizerConfig;
	}

	public CollectionConfig setOptimizerConfig(OptimizersConfig optimizerConfig) {
		this.optimizerConfig = optimizerConfig;
		return this;
	}

	public WalConfig getWalConfig() {
		return walConfig;
	}

	public CollectionConfig setWalConfig(WalConfig walConfig) {
		this.walConfig = walConfig;
		return this;
	}

	public QuantizationConfig getQuantizationConfig() {
		return quantizationConfig;
	}

	public CollectionConfig setQuantizationConfig(QuantizationConfig quantizationConfig) {
		this.quantizationConfig = quantizationConfig;
		return this;
	}
}
