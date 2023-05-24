package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

public class PointGroup {

	private List<ScoredPoint> hits;

	private String id;

	public List<ScoredPoint> getHits() {
		return hits;
	}

	public PointGroup setHits(List<ScoredPoint> hits) {
		this.hits = hits;
		return this;
	}

	public String getId() {
		return id;
	}

	public PointGroup setId(String id) {
		this.id = id;
		return this;
	}
}
