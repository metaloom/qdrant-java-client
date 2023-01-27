package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class RunningEnvironmentTelemetry implements RestModel {

	private String distribution;

	@JsonProperty("distribution_version")
	private String distributionVersion;

	@JsonProperty("is_docker")
	private boolean docker;

	private Integer cores;

	@JsonProperty("ram_size")
	private Integer ramSize;

	@JsonProperty("disk_size")
	private Integer diskSize;

	@JsonProperty("cpu_flags")
	private String cpuFlags;

	public String getDistribution() {
		return distribution;
	}

	public String getDistributionVersion() {
		return distributionVersion;
	}

	public boolean isDocker() {
		return docker;
	}

	public Integer getCores() {
		return cores;
	}

	public Integer getRamSize() {
		return ramSize;
	}

	public String getCpuFlags() {
		return cpuFlags;
	}

	public Integer getDiskSize() {
		return diskSize;
	}
}
