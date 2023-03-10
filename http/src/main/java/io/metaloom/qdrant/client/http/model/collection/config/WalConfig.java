package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class WalConfig implements RestModel {

	@JsonProperty("wal_capacity_mb")
	private Integer walCapacityMB;

	@JsonProperty("wal_segments_ahead")
	private Integer walSegmentsAhead;

	public Integer getWalCapacityMB() {
		return walCapacityMB;
	}

	public WalConfig setWalCapacityMB(Integer walCapacityMB) {
		this.walCapacityMB = walCapacityMB;
		return this;
	}

	public Integer getWalSegmentsAhead() {
		return walSegmentsAhead;
	}

	public WalConfig setWalSegmentsAhead(Integer walSegmentsAhead) {
		this.walSegmentsAhead = walSegmentsAhead;
		return this;
	}

}
