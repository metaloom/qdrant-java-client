package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.assertPointId;
import static io.metaloom.qdrant.client.util.QDrantClientUtil.assertUuid;

import java.util.UUID;

import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.point.DeleteVectorsResponse;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointDeleteVectorsRequest;
import io.metaloom.qdrant.client.http.model.point.PointGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointId;
import io.metaloom.qdrant.client.http.model.point.PointOverwritePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointUpdateVectorsRequest;
import io.metaloom.qdrant.client.http.model.point.PointsClearPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointsDeleteRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendGroupRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendGroupResponse;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendResponse;
import io.metaloom.qdrant.client.http.model.point.PointsScrollRequest;
import io.metaloom.qdrant.client.http.model.point.PointsScrollResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchGroupRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchGroupResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.UpdateResultResponse;
import io.metaloom.qdrant.client.http.model.point.UpdateVectorsResponse;
import io.metaloom.qdrant.client.http.model.query.WriteOrdering;

public interface PointMethods {

	/**
	 * Retrieve full information of single point by id.
	 * 
	 * @param collectionName
	 * @param pointId
	 * @return
	 */
	QDrantClientRequest<PointGetResponse> getPoint(String collectionName, String pointId);

	/**
	 * Retrieve full information of single point by id.
	 * 
	 * @param collectionName
	 * @param pointId
	 * @return
	 */
	default QDrantClientRequest<PointGetResponse> getPoint(String collectionName, PointId pointId) {
		assertPointId(pointId);
		return getPoint(collectionName, pointId.toString());
	}

	/**
	 * Retrieve full information of single point by id.
	 * 
	 * @param collectionName
	 * @param id
	 * @return
	 */
	default QDrantClientRequest<PointGetResponse> getPoint(String collectionName, long id) {
		return getPoint(collectionName, String.valueOf(id));
	}

	/**
	 * Retrieve full information of single point by id.
	 * 
	 * @param collectionName
	 * @param uuid
	 * @return
	 */
	default QDrantClientRequest<PointGetResponse> getPoint(String collectionName, UUID uuid) {
		assertUuid(uuid);
		return getPoint(collectionName, uuid.toString());
	}

	/**
	 * Retrieve multiple points by specified IDs.
	 *
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsGetResponse> getPoints(String collectionName, PointsGetRequest request);

	/**
	 * Perform insert + updates on points. If point with given ID already exists - it will be overwritten.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<UpdateResultResponse> upsertPoints(String collectionName, PointsUpsertRequest request, boolean wait);

	/**
	 * Delete points.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<UpdateResultResponse> deletePoints(String collectionName, PointsDeleteRequest request, boolean wait);

	/**
	 * Update specified named vectors on points, keep unspecified vectors intact.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @param ordering
	 * @return
	 */
	QDrantClientRequest<UpdateVectorsResponse> updateVectors(String collectionName, PointUpdateVectorsRequest request, boolean wait,
		WriteOrdering ordering);

	/**
	 * Delete named vectors from the given points.
	 *
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @param ordering
	 * @return
	 */
	QDrantClientRequest<DeleteVectorsResponse> deleteVectors(String collectionName, PointDeleteVectorsRequest request, boolean wait,
		WriteOrdering ordering);

	/**
	 * Set payload values for points. This will merge the points payload and add new properties or update existing ones.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<UpdateResultResponse> setPointPayload(String collectionName, PointSetPayloadRequest request, boolean wait);

	/**
	 * Replace full payload of points with new one. This will overwrite the whole given payload with the new one. Existing properties will be remove.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<UpdateResultResponse> overwritePayload(String collectionName, PointOverwritePayloadRequest request, boolean wait);

	/**
	 * Delete specified key payload for points. This will only delete the specified properties from the found payloads.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<UpdateResultResponse> deletePayload(String collectionName, PointDeletePayloadRequest request, boolean wait);

	/**
	 * Remove all payload for specified points. Removes the whole payload from all selected points.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<UpdateResultResponse> clearPayload(String collectionName, PointsClearPayloadRequest request, boolean wait);

	/**
	 * Scroll request - paginate over all points which matches given filtering condition.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsScrollResponse> scrollPoints(String collectionName, PointsScrollRequest request);

	/**
	 * Retrieve closest points based on vector similarity and given filtering conditions.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsSearchResponse> searchPoints(String collectionName, PointsSearchRequest request);

	/**
	 * Retrieve by batch the closest points based on vector similarity and given filtering conditions.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsSearchBatchResponse> searchBatchPoints(String collectionName, PointsSearchBatchRequest request);

	/**
	 * Retrieve closest points based on vector similarity and given filtering conditions, grouped by a given payload field.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsSearchGroupResponse> searchGroupPoints(String collectionName, PointsSearchGroupRequest request);

	/**
	 * Look for the points which are closer to stored positive examples and at the same time further to negative examples.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsRecommendResponse> recommendPoints(String collectionName, PointsRecommendRequest request);

	/**
	 * Look for the points which are closer to stored positive examples and at the same time further to negative examples.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsRecommendBatchResponse> recommendBatchPoints(String collectionName, PointsRecommendBatchRequest request);

	/**
	 * Look for the points which are closer to stored positive examples and at the same time further to negative examples, grouped by a given payload field.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointsRecommendGroupResponse> recommendGroupPoints(String collectionName, PointsRecommendGroupRequest request);

	/**
	 * Count points which matches given filtering condition.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<PointCountResponse> countPoints(String collectionName, PointCountRequest request);

}
