package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CountResult implements RestModel {

	private long count;

	public long getCount() {
		return count;
	}
	
	public CountResult setCount(long count) {
		this.count = count;
		return this;
	}
}
