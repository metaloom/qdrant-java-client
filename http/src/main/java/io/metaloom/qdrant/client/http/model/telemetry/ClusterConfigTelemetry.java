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

	public P2pConfigTelemetry getP2p() {
		return p2p;
	}

	public ConsensusConfigTelemetry getConsensus() {
		return consensus;
	}
}
