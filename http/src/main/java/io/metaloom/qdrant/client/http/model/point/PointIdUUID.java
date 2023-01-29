package io.metaloom.qdrant.client.http.model.point;

import java.util.UUID;

public class PointIdUUID implements PointId {

	private UUID id;

	public UUID getId() {
		return id;
	}

	public PointIdUUID setId(UUID id) {
		this.id = id;
		return this;
	}

}
