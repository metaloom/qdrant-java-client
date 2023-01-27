package io.metaloom.qdrant.client.http.model.collection.filter;

import java.util.List;

public class Filter {

	/**
	 * At least one of those conditions should match.
	 */
	List<? extends Condition> should;

	/**
	 * All conditions must match.
	 */
	List<? extends Condition> must;

	/**
	 * All conditions must NOT match.
	 */
	List<? extends Condition> must_not;
}
