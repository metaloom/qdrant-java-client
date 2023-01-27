package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsensusConfigTelemetry {

	@JsonProperty("max_message_queue_size")
	private int maxMessageQueueSize;

	@JsonProperty("tick_period_ms")
	private long tickPeriodMs;

	@JsonProperty("bootstrap_timeout_sec")
	private long bootstrapTimeoutSec;

	public int getMaxMessageQueueSize() {
		return maxMessageQueueSize;
	}

	public long getTickPeriodMs() {
		return tickPeriodMs;
	}

	public long getBootstrapTimeoutSec() {
		return bootstrapTimeoutSec;
	}
}
