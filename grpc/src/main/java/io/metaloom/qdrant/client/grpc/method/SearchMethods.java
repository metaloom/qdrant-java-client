package io.metaloom.qdrant.client.grpc.method;

import static io.metaloom.qdrant.client.grpc.GrpcUtil.pointsAsyncStub;
import static io.metaloom.qdrant.client.grpc.GrpcUtil.pointsStub;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.proto.Points.Filter;
import io.metaloom.qdrant.client.grpc.proto.Points.LookupLocation;
import io.metaloom.qdrant.client.grpc.proto.Points.PointId;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendBatchPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendBatchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchBatchPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchBatchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchParams;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.WithPayloadSelector;
import io.metaloom.qdrant.client.grpc.proto.Points.WithVectorsSelector;

public interface SearchMethods extends ClientSettings {

	/**
	 * Retrieve closest points based on vector similarity.
	 * 
	 * @param collectionName
	 * @param vector
	 * @param limit
	 * @param scoreThreshold
	 * @return
	 */
	default GrpcClientRequest<SearchResponse> searchPoints(String collectionName, float[] vector, long limit, Float scoreThreshold) {
		return searchPoints(collectionName, vector, null, null, limit, null, null, null, scoreThreshold);
	}

	/**
	 * Retrieve closest points based on vector similarity and given filtering conditions.
	 * 
	 * @param collectionName
	 *            Name of the collection to search in
	 * @param vector
	 *            Vector data
	 * @param filter
	 *            Look only for points which satisfies this conditions
	 * @param params
	 *            Additional search params
	 * @param limit
	 *            Max number of result to return
	 * @param offset
	 *            Offset of the first result to return. May be used to paginate results. Note: large offset values may cause performance issues.
	 * @param withPayloadSelector
	 *            Select which payload to return with the response. Default: None
	 * @param withVectorsSelector
	 *            Whether to return the point vector with the result.
	 * @param scoreThreshold
	 *            Define a minimal score threshold for the result. If defined, less similar results will not be returned. Score of the returned result might be
	 *            higher or smaller than the threshold depending on the Distance function used. E.g. for cosine similarity only higher scores will be returned.
	 * @return
	 */
	default GrpcClientRequest<SearchResponse> searchPoints(String collectionName, float[] vector, Filter filter, SearchParams params, long limit,
		Long offset,
		WithPayloadSelector withPayloadSelector, WithVectorsSelector withVectorsSelector, Float scoreThreshold) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		// Convert the vector
		List<Float> vectorList = new ArrayList<>(vector.length);
		for (float f : vector) {
			vectorList.add(Float.valueOf(f));
		}

		SearchPoints.Builder request = SearchPoints.newBuilder()
			.setLimit(limit)
			.addAllVector(vectorList)
			.setCollectionName(collectionName);

		if (filter != null) {
			request.setFilter(filter);
		}
		if (params != null) {
			request.setParams(params);
		}
		if (offset != null) {
			request.setOffset(offset);
		}
		if (withPayloadSelector != null) {
			request.setWithPayload(withPayloadSelector);
		}
		if (withVectorsSelector != null) {
			request.setWithVectors(withVectorsSelector);
		}
		if (scoreThreshold != null) {
			request.setScoreThreshold(scoreThreshold);
		}

