package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ConsensusThreadStatus implements RestModel {

	@JsonProperty("consensus_thread_status")
	private String consensusThreadStatus;

	@JsonProperty("last_update")
	private String lastUpdate;

	@JsonProperty("err")
	private String error;

	public String getConsensusThreadStatus() {
		return consensusThreadStatus;
	}

	public ConsensusThreadStatus setConsensusThreadStatus(String consensusThreadStatus) {
		this.consensusThreadStatus = consensusThreadStatus;
		return this;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public ConsensusThreadStatus setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
		return this;
	}

	public String getError() {
		return error;
	}

	public ConsensusThreadStatus setError(String error) {
		this.error = error;
		return this;
	}
}
