package io.metaloom.qdrant.client.http.model.cluster.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropReplicaOperation extends AbstractShardOperation {

	@JsonProperty("peer_id")
	private long peerId;

	public long getPeerId() {
		return peerId;
	}

}
