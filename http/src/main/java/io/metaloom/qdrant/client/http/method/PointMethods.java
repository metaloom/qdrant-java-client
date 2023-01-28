package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointOverwritePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointsClearPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointsDeleteRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendResponse;
import io.metaloom.qdrant.client.http.model.point.PointsScrollRequest;
import io.metaloom.qdrant.client.http.model.point.PointsScrollResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.UpdateResultResponse;

public interface PointMethods {

	QDrantClientRequest<PointGetResponse> getPoint(String collectionName, String pointId);

	QDrantClientRequest<PointsGetResponse> getPoints(String collectionName, PointsGetRequest request);

	QDrantClientRequest<UpdateResultResponse> upsertPoints(String collectionName, PointsUpsertRequest request, boolean wait);

	QDrantClientRequest<UpdateResultResponse> deletePoints(String collectionName, PointsDeleteRequest request, boolean wait);

	QDrantClientRequest<UpdateResultResponse> setPointPayload(String collectionName, PointSetPayloadRequest request, boolean wait);

	QDrantClientRequest<UpdateResultResponse> overwritePayload(String collectionName, PointOverwritePayloadRequest request, boolean wait);

	QDrantClientRequest<UpdateResultResponse> deletePayload(String collectionName, PointDeletePayloadRequest request, boolean wait);

	QDrantClientRequest<UpdateResultResponse> clearPayload(String collectionName, PointsClearPayloadRequest request, boolean wait);

	QDrantClientRequest<PointsScrollResponse> scrollPoints(String collectionName, PointsScrollRequest request);

	QDrantClientRequest<PointsSearchResponse> searchPoints(String collectionName, PointsSearchRequest request);

	QDrantClientRequest<PointsSearchBatchResponse> searchBatchPoints(String collectionName, PointsSearchBatchRequest request);

	QDrantClientRequest<PointsRecommendResponse> recommendPoints(String collectionName, PointsRecommendRequest request);

	QDrantClientRequest<PointsRecommendBatchResponse> recommendBatchPoints(String collectionName, PointsRecommendBatchRequest request);

	QDrantClientRequest<PointCountResponse> countPoints(String collectionName, PointCountRequest request);

}
