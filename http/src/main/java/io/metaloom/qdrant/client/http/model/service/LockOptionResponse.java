package io.metaloom.qdrant.client.http.model.service;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class LockOptionResponse extends AbstractResponse {

	private LockOption result;

	public LockOption getResult() {
		return result;
	}
}
