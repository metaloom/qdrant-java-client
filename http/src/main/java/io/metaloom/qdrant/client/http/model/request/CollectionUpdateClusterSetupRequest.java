package io.metaloom.qdrant.client.http.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.cluster.operation.AbortTransferOperation;
import io.metaloom.qdrant.client.http.model.cluster.operation.DropReplicaOperation;
import io.metaloom.qdrant.client.http.model.cluster.operation.MoveShardOperation;
import io.metaloom.qdrant.client.http.model.cluster.operation.ReplicateShardOperation;

public class CollectionUpdateClusterSetupRequest implements RestRequest {

	@JsonProperty("move_shard")
	private MoveShardOperation moveShardOperation;

	@JsonProperty("drop_replica")
	private DropReplicaOperation dropReplicaOperation;

	@JsonProperty("abort_transfer")
	private AbortTransferOperation abortTransferOperation;

	@JsonProperty("replicate_shard")
	private ReplicateShardOperation replicateShardOperation;

	public AbortTransferOperation getAbortTransferOperation() {
		return abortTransferOperation;
	}

	public DropReplicaOperation getDropReplicaOperation() {
		return dropReplicaOperation;
	}

	public MoveShardOperation getMoveShardOperation() {
		return moveShardOperation;
	}

	public ReplicateShardOperation getReplicateShardOperation() {
		return replicateShardOperation;
	}
}
