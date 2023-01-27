package io.metaloom.qdrant.client.http.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class LockOption implements RestModel {

	@JsonProperty("error_message")
	private String errorMessage;

	private boolean write;

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isWrite() {
		return write;
	}
}
