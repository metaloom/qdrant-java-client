package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class OperationDurationStatistics implements RestModel {

	private int count;

	@JsonProperty("fail_count")
	private Integer failCount;

	@JsonProperty("avg_duration_micros")
	private Float avgDurationMicros;

	@JsonProperty("min_duration_micros")
	private Float minDurationMicros;

	@JsonProperty("max_duration_micros")
	private Float maxDurationMicros;

	public int getCount() {
		return count;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public Float getAvgDurationMicros() {
		return avgDurationMicros;
	}

	public Float getMinDurationMicros() {
		return minDurationMicros;
	}

	public Float getMaxDurationMicros() {
		return maxDurationMicros;
	}
}
