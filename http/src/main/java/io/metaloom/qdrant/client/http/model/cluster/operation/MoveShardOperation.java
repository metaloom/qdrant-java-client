package io.metaloom.qdrant.client.http.model.cluster.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveShardOperation extends AbstractShardOperation {

	@JsonProperty("to_peer_id")
	private long toPeerId;

	@JsonProperty("from_peer_id")
	private long fromPeerId;

	public long getToPeerId() {
		return toPeerId;
	}

	public long getFromPeerId() {
		return fromPeerId;
	}

}
