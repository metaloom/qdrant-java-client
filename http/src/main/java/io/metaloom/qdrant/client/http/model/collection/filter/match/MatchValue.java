package io.metaloom.qdrant.client.http.model.collection.filter.match;

public class MatchValue implements Match {

	private Object value;

	public Object getValue() {
		return value;
	}

	public MatchValue setValue(Object value) {
		this.value = value;
		return this;
	}

}
