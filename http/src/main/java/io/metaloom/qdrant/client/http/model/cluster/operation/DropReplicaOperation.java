package io.metaloom.qdrant.client.http.model.cluster.operation;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropReplicaOperation extends AbstractShardOperation {

	@JsonProperty("peer_id")
	private BigInteger peerId;

	public BigInteger getPeerId() {
		return peerId;
	}

	public DropReplicaOperation setPeerId(BigInteger peerId) {
		this.peerId = peerId;
		return this;
	}

}
