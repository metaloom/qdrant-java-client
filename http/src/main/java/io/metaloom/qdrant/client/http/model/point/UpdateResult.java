package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class UpdateResult implements RestModel {

	@JsonProperty("operation_id")
	private long operationId;

	private UpdateStatus status;

	public long getOperationId() {
		return operationId;
	}

	public UpdateResult setOperationId(long operationId) {
		this.operationId = operationId;
		return this;
	}

	public UpdateStatus getStatus() {
		return status;
	}

	public UpdateResult setStatus(UpdateStatus status) {
		this.status = status;
		return this;
	}
}
