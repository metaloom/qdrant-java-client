package io.metaloom.qdrant.client.http.model;

public abstract class AbstractResponse implements RestResponse, RestModel {

	private float time;

	private String status;

	public float getTime() {
		return time;
	}

	public AbstractResponse setTime(float time) {
		this.time = time;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public AbstractResponse setStatus(String status) {
		this.status = status;
		return this;
	}

}
