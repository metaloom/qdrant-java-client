package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ReplicaSetTelemetry implements RestModel {

	private int id;

	private LocalShardTelemetry local;

	private List<RemoteShardTelemetry> remote;

	@JsonProperty("replicate_states")
	private Object replicateStates;

	public int getId() {
		return id;
	}

	public LocalShardTelemetry getLocal() {
		return local;
	}

	public List<RemoteShardTelemetry> getRemote() {
		return remote;
	}

	public Object getReplicateStates() {
		return replicateStates;
	}
	
}
