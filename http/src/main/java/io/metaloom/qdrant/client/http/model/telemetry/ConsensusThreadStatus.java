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

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getError() {
		return error;
	}
}
