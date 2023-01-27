package io.metaloom.qdrant.client.http.model.snapshot;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class SnapshotDescription implements RestModel {

	private String name;

	@JsonProperty("creation_time")
	private String creationTime;

	private long size;

	public String getName() {
		return name;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public long getSize() {
		return size;
	}

}
