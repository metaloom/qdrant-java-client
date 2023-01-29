package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

	public static PointId of(UUID uuid) {
		return new PointIdUUID().setId(uuid);
	}

	public static List<PointId> list(String... uuid) {
		return Arrays.asList(uuid).stream()
			.map(PointId::of)
			.collect(Collectors.toList());
	}

	public static List<PointId> list(UUID... uuid) {
		return Arrays.asList(uuid).stream()
			.map(PointId::of)
			.collect(Collectors.toList());
	}

	public static List<PointId> list(long... ids) {
		return toList(ids).stream()
			.map(PointId::of)
			.collect(Collectors.toList());
	}

}
