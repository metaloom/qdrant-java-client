package io.metaloom.qdrant.client.http.model;

public abstract class AbstractResponse implements RestResponse {

	private float time;

	private String status;

	public float getTime() {
		return time;
	}

	public String getStatus() {
		return status;
	}
}
