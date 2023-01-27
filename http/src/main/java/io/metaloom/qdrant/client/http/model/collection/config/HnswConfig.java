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

	public Integer getEfConstruct() {
		return efConstruct;
	}

	public Integer getFullScanThreshold() {
		return fullScanThreshold;
	}

	public Integer getMaxIndexingThreads() {
		return maxIndexingThreads;
	}

	public Boolean getOnDisk() {
		return onDisk;
	}

	public Integer getPayloadM() {
		return payloadM;
	}
}
