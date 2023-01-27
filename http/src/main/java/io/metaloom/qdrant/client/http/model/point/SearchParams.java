package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class SearchParams implements RestModel {

	@JsonProperty("hnsw_ef")
	private Integer hnswBeamSearchSize;

	private Boolean exact;

	public Integer getHnswBeamSearchSize() {
		return hnswBeamSearchSize;
	}

	public Boolean getExact() {
		return exact;
	}
}
