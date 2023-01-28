package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ClusterConfigTelemetry implements RestModel {

	@JsonProperty("grpc_timeout_ms")
	private long grcpTimeoutMs;

	private P2pConfigTelemetry p2p;

	private ConsensusConfigTelemetry consensus;

	public long getGrcpTimeoutMs() {
		return grcpTimeoutMs;
	}

	public ClusterConfigTelemetry setGrcpTimeoutMs(long grcpTimeoutMs) {
		this.grcpTimeoutMs = grcpTimeoutMs;
		return this;
	}

	public P2pConfigTelemetry getP2p() {
		return p2p;
	}

	public ClusterConfigTelemetry setP2p(P2pConfigTelemetry p2p) {
		this.p2p = p2p;
		return this;
	}

	public ConsensusConfigTelemetry getConsensus() {
		return consensus;
	}

	public ClusterConfigTelemetry setConsensus(ConsensusConfigTelemetry consensus) {
		this.consensus = consensus;
		return this;
	}
}
