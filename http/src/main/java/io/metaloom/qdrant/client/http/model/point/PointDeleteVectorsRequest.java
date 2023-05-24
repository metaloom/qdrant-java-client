package io.metaloom.qdrant.client.http.model.point;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointDeleteVectorsRequest implements RestRequestModel {

	private List<String> vector;

	private List<PointId> points;

	private Filter filter;

	public List<String> getVectors() {
		return vector;
	}

	public PointDeleteVectorsRequest setVector(List<String> vector) {
		this.vector = vector;
		return this;
	}

	@JsonIgnore
	public PointDeleteVectorsRequest setVector(String... vectors) {
		this.vector = Arrays.asList(vectors);
		return this;
	}

	public List<PointId> getPoints() {
		return points;
	}

	public PointDeleteVectorsRequest setPoints(List<PointId> points) {
		this.points = points;
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointDeleteVectorsRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}
}
