package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterStatusTelemetry {

	@JsonProperty("number_of_peers")
	private int numberOfPeers;

	private long term;

	private long commit;

	@JsonProperty("pending_operations")
	private int pendingOperations;

	private String role;

	@JsonProperty("is_voter")
	private boolean isVoter;

	@JsonProperty("peer_id")
	private long peerId;

	@JsonProperty("consensus_thread_status")
	private ConsensusThreadStatus consensusThreadStatus;

	public int getNumberOfPeers() {
		return numberOfPeers;
	}

	public long getTerm() {
		return term;
	}

	public long getCommit() {
		return commit;
	}

	public String getRole() {
		return role;
	}

	public boolean isVoter() {
		return isVoter;
	}

	public long getPeerId() {
		return peerId;
	}

	public ConsensusThreadStatus getConsensusThreadStatus() {
		return consensusThreadStatus;
	}

	public int getPendingOperations() {
		return pendingOperations;
	}
}
