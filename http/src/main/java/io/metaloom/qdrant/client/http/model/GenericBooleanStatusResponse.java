package io.metaloom.qdrant.client.http.model;

public class GenericBooleanStatusResponse extends AbstractResponse {

	private boolean result;

	public boolean getResult() {
		return result;
	}

	public GenericBooleanStatusResponse setResult(boolean result) {
		this.result = result;
		return this;
	}

}
