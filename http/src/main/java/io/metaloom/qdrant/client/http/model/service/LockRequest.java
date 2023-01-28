package io.metaloom.qdrant.client.http.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class LockRequest implements RestRequestModel {

	@JsonProperty("error_message")
	private String errorMessage;

	private boolean write;

	public String getErrorMessage() {
		return errorMessage;
	}

	public LockRequest setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public boolean isWrite() {
		return write;
	}

	public LockRequest setWrite(boolean write) {
		this.write = write;
		return this;
	}

}
