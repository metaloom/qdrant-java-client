package io.metaloom.qdrant.client.http.model.collection.filter;

public class FieldCondition implements Condition {

	String key;
	Match match;
	Range range;
	GeoBoundingBox geo_bounding_box;
	GeoRadius geo_radius;
	ValuesCount values_count;
}
