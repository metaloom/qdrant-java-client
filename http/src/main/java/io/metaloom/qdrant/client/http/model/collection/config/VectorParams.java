package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VectorParams implements VectorsConfig {

	private long size;

	private Distance distance;

	@JsonProperty("hnsw_config")
	private HnswConfigDiff hnswConfig;

	@JsonProperty("quantization_config")
	private QuantizationConfig quantizationConfig;

	@JsonProperty("on_disk")
	private Boolean onDisk;

	public long getSize() {
		return size;
	}

	public VectorParams setSize(long size) {
		this.size = size;
		return this;
	}

	public Distance getDistance() {
		return distance;
	}

	public VectorParams setDistance(Distance distance) {
		this.distance = distance;
		return this;
	}

	public static VectorParams of(long size, Distance distance) {
		return new VectorParams().setSize(size).setDistance(distance);
	}

	public HnswConfigDiff getHnswConfig() {
		return hnswConfig;
	}

	public void setHnswConfig(HnswConfigDiff hnswConfig) {
		this.hnswConfig = hnswConfig;
	}

	public QuantizationConfig getQuantizationConfig() {
		return quantizationConfig;
	}

	public VectorParams setQuantizationConfig(QuantizationConfig quantizationConfig) {
		this.quantizationConfig = quantizationConfig;
		return this;
	}

	public Boolean getOnDisk() {
		return onDisk;
	}

	public VectorParams setOnDisk(Boolean onDisk) {
		this.onDisk = onDisk;
		return this;
	}
}
