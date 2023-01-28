package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class HnswConfig implements RestModel {

	private Integer m;

	@JsonProperty("ef_construct")
	private Integer efConstruct;

	@JsonProperty("full_scan_threshold")
	private Integer fullScanThreshold;

	@JsonProperty("max_indexing_threads")
	private Integer maxIndexingThreads;

	@JsonProperty("on_disk")
	private Boolean onDisk;

	@JsonProperty("payload_m")
	private Integer payloadM;

	public Integer getM() {
		return m;
	}

	public HnswConfig setM(Integer m) {
		this.m = m;
		return this;
	}

	public Integer getEfConstruct() {
		return efConstruct;
	}

	public HnswConfig setEfConstruct(Integer efConstruct) {
		this.efConstruct = efConstruct;
		return this;
	}

	public Integer getFullScanThreshold() {
		return fullScanThreshold;
	}

	public HnswConfig setFullScanThreshold(Integer fullScanThreshold) {
		this.fullScanThreshold = fullScanThreshold;
		return this;
	}

	public Integer getMaxIndexingThreads() {
		return maxIndexingThreads;
	}

	public HnswConfig setMaxIndexingThreads(Integer maxIndexingThreads) {
		this.maxIndexingThreads = maxIndexingThreads;
		return this;
	}

	public Boolean getOnDisk() {
		return onDisk;
	}

	public HnswConfig setOnDisk(Boolean onDisk) {
		this.onDisk = onDisk;
		return this;
	}

	public Integer getPayloadM() {
		return payloadM;
	}

	public HnswConfig setPayloadM(Integer payloadM) {
		this.payloadM = payloadM;
		return this;
	}
}
