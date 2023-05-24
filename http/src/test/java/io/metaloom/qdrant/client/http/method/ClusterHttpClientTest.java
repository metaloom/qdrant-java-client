package io.metaloom.qdrant.client.http.method;

import java.math.BigInteger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.http.AbstractHTTPClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.cluster.CollectionUpdateClusterSetupRequest;
import io.metaloom.qdrant.client.testcases.ClusterClientTestcases;

public class ClusterHttpClientTest extends AbstractHTTPClientTest implements ClusterClientTestcases {

	@Test
	@Override
	public void testGetClusterStatusInfo() throws HttpErrorException {
		invoke(client.getClusterStatusInfo());
	}

	@Test
	@Override
	@Disabled("Clustering not implemented for test environment")
	public void testRemovePeerFromCluster() throws HttpErrorException {
		createTestCollection();
		BigInteger peerId = invoke(client.getCollectionClusterInfo(TEST_COLLECTION_NAME)).getResult().getPeerId();
		invoke(client.removePeerFromCluster(peerId.toString(), true));
	}

	@Test
	@Override
	public void testCollectionClusterInfo() throws HttpErrorException {
		createTestCollection();
		invoke(client.getCollectionClusterInfo(TEST_COLLECTION_NAME));
	}

	@Test
	@Override
	@Disabled("Clustering not implemented for test environment")
	public void testUpdateCollectionClusterSetup() throws HttpErrorException {
		CollectionUpdateClusterSetupRequest request = new CollectionUpdateClusterSetupRequest();
		invoke(client.updateCollectionClusterSetup(TEST_COLLECTION_NAME, request));
	}
}
