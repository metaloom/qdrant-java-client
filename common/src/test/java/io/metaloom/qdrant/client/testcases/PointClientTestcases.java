package io.metaloom.qdrant.client.testcases;

public interface PointClientTestcases {

	void testGetPoint() throws Exception;

	void testGetPoints() throws Exception;

	void testUpsertPointViaList() throws Exception;

	void testUpsertPointsViaListBatch() throws Exception;

	void testUpsertPointsViaNamedBatch() throws Exception;

	void testDeletePoints() throws Exception;

	void testSetPointPayload() throws Exception;

	void testOverwritePointPayload() throws Exception;

	void testDeletePointPayload() throws Exception;

	void testClearPointPayload() throws Exception;

	void testScrollPoints() throws Exception;

	void testSearchPoints() throws Exception;

	void testSearchBatchPoints() throws Exception;

	void testRecommendPoints() throws Exception;

	void testRecommendBatchPoints() throws Exception;

	void testCountPoints() throws Exception;

}
