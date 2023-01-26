package io.metaloom.qdrant.client.grcp.methods;

import static io.metaloom.qdrant.client.grcp.GrcpUtil.pointsAsyncStub;
import static io.metaloom.qdrant.client.grcp.GrcpUtil.pointsStub;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import io.metaloom.qdrant.client.http.ClientSettings;
import qdrant.JsonWithInt.Value;
import qdrant.Points.ClearPayloadPoints;
import qdrant.Points.CountPoints;
import qdrant.Points.CountPoints.Builder;
import qdrant.Points.CountResponse;
import qdrant.Points.DeletePayloadPoints;
import qdrant.Points.DeletePoints;
import qdrant.Points.Filter;
import qdrant.Points.GetPoints;
import qdrant.Points.GetResponse;
import qdrant.Points.PointId;
import qdrant.Points.PointStruct;
import qdrant.Points.PointsIdsList;
import qdrant.Points.PointsOperationResponse;
import qdrant.Points.PointsSelector;
import qdrant.Points.ScrollPoints;
import qdrant.Points.ScrollResponse;
import qdrant.Points.SetPayloadPoints;
import qdrant.Points.UpsertPoints;
import qdrant.Points.WithPayloadSelector;
import qdrant.Points.WithVectorsSelector;

public interface PointMethods extends ClientSettings {

	/**
	 * Retrieve full information of single point by id.
	 * 
	 * @param collectionName
	 * @param pointId
	 * @return
	 */
	default GrpcClientRequest<GetResponse> getPoint(String collectionName, PointId pointId) {
		return getPoint(collectionName, null, null, pointId);
	}

	/**
	 * Retrieve full information of single point by id.
	 * 
	 * @param collectionName
	 * @param withPayloadSelector
	 * @param withVectorsSelector
	 * @param pointId
	 * @return
	 */
	default GrpcClientRequest<GetResponse> getPoint(String collectionName, WithPayloadSelector withPayloadSelector,
		WithVectorsSelector withVectorsSelector, PointId pointId) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		GetPoints.Builder request = GetPoints.newBuilder()
			.setCollectionName(collectionName)
			.addIds(pointId);

		if (withPayloadSelector != null) {
			request.setWithPayload(withPayloadSelector);
		}

		if (withVectorsSelector != null) {
			request.setWithVectors(withVectorsSelector);
		}

