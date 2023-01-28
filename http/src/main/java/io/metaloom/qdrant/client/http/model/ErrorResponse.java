package io.metaloom.qdrant.client.http.model;

public class ErrorResponse implements RestModel {

	private ReponseStatus status;

	private float time;

	private Object result;

	public ReponseStatus getStatus() {
		return status;
	}

	public ErrorResponse setStatus(ReponseStatus status) {
		this.status = status;
		return this;
	}

	public float getTime() {
		return time;
	}

	public ErrorResponse setTime(float time) {
		this.time = time;
		return this;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