		return request(
			() -> pointsStub(this).search(request.build()),
			() -> pointsAsyncStub(this).search(request.build()));
	}

	/**
	 * Retrieve by batch the closest points based on vector similarity and given filtering conditions.
	 * 
	 * @param collectionName
	 *            Name of the collection to search in
	 * @param searches
	 *            List of search batches
	 * @return
	 */
	default GrpcClientRequest<SearchBatchResponse> searchBatch(String collectionName, List<? extends SearchPoints> searches) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(searches, "A list of searches must be specified");

		SearchBatchPoints.Builder request = SearchBatchPoints.newBuilder()
			.setCollectionName(collectionName)
			.addAllSearchPoints(searches);

		return request(
			() -> pointsStub(this).searchBatch(request.build()),
			() -> pointsAsyncStub(this).searchBatch(request.build()));
	}

	/**
	 * Look for the points which are closer to stored positive examples and at the same time further to negative examples.
	 * 
	 * @param collectionName
	 *            Name of the collection to search in
	 * @param positives
	 *            Look for vectors closest to those
	 * @param limit
	 *            Max number of result to return
	 * @return
	 */
	default GrpcClientRequest<RecommendResponse> recommendPoints(String collectionName, List<PointId> positives, int limit) {
		return recommendPoints(collectionName, positives, null, null, null, limit, null, null, null, null, null, null);
	}

	/**
	 * Look for the points which are closer to stored positive examples.
	 * 
	 * @param collectionName
	 * @param positives
	 * @param limit
	 * @param using
	 * @return
	 */
	default GrpcClientRequest<RecommendResponse> recommendPoints(String collectionName, List<PointId> positives, int limit, String using) {
		return recommendPoints(collectionName, positives, null, null, null, limit, null, null, null, null, using, null);
	}

	/**
	 * Look for the points which are closer to stored positive examples and at the same time further to negative examples.
	 * 
	 * @param collectionName
	 *            Name of the collection to search in
	 * @param positives
	 *            Look for vectors closest to those
	 * @param negatives
	 *            Try to avoid vectors like this
	 * @param filter
	 *            Look only for points which satisfies this conditions
	 * @param params
	 *            Additional search params
	 * @param limit
	 *            Max number of result to return
	 * @param offset
	 *            Offset of the first result to return. May be used to paginate results. Note: large offset values may cause performance issues.
	 * @param withPayload
	 *            Select which payload to return with the response. Default: None
	 * @param withVector
	 *            Whether to return the point vector with the result.
	 * @param scoreThreshold
	 *            Define a minimal score threshold for the result. If defined, less similar results will not be returned. Score of the returned result might be
	 *            higher or smaller than the threshold depending on the Distance function used. E.g. for cosine similarity only higher scores will be returned.
	 * @param using
	 *            Define which vector to use for recommendation, if not specified - try to use default vector
	 * @param lookupFrom
	 *            The location used to lookup vectors. If not specified - use current collection. Note: the other collection should have the same vector size as
	 *            the current collection
	 * @return
	 */
	default GrpcClientRequest<RecommendResponse> recommendPoints(String collectionName, List<PointId> positives, List<PointId> negatives,
		Filter filter,
		SearchParams params, int limit, Integer offset, WithPayloadSelector withPayload, WithVectorsSelector withVector, Float scoreThreshold,
		String using, LookupLocation lookupFrom) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(positives, "The start vector for the search has to be specified.");

		RecommendPoints.Builder request = RecommendPoints.newBuilder()
			.setCollectionName(collectionName)
			.addAllPositive(positives)
			.setLimit(limit);

		if (negatives != null) {
			request.addAllNegative(negatives);
		}
		if (filter != null) {
			request.setFilter(filter);
		}
		if (params != null) {
			request.setParams(params);
		}
		if (offset != null) {
			request.setOffset(offset);
		}
		if (withPayload != null) {
			request.setWithPayload(withPayload);
		}
		if (withVector != null) {
			request.setWithVectors(withVector);
		}
		if (scoreThreshold != null) {
			request.setScoreThreshold(scoreThreshold);
		}
		if (using != null) {
			request.setUsing(using);
		}
		if (lookupFrom != null) {
			request.setLookupFrom(lookupFrom);
		}

		return request(
			() -> pointsStub(this).recommend(request.build()),
			() -> pointsAsyncStub(this).recommend(request.build()));
	}

	/**
	 * Look for the points which are closer to stored positive examples and at the same time further to negative examples.
	 * 
	 * @param collectionName
	 *            Name of the collection to search in
	 * @param searches
	 *            Request points based on positive and negative examples.
	 * @return
	 */
	default GrpcClientRequest<RecommendBatchResponse> recommendBatchPoints(String collectionName, List<? extends RecommendPoints> searches) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(searches, "The list of searches must be specified");

		RecommendBatchPoints request = RecommendBatchPoints.newBuilder()
			.setCollectionName(collectionName)
			.addAllRecommendPoints(searches)
			.build();

		return request(
			() -> pointsStub(this).recommendBatch(request),
			() -> pointsAsyncStub(this).recommendBatch(request));
	}
}
