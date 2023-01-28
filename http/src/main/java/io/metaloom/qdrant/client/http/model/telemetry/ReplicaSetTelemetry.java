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

	public ReplicaSetTelemetry setId(int id) {
		this.id = id;
		return this;
	}

	public LocalShardTelemetry getLocal() {
		return local;
	}

	public ReplicaSetTelemetry setLocal(LocalShardTelemetry local) {
		this.local = local;
		return this;
	}

	public List<RemoteShardTelemetry> getRemote() {
		return remote;
	}

	public ReplicaSetTelemetry setRemote(List<RemoteShardTelemetry> remote) {
		this.remote = remote;
		return this;
	}

	public Object getReplicateStates() {
		return replicateStates;
	}

	public ReplicaSetTelemetry setReplicateStates(Object replicateStates) {
		this.replicateStates = replicateStates;
		return this;
	}

}
