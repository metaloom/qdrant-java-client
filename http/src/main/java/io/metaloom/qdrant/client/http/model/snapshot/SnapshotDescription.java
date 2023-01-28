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

	public SnapshotDescription setName(String name) {
		this.name = name;
		return this;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public SnapshotDescription setCreationTime(String creationTime) {
		this.creationTime = creationTime;
		return this;
	}

	public long getSize() {
		return size;
	}

	public SnapshotDescription setSize(long size) {
		this.size = size;
		return this;
	}

}
