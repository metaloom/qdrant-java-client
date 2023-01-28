package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ClusterStatusTelemetry implements RestModel {

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

	public ClusterStatusTelemetry setNumberOfPeers(int numberOfPeers) {
		this.numberOfPeers = numberOfPeers;
		return this;
	}

	public long getTerm() {
		return term;
	}

	public ClusterStatusTelemetry setTerm(long term) {
		this.term = term;
		return this;
	}

	public long getCommit() {
		return commit;
	}

	public ClusterStatusTelemetry setCommit(long commit) {
		this.commit = commit;
		return this;
	}

	public String getRole() {
		return role;
	}

	public ClusterStatusTelemetry setRole(String role) {
		this.role = role;
		return this;
	}

	public boolean isVoter() {
		return isVoter;
	}

	public ClusterStatusTelemetry setVoter(boolean isVoter) {
		this.isVoter = isVoter;
		return this;
	}

	public long getPeerId() {
		return peerId;
	}

	public ClusterStatusTelemetry setPeerId(long peerId) {
		this.peerId = peerId;
		return this;
	}

	public ConsensusThreadStatus getConsensusThreadStatus() {
		return consensusThreadStatus;
	}

	public ClusterStatusTelemetry setConsensusThreadStatus(ConsensusThreadStatus consensusThreadStatus) {
		this.consensusThreadStatus = consensusThreadStatus;
		return this;
	}

	public int getPendingOperations() {
		return pendingOperations;
	}

	public ClusterStatusTelemetry setPendingOperations(int pendingOperations) {
		this.pendingOperations = pendingOperations;
		return this;
	}
}
