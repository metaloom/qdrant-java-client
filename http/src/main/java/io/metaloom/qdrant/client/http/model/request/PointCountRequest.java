package io.metaloom.qdrant.client.http.model.request;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointCountRequest implements RestModel {

	Filter filter;

	Boolean exact;
}
