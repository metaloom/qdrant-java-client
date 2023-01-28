package io.metaloom.qdrant.client.http.model.cluster;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.cluster.operation.AbortTransferOperation;
import io.metaloom.qdrant.client.http.model.cluster.operation.DropReplicaOperation;
import io.metaloom.qdrant.client.http.model.cluster.operation.MoveShardOperation;
import io.metaloom.qdrant.client.http.model.cluster.operation.ReplicateShardOperation;

public class CollectionUpdateClusterSetupRequest implements RestRequestModel {

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

	public void setAbortTransferOperation(AbortTransferOperation abortTransferOperation) {
		this.abortTransferOperation = abortTransferOperation;
	}

	public DropReplicaOperation getDropReplicaOperation() {
		return dropReplicaOperation;
	}

	public void setDropReplicaOperation(DropReplicaOperation dropReplicaOperation) {
		this.dropReplicaOperation = dropReplicaOperation;
	}

	public MoveShardOperation getMoveShardOperation() {
		return moveShardOperation;
	}

	public void setMoveShardOperation(MoveShardOperation moveShardOperation) {
		this.moveShardOperation = moveShardOperation;
	}

	public ReplicateShardOperation getReplicateShardOperation() {
		return replicateShardOperation;
	}

	public void setReplicateShardOperation(ReplicateShardOperation replicateShardOperation) {
		this.replicateShardOperation = replicateShardOperation;
	}
}
