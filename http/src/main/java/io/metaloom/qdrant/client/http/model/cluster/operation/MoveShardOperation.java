package io.metaloom.qdrant.client.http.model.cluster.operation;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveShardOperation extends AbstractShardOperation {

	@JsonProperty("to_peer_id")
	private BigInteger toPeerId;

	@JsonProperty("from_peer_id")
	private BigInteger fromPeerId;

	public BigInteger getToPeerId() {
		return toPeerId;
	}

	public MoveShardOperation setToPeerId(BigInteger toPeerId) {
		this.toPeerId = toPeerId;
		return this;
	}

	public BigInteger getFromPeerId() {
		return fromPeerId;
	}

	public MoveShardOperation setFromPeerId(BigInteger fromPeerId) {
		this.fromPeerId = fromPeerId;
		return this;
	}

}
