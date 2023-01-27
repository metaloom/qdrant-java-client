package io.metaloom.qdrant.client.http.model.collection.filter.condition;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.collection.filter.GeoBoundingBox;
import io.metaloom.qdrant.client.http.model.collection.filter.GeoRadius;
import io.metaloom.qdrant.client.http.model.collection.filter.Range;
import io.metaloom.qdrant.client.http.model.collection.filter.ValuesCount;
import io.metaloom.qdrant.client.http.model.collection.filter.match.Match;

public class FieldCondition implements Condition {

	private String key;

	private Match match;

	private Range range;

	public FieldCondition() {
	}

	@JsonProperty("geo_bounding_box")
	private GeoBoundingBox geoBoundingBox;

	@JsonProperty("geo_radius")
	private GeoRadius geoRadius;

	@JsonProperty("values_count")
	private ValuesCount valuesCount;

	public String getKey() {
		return key;
	}

	public GeoBoundingBox getGeoBoundingBox() {
		return geoBoundingBox;
	}

	public Match getMatch() {
		return match;
	}

	public Range getRange() {
		return range;
	}

	public GeoRadius getGeoRadius() {
		return geoRadius;
	}

	public ValuesCount getValuesCount() {
		return valuesCount;
	}
}
