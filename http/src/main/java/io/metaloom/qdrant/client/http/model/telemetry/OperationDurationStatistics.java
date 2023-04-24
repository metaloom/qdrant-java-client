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

	@JsonProperty("last_responded")
	private String lastResponded;

	public int getCount() {
		return count;
	}

	public OperationDurationStatistics setCount(int count) {
		this.count = count;
		return this;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public OperationDurationStatistics setFailCount(Integer failCount) {
		this.failCount = failCount;
		return this;
	}

	public Float getAvgDurationMicros() {
		return avgDurationMicros;
	}

	public OperationDurationStatistics setAvgDurationMicros(Float avgDurationMicros) {
		this.avgDurationMicros = avgDurationMicros;
		return this;
	}

	public Float getMinDurationMicros() {
		return minDurationMicros;
	}

	public OperationDurationStatistics setMinDurationMicros(Float minDurationMicros) {
		this.minDurationMicros = minDurationMicros;
		return this;
	}

	public Float getMaxDurationMicros() {
		return maxDurationMicros;
	}

	public OperationDurationStatistics setMaxDurationMicros(Float maxDurationMicros) {
		this.maxDurationMicros = maxDurationMicros;
		return this;
	}

	public String getLastResponded() {
		return lastResponded;
	}

	public OperationDurationStatistics setLastResponded(String lastResponded) {
		this.lastResponded = lastResponded;
		return this;
	}
}
