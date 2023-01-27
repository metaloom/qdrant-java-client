package io.metaloom.qdrant.client.http.model.collection.config;

public class OptimizersConfigDiff {

	Double deleted_threshold;
	Integer vacuum_min_vector_number;
	Integer default_segment_number;
	Integer max_segment_size;
	Integer memmap_threshold;
	Integer indexing_threshold;
	Integer flush_interval_sec;
	Integer max_optimization_threads;
}
