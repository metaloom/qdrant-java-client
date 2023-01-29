package io.metaloom.qdrant.client.http.model.point;

import java.util.UUID;

import io.metaloom.qdrant.client.http.model.RestModel;

public interface PointId extends RestModel {

	public static PointId of(String uuidStr) {
		// TODO parse uuid / simple / hyphenated / urn
		UUID uuid = UUID.fromString(uuidStr);
		return new PointIdUUID().setId(uuid);
	}

	public static PointId of(long id) {
		return new PointIdLong().setId(id);
	}

}
