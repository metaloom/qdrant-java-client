package io.metaloom.qdrant.client.http.model.cluster.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbortTransferOperation extends AbstractShardOperation {

	@JsonProperty("to_peer_id")
	private long toPeerId;

	@JsonProperty("from_peer_id")
	private long fromPeerId;

	public long getToPeerId() {
		return toPeerId;
	}

	public AbortTransferOperation setToPeerId(long toPeerId) {
		this.toPeerId = toPeerId;
		return this;
	}

	public long getFromPeerId() {
		return fromPeerId;
	}

	public AbortTransferOperation setFromPeerId(long fromPeerId) {
		this.fromPeerId = fromPeerId;
		return this;
	}
}
