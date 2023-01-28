package io.metaloom.qdrant.client.http.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.assertj.core.api.AbstractAssert;

import io.metaloom.qdrant.client.http.QDrantHttpClient;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;

public class QDrantHttpClientAssert extends AbstractAssert<QDrantHttpClientAssert, QDrantHttpClient> {

	protected QDrantHttpClientAssert(QDrantHttpClient actual) {
		super(actual, QDrantHttpClientAssert.class);
	}

	public static QDrantHttpClientAssert assertThat(QDrantHttpClient actual) {
		return new QDrantHttpClientAssert(actual);
	}

	public QDrantHttpClientAssert hasPoints(String collectionName, int expectedCount) throws HttpErrorException {
		isNotNull();
		PointCountRequest request = new PointCountRequest();
		request.setExact(true);
		PointCountResponse resp = actual.countPoints(collectionName, request).sync();
		assertTrue("The status of the response was not ok", resp.getStatus().equalsIgnoreCase("ok"));
		assertEquals("The count did not match for collection " + collectionName, expectedCount, resp.getResult().getCount());
		return this;
	}
}