		return request(
			() -> pointsStub(this).get(request.build()),
			() -> pointsAsyncStub(this).get(request.build()));
	}

	/**
	 * Retrieve multiple points by specified IDs
	 * 
	 * @param collectionName
	 * @param withPayloadSelector
	 * @param withVectorsSelector
	 * @param ids
	 * @return
	 */
	default GrpcClientRequest<GetResponse> getPoints(String collectionName, WithPayloadSelector withPayloadSelector,
		WithVectorsSelector withVectorsSelector, PointId... ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		return getPoints(collectionName, withPayloadSelector, withVectorsSelector, Arrays.asList(ids));
	}

	/**
	 * Retrieve multiple points by specified IDs
	 * 
	 * @param collectionName
	 * @param withPayloadSelector
	 * @param withVectorsSelector
	 * @param ids
	 * @return
	 */
	default GrpcClientRequest<GetResponse> getPoints(String collectionName, WithPayloadSelector withPayloadSelector,
		WithVectorsSelector withVectorsSelector,
		List<PointId> ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(ids, "Point Ids must be specified");

		GetPoints.Builder request = GetPoints.newBuilder()
			.setCollectionName(collectionName)
			.addAllIds(ids);

		if (withPayloadSelector != null) {
			request.setWithPayload(withPayloadSelector);
		}

		if (withVectorsSelector != null) {
			request.setWithVectors(withVectorsSelector);
		}

		return request(
			() -> pointsStub(this).get(request.build()),
			() -> pointsAsyncStub(this).get(request.build()));
	}

	/**
	 * Scroll request - paginate over all points which matches given filtering condition.
	 * 
	 * @param collectionName
	 *            Name of the collection to retrieve from
	 * @param offset
	 *            Start ID to read points from.
	 * @param limit
	 *            Page size. Default: 10
	 * @param filter
	 *            Look only for points which satisfies this conditions. If not provided - all points.
	 * @param withPayloadSelector
	 *            Select which payload to return with the response. Default: All
	 * @param withVectorSelector
	 *            Options for specifying which vector to include
	 * @return
	 */
	default GrpcClientRequest<ScrollResponse> scrollPoint(String collectionName, PointId offset, Integer limit, Filter filter,
		WithPayloadSelector withPayloadSelector,
		WithVectorsSelector withVectorSelector) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		ScrollPoints.Builder request = ScrollPoints.newBuilder()
			.setCollectionName(collectionName);
		if (offset != null) {
			request.setOffset(offset);
		}
		if (limit != null) {
			request.setLimit(limit);
		}
		if (withPayloadSelector != null) {
			request.setWithPayload(withPayloadSelector);
		}
		if (withVectorSelector != null) {
			request.setWithVectors(withVectorSelector);
		}
		if (filter != null) {
			request.setFilter(filter);
		}
		return request(
			() -> pointsStub(this).scroll(request.build()),
			() -> pointsAsyncStub(this).scroll(request.build()));
	}

	/**
	 * Count points which matches given filtering condition.
	 * 
	 * @param collectionName
	 *            Name of the collection to count in
	 * @param filter
	 *            Look only for points which satisfies this conditions
	 * @param exact
	 *            If true, count exact number of points. If false, count approximate number of points faster. Approximate count might be unreliable during the
	 *            indexing process.
	 * @return
	 */
	default GrpcClientRequest<CountResponse> countPoints(String collectionName, Filter filter, boolean exact) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		Builder request = CountPoints.newBuilder()
			.setCollectionName(collectionName)
			.setExact(exact);

		if (filter != null) {
			request.setFilter(filter);
		}

		return request(
			() -> pointsStub(this).count(request.build()),
			() -> pointsAsyncStub(this).count(request.build()));
	}

	/**
	 * Remove all payload for specified points.
	 *
	 * @param collectionName
	 *            Name of the collection to clear payload from
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @param filter
	 *            Filter to be used to select points
	 * @param ids
	 *            List of point ids to clear payload from
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> clearPayload(String collectionName, boolean wait, Filter filter, PointId... ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		ClearPayloadPoints.Builder request = ClearPayloadPoints.newBuilder()
			.setCollectionName(collectionName)
			.setWait(wait);

		PointsSelector.Builder pointsSelector = PointsSelector.newBuilder();
		if (filter != null) {
			pointsSelector.setFilter(filter);
		}

		if (ids.length > 0) {
			List<PointId> points = Arrays.asList(ids);
			PointsIdsList pointsList = PointsIdsList.newBuilder()
				.addAllIds(points)
				.build();
			pointsSelector.setPoints(pointsList);
		}

		return request(
			() -> pointsStub(this).clearPayload(request.build()),
			() -> pointsAsyncStub(this).clearPayload(request.build()));
	}

	/**
	 * Delete specified key payload for points.
	 * 
	 * @param collectionName
	 *            Name of the collection to delete from
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @param keys
	 *            List of payload keys to remove from payload
	 * @param filter
	 *            Deletes values from points that satisfy this filter condition
	 * @param ids
	 *            Deletes values from each point in this list
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> deletePayload(String collectionName, boolean wait, Set<String> keys, Filter filter,
		PointId... ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(keys, "A set of keys has to be specified");

		DeletePayloadPoints.Builder request = DeletePayloadPoints.newBuilder()
			.setCollectionName(collectionName)
			.addAllKeys(keys)
			.setWait(wait);

		if (ids.length > 0) {
			request.addAllPoints(Arrays.asList(ids));
		}

		if (filter != null) {
			PointsSelector selector = PointsSelector.newBuilder()
				.setFilter(filter)
				.build();
			request.setPointsSelector(selector);
		}

		return request(
			() -> pointsStub(this).deletePayload(request.build()),
			() -> pointsAsyncStub(this).deletePayload(request.build()));
	}

	/**
	 * Replace full payload of points with new one.
	 * 
	 * @param collectionName
	 *            Name of the collection to set from
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @param filter
	 *            Assigns payload to each point that satisfy this filter condition
	 * @param payload
	 *            Payload to be assigned to matching points
	 * @param ids
	 *            Assigns payload to each point in this list
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> overwritePayload(String collectionName, boolean wait, Filter filter,
		Map<String, Value> payload,
		PointId... ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(payload, "A payload must be specified");

		PointsIdsList pointList = PointsIdsList.newBuilder()
			.addAllIds(Arrays.asList(ids))
			.build();

		PointsSelector pointsSelector = PointsSelector.newBuilder()
			.setPoints(pointList)
			.setFilter(filter)
			.build();

		SetPayloadPoints request = SetPayloadPoints.newBuilder()
			.setCollectionName(collectionName)
			.setWait(wait)
			.putAllPayload(payload)
			.setPointsSelector(pointsSelector)
			.build();

		return request(
			() -> pointsStub(this).overwritePayload(request),
			() -> pointsAsyncStub(this).overwritePayload(request));
	}

	/**
	 * Set payload values for points.
	 * 
	 * @param collectionName
	 *            Name of the collection to set from
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @param filter
	 *            Assigns payload to each point that satisfy this filter condition
	 * @param payload
	 *            Payload to assign
	 * @param ids
	 *            Assigns payload to each point in this list
	 * 
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> setPointPayload(String collectionName, boolean wait, Filter filter,
		Map<String, Value> payload,
		PointId... ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(payload, "A payload must be specified");

		PointsIdsList pointList = PointsIdsList.newBuilder()
			.addAllIds(Arrays.asList(ids))
			.build();

		PointsSelector pointsSelector = PointsSelector.newBuilder()
			.setPoints(pointList)
			.setFilter(filter)
			.build();

		SetPayloadPoints request = SetPayloadPoints.newBuilder()
			.setCollectionName(collectionName)
			.setWait(wait)
			.putAllPayload(payload)
			.setPointsSelector(pointsSelector)
			.build();

		return request(
			() -> pointsStub(this).setPayload(request),
			() -> pointsAsyncStub(this).setPayload(request));
	}

	/**
	 * Delete points.
	 * 
	 * @param collectionName
	 *            Name of the collection to delete from
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @param ids
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> deletePoints(String collectionName, boolean wait, PointId... ids) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");

		PointsIdsList pointList = PointsIdsList.newBuilder()
			.addAllIds(Arrays.asList(ids))
			.build();
		PointsSelector points = PointsSelector.newBuilder()
			.setPoints(pointList)
			.build();

		DeletePoints.Builder request = DeletePoints.newBuilder()
			.setCollectionName(collectionName)
			.setPoints(points)
			.setWait(wait);

		return request(
			() -> pointsStub(this).delete(request.build()),
			() -> pointsAsyncStub(this).delete(request.build()));
	}

	/**
	 * Perform insert + updates on point. If point with given ID already exists - it will be overwritten.
	 * 
	 * @param collectionName
	 *            Name of the collection to update from
	 * @param point
	 *            Point to be inserted
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> upsertPoint(String collectionName, PointStruct point, boolean wait) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(point, "The batch of points has to be specified");
		return upsertPoints(collectionName, Arrays.asList(point), wait);
	}

	/**
	 * Perform insert + updates on points. If point with given ID already exists - it will be overwritten.
	 * 
	 * @param collectionName
	 *            Name of the collection to update from
	 * @param points
	 *            Batch of points to be inserted
	 * @param wait
	 *            If true, wait for changes to actually happen
	 * @return
	 */
	default GrpcClientRequest<PointsOperationResponse> upsertPoints(String collectionName, List<? extends PointStruct> points, boolean wait) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
		Objects.requireNonNull(points, "The batch of points has to be specified");

		UpsertPoints.Builder request = UpsertPoints.newBuilder()
			.setCollectionName(collectionName)
			.addAllPoints(points)
			.setWait(wait);

		return request(
			() -> pointsStub(this).upsert(request.build()),
			() -> pointsAsyncStub(this).upsert(request.build()));
	}

}
