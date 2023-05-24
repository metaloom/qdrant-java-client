package io.metaloom.qdrant.client.http.model.point;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class PointUpdateVectorsRequest implements RestRequestModel {

	private List<PointVectors> points = new ArrayList<>();

	public List<PointVectors> getPoints() {
		return points;
	}

	@JsonIgnore
	public PointUpdateVectorsRequest addPoint(PointVectors value) {
		this.points.add(value);
		return this;
	}

	public PointUpdateVectorsRequest setPoints(List<PointVectors> points) {
		this.points = points;
		return this;
	}

}
