package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ConsensusConfigTelemetry implements RestModel {

	@JsonProperty("max_message_queue_size")
	private int maxMessageQueueSize;

	@JsonProperty("tick_period_ms")
	private long tickPeriodMs;

	@JsonProperty("bootstrap_timeout_sec")
	private long bootstrapTimeoutSec;

	public int getMaxMessageQueueSize() {
		return maxMessageQueueSize;
	}

	public ConsensusConfigTelemetry setMaxMessageQueueSize(int maxMessageQueueSize) {
		this.maxMessageQueueSize = maxMessageQueueSize;
		return this;
	}

	public long getTickPeriodMs() {
		return tickPeriodMs;
	}

	public ConsensusConfigTelemetry setTickPeriodMs(long tickPeriodMs) {
		this.tickPeriodMs = tickPeriodMs;
		return this;
	}

	public long getBootstrapTimeoutSec() {
		return bootstrapTimeoutSec;
	}

	public ConsensusConfigTelemetry setBootstrapTimeoutSec(long bootstrapTimeoutSec) {
		this.bootstrapTimeoutSec = bootstrapTimeoutSec;
		return this;
	}
}
