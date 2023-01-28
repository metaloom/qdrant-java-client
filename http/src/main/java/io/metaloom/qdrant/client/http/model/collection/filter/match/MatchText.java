package io.metaloom.qdrant.client.http.model.collection.filter.match;

public class MatchText implements Match {

	private String text;

	public String getText() {
		return text;
	}

	public MatchText setText(String text) {
		this.text = text;
		return this;
	}

}
