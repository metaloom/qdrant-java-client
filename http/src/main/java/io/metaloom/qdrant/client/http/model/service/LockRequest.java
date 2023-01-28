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

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

}
