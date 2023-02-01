package io.metaloom.qdrant.client.testcases;

public interface ClusterClientTestcases {

	void testGetClusterStatusInfo() throws Exception;

	void testRemovePeerFromCluster() throws Exception;

	void testCollectionClusterInfo() throws Exception;

	void testUpdateCollectionClusterSetup() throws Exception;

}
