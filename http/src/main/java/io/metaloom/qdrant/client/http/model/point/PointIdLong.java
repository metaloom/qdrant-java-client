package io.metaloom.qdrant.client.http.model.point;

public class PointIdLong implements PointId {

	private Long id;

	public Long getId() {
		return id;
	}

	public PointIdLong setId(Long id) {
		this.id = id;
		return this;
	}

}
