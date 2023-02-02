package io.metaloom.qdrant.client.util;

import java.util.Objects;
import java.util.UUID;

import io.metaloom.qdrant.client.http.model.point.PointId;

public final class QDrantClientUtil {

	private QDrantClientUtil() {
	}

	public static void assertPointId(PointId pointId) {
		Objects.requireNonNull(pointId, "The pointId must be specified");
	}

	public static void assertUuid(UUID uuid) {
		Objects.requireNonNull(uuid, "The uuid must be specified");
	}
}
