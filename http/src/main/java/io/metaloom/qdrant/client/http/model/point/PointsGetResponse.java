package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsGetResponse extends AbstractResponse {

	private List<Record> result;

	public List<Record> getResult() {
		return result;
	}

	public PointsGetResponse setResult(List<Record> result) {
		this.result = result;
		return this;
	}

	/**
	 * Return the first point record from the results.
	 * 
	 * @return Null of no records are listed, otherwise the first record.
	 */
	@JsonIgnore
	public Record first() {
		if (getResult().size() == 0) {
			return null;
		} else {
			return getResult().get(0);
		}
	}
}
