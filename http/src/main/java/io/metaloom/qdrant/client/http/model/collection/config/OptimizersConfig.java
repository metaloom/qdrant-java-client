package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptimizersConfig {

	@JsonProperty("deleted_threshold")
	private Double deletedThreshold;

	@JsonProperty("vacuum_min_vector_number")
	private Integer vacuumMinVectorNumber;

	@JsonProperty("default_segment_number")
	private Integer defaultSegmentNumber;

	@JsonProperty("max_segment_size")
	private Integer maxSegmentSize;

	@JsonProperty("memmap_threshold")
	private Integer memmapThreshold;

	@JsonProperty("indexing_threshold")
	private Integer indexingThreshold;

	@JsonProperty("flush_interval_sec")
	private Integer flushIntervalSec;

	@JsonProperty("max_optimization_threads")
	private Integer maxOptimizationThreads;

	public Double getDeletedThreshold() {
		return deletedThreshold;
	}

	public OptimizersConfig setDeletedThreshold(Double deletedThreshold) {
		this.deletedThreshold = deletedThreshold;
		return this;
	}

	public Integer getVacuumMinVectorNumber() {
		return vacuumMinVectorNumber;
	}

	public OptimizersConfig setVacuumMinVectorNumber(Integer vacuumMinVectorNumber) {
		this.vacuumMinVectorNumber = vacuumMinVectorNumber;
		return this;
	}

	public Integer getMaxOptimizationThreads() {
		return maxOptimizationThreads;
	}

	public OptimizersConfig setMaxOptimizationThreads(Integer maxOptimizationThreads) {
		this.maxOptimizationThreads = maxOptimizationThreads;
		return this;
	}

	public Integer getMemmapThreshold() {
		return memmapThreshold;
	}

	public OptimizersConfig setMemmapThreshold(Integer memmapThreshold) {
		this.memmapThreshold = memmapThreshold;
		return this;
	}

	public Integer getIndexingThreshold() {
		return indexingThreshold;
	}

	public OptimizersConfig setIndexingThreshold(Integer indexingThreshold) {
		this.indexingThreshold = indexingThreshold;
		return this;
	}

	public Integer getFlushIntervalSec() {
		return flushIntervalSec;
	}

	public OptimizersConfig setFlushIntervalSec(Integer flushIntervalSec) {
		this.flushIntervalSec = flushIntervalSec;
		return this;
	}

	public Integer getMaxSegmentSize() {
		return maxSegmentSize;
	}

	public OptimizersConfig setMaxSegmentSize(Integer maxSegmentSize) {
		this.maxSegmentSize = maxSegmentSize;
		return this;
	}

}
