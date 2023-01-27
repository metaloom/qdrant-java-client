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

	public Integer getVacuumMinVectorNumber() {
		return vacuumMinVectorNumber;
	}

	public Integer getMaxOptimizationThreads() {
		return maxOptimizationThreads;
	}

	public Integer getMemmapThreshold() {
		return memmapThreshold;
	}

	public Integer getIndexingThreshold() {
		return indexingThreshold;
	}

	public Integer getFlushIntervalSec() {
		return flushIntervalSec;
	}

	public Integer getMaxSegmentSize() {
		return maxSegmentSize;
	}

}
