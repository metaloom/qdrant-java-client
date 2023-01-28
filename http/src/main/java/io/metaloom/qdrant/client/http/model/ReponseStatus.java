package io.metaloom.qdrant.client.http.model;

public class ReponseStatus implements RestModel {

	private String error;

	public String getError() {
		return error;
	}

	public ReponseStatus setError(String error) {
		this.error = error;
		return this;
	}

}
