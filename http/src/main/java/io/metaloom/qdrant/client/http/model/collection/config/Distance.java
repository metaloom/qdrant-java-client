package io.metaloom.qdrant.client.http.model.collection.config;

public enum Distance {

	/**
	 * <code>UnknownDistance = 0;</code>
	 */
	UnknownDistance(0),
	/**
	 * <code>Cosine = 1;</code>
	 */
	Cosine(1),
	/**
	 * <code>Euclid = 2;</code>
	 */
	Euclid(2),
	/**
	 * <code>Dot = 3;</code>
	 */
	Dot(3), UNRECOGNIZED(-1);

	int num;

	Distance(int num) {
		this.num = num;
	}

}
