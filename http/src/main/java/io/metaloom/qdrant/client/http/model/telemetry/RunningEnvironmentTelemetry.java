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

	public RunningEnvironmentTelemetry setDistribution(String distribution) {
		this.distribution = distribution;
		return this;
	}

	public String getDistributionVersion() {
		return distributionVersion;
	}

	public RunningEnvironmentTelemetry setDistributionVersion(String distributionVersion) {
		this.distributionVersion = distributionVersion;
		return this;
	}

	public boolean isDocker() {
		return docker;
	}

	public RunningEnvironmentTelemetry setDocker(boolean docker) {
		this.docker = docker;
		return this;
	}

	public Integer getCores() {
		return cores;
	}

	public RunningEnvironmentTelemetry setCores(Integer cores) {
		this.cores = cores;
		return this;
	}

	public Integer getRamSize() {
		return ramSize;
	}

	public RunningEnvironmentTelemetry setRamSize(Integer ramSize) {
		this.ramSize = ramSize;
		return this;
	}

	public String getCpuFlags() {
		return cpuFlags;
	}

	public RunningEnvironmentTelemetry setCpuFlags(String cpuFlags) {
		this.cpuFlags = cpuFlags;
		return this;
	}

	public Integer getDiskSize() {
		return diskSize;
	}

	public RunningEnvironmentTelemetry setDiskSize(Integer diskSize) {
		this.diskSize = diskSize;
		return this;
	}
}
