package io.metaloom.qdrant.client.http.impl;

import static io.metaloom.qdrant.client.http.QDrantClientRequest.DELETE;
import static io.metaloom.qdrant.client.http.QDrantClientRequest.GET;
import static io.metaloom.qdrant.client.http.QDrantClientRequest.PATCH;
import static io.metaloom.qdrant.client.http.QDrantClientRequest.POST;
import static io.metaloom.qdrant.client.http.QDrantClientRequest.PUT;

import java.time.Duration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.metaloom.qdrant.client.http.AbstractQDrantClient;
import io.metaloom.qdrant.client.http.QDrantBinaryResponse;
import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.QDrantHttpClient;
import io.metaloom.qdrant.client.http.model.CollectionClusterInfoResponse;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.RestResponse;
import io.metaloom.qdrant.client.http.model.cluster.ClusterStatusResponse;
import io.metaloom.qdrant.client.http.model.cluster.CollectionUpdateClusterSetupRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionListResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionsAliasesListResponse;
import io.metaloom.qdrant.client.http.model.collection.schema.CollectionCreateIndexFieldRequest;
import io.metaloom.qdrant.client.http.model.collection.schema.CollectionIndexFieldResponse;
import io.metaloom.qdrant.client.http.model.point.DeleteVectorsResponse;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointDeleteVectorsRequest;
import io.metaloom.qdrant.client.http.model.point.PointGetResponse;
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
import io.metaloom.qdrant.client.http.model.service.LockOptionResponse;
import io.metaloom.qdrant.client.http.model.service.LockRequest;
import io.metaloom.qdrant.client.http.model.service.ServiceTelemetryResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotCreateResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotListResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotRecoverRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotResponse;
import okhttp3.OkHttpClient;

/**
 * Implementation for the {@link QDrantHttpClient}
 */
public class QDrantHttpClientImpl extends AbstractQDrantClient {

	public static final Logger log = LoggerFactory.getLogger(QDrantHttpClientImpl.class);

	public static Builder builder() {
		return new Builder();
	}

	private OkHttpClient okClient;

	/**
	 * Create a new client with a default timeout of 10s for all timeouts (connect, read and write).
	 * 
	 * @param okClient
	 * @param scheme
	 * @param hostname
	 * @param port
	 * @param connectTimeout
	 * @param readTimeout
	 * @param writeTimeout
	 */
	protected QDrantHttpClientImpl(OkHttpClient okClient, String scheme, String hostname, int port, Duration connectTimeout, Duration readTimeout,
		Duration writeTimeout) {
		super(scheme, hostname, port, connectTimeout, readTimeout, writeTimeout);
		this.okClient = okClient;
	}

	/**
	 * @deprecated No longer needed. OkHttpClient will be initialized in the builder
	 */
	@Deprecated
	public void init() {
	}

	/**
	 * Return the used OK HTTP client.
	 * 
	 * @return
	 */
	public OkHttpClient getOkHttpClient() {
		return okClient;
	}

	@Override
	public void close() {
		// Not needed for OkClient
	}

	public static class Builder {

		private String scheme = "http";
		private String hostname = "localhost";
		private int port = 6333;

		private Duration connectTimeout = Duration.ofMillis(10_000);
		private Duration readTimeout = Duration.ofMillis(10_000);
		private Duration writeTimeout = Duration.ofMillis(10_000);
		private OkHttpClient okClient;

		/**
		 * Verify the builder and build the client.
		 * 
		 * @return
		 */
		public QDrantHttpClientImpl build() {
			Objects.requireNonNull(scheme, "A protocol scheme has to be specified.");
			Objects.requireNonNull(hostname, "A hostname has to be specified.");

			if (okClient == null) {
				okClient = createDefaultOkHttpClient();
			}

			return new QDrantHttpClientImpl(okClient, scheme, hostname, port, connectTimeout, readTimeout, writeTimeout);
		}

		private OkHttpClient createDefaultOkHttpClient() {
			okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.connectTimeout(connectTimeout);
			builder.readTimeout(readTimeout);
			builder.writeTimeout(writeTimeout);
			// // Disable gzip
			// builder.addInterceptor(chain -> {
			// Request request = chain.request();
			// Request newRequest;
			// try {
			// newRequest = request.newBuilder().addHeader("Accept-Encoding", "identity").build();
			// } catch (Exception e) {
			// log.error("Error while creating new request", e);
			// return chain.proceed(request);
			// }
			// return chain.proceed(newRequest);
			// });
			return builder.build();
		}

		/**
		 * Set the protocol scheme to be used for the client (e.g.: http, https).
		 * 
		 * @param scheme
		 * @return Fluent API
		 */
		public Builder setScheme(String scheme) {
			this.scheme = scheme;
			return this;
		}

		/**
		 * Set the hostname for the client.
		 * 
		 * @param hostname
		 * @return Fluent API
		 */
		public Builder setHostname(String hostname) {
			this.hostname = hostname;
			return this;
		}

		/**
		 * Set a custom http client to be used. A default client will be generated if non is specified.
		 * 
		 * @param okClient
		 * @return
		 */
		public Builder setOkHttpClient(OkHttpClient okClient) {
			this.okClient = okClient;
			return this;
		}

		/**
		 * Set the port to connect to. (e.g. 6333).
		 * 
		 * @param port
		 * @return Fluent API
		 */
		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		/**
		 * Set connection timeout.
		 * 
		 * @param connectTimeout
		 * @return Fluent API
		 */
		public Builder setConnectTimeout(Duration connectTimeout) {
			if (okClient != null) {
				throw new RuntimeException("Please configure the timeout on the okHttpClient you provided.");
			}
			this.connectTimeout = connectTimeout;
			return this;
		}

		/**
		 * Set read timeout for the client.
		 * 
		 * @param readTimeout
		 * @return Fluent API
		 */
		public Builder setReadTimeout(Duration readTimeout) {
			if (okClient != null) {
				throw new RuntimeException("Please configure the timeout on the okHttpClient you provided.");
			}
			this.readTimeout = readTimeout;
			return this;
		}

		/**
		 * Set write timeout for the client.
		 * 
		 * @param writeTimeout
		 * @return Fluent API
		 */
		public Builder setWriteTimeout(Duration writeTimeout) {
			if (okClient != null) {
				throw new RuntimeException("Please configure the timeout on the okHttpClient you provided.");
			}
			this.writeTimeout = writeTimeout;
			return this;
		}

	}

	// REST Methods
	@Override
	public QDrantClientRequest<PointGetResponse> getPoint(String collectionName, String pointId) {
		assertCollectionName(collectionName);
		return getRequest("collections/" + collectionName + "/points/" + pointId, PointGetResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsGetResponse> getPoints(String collectionName, PointsGetRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points", request, PointsGetResponse.class);
	}

	@Override
	public QDrantClientRequest<UpdateResultResponse> upsertPoints(String collectionName, PointsUpsertRequest request, boolean wait) {
		assertCollectionName(collectionName);
		QDrantClientRequest<UpdateResultResponse> req = putRequest("collections/" + collectionName + "/points", request, UpdateResultResponse.class);
		return req.addWait(wait);
	}

	@Override
	public QDrantClientRequest<UpdateResultResponse> deletePoints(String collectionName, PointsDeleteRequest request, boolean wait) {
		assertCollectionName(collectionName);
		QDrantClientRequest<UpdateResultResponse> req = postRequest("collections/" + collectionName + "/points/delete", request,
			UpdateResultResponse.class);
		return req.addWait(wait);
	}

	@Override
	public QDrantClientRequest<UpdateVectorsResponse> updateVectors(String collectionName, PointUpdateVectorsRequest request, boolean wait,
		WriteOrdering ordering) {
		QDrantClientRequest<UpdateVectorsResponse> req = putRequest("collections/" + collectionName + "/points/vectors", request,
			UpdateVectorsResponse.class);
		return req.addWriteOrdering(ordering);
	}

	@Override
	public QDrantClientRequest<DeleteVectorsResponse> deleteVectors(String collectionName, PointDeleteVectorsRequest request, boolean wait,
		WriteOrdering ordering) {
		QDrantClientRequest<DeleteVectorsResponse> req = postRequest("collections/" + collectionName + "/points/vectors/delete", request,
			DeleteVectorsResponse.class);
		return req.addWait(wait);
	}

	@Override
	public QDrantClientRequest<UpdateResultResponse> setPointPayload(String collectionName, PointSetPayloadRequest request, boolean wait) {
		assertCollectionName(collectionName);
		QDrantClientRequest<UpdateResultResponse> req = postRequest("collections/" + collectionName + "/points/payload", request,
			UpdateResultResponse.class);
		return req.addWait(wait);
	}

	@Override
	public QDrantClientRequest<UpdateResultResponse> overwritePayload(String collectionName, PointOverwritePayloadRequest request, boolean wait) {
		assertCollectionName(collectionName);
		QDrantClientRequest<UpdateResultResponse> req = putRequest("collections/" + collectionName + "/points/payload", request,
			UpdateResultResponse.class);
		return req.addWait(wait);
	}

	@Override
	public QDrantClientRequest<UpdateResultResponse> deletePayload(String collectionName, PointDeletePayloadRequest request, boolean wait) {
		assertCollectionName(collectionName);
		Objects.requireNonNull(request.getKeys(), "Keys must be specified");
		return postRequest("collections/" + collectionName + "/points/payload/delete", request, UpdateResultResponse.class);
	}

	@Override
	public QDrantClientRequest<UpdateResultResponse> clearPayload(String collectionName, PointsClearPayloadRequest request, boolean wait) {
		assertCollectionName(collectionName);
		QDrantClientRequest<UpdateResultResponse> req = postRequest("collections/" + collectionName + "/points/payload/clear", request,
			UpdateResultResponse.class);
		return req.addWait(wait);
	}

	@Override
	public QDrantClientRequest<PointsScrollResponse> scrollPoints(String collectionName, PointsScrollRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/scroll", request, PointsScrollResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsSearchResponse> searchPoints(String collectionName, PointsSearchRequest request) {
		assertCollectionName(collectionName);
		Objects.requireNonNull(request.getVector(), "A vector must be specified to run the search");
		Objects.requireNonNull(request.getLimit(), "A limit must be specified");
		return postRequest("collections/" + collectionName + "/points/search", request, PointsSearchResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsSearchBatchResponse> searchBatchPoints(String collectionName, PointsSearchBatchRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/search/batch", request, PointsSearchBatchResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsSearchGroupResponse> searchGroupPoints(String collectionName, PointsSearchGroupRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/search/groups", request, PointsSearchGroupResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsRecommendResponse> recommendPoints(String collectionName, PointsRecommendRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/recommend", request, PointsRecommendResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsRecommendBatchResponse> recommendBatchPoints(String collectionName, PointsRecommendBatchRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/recommend/batch", request, PointsRecommendBatchResponse.class);
	}

	@Override
	public QDrantClientRequest<PointsRecommendGroupResponse> recommendGroupPoints(String collectionName, PointsRecommendGroupRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/recommend/groups", request, PointsRecommendGroupResponse.class);
	}

	@Override
	public QDrantClientRequest<PointCountResponse> countPoints(String collectionName, PointCountRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/points/count", request, PointCountResponse.class);
	}

	@Override
	public QDrantClientRequest<ClusterStatusResponse> getClusterStatusInfo() {
		return getRequest("cluster", ClusterStatusResponse.class);
	}

	@Override
	public QDrantClientRequest<GenericBooleanStatusResponse> removePeerFromCluster(String peerId, boolean force) {
		QDrantClientRequest<GenericBooleanStatusResponse> request = getRequest("cluster/peer/" + peerId, GenericBooleanStatusResponse.class);
		return request.addForce(force);
	}

	@Override
	public QDrantClientRequest<CollectionClusterInfoResponse> getCollectionClusterInfo(String collectionName) {
		assertCollectionName(collectionName);
		return getRequest("collections/" + collectionName + "/cluster", CollectionClusterInfoResponse.class);
	}

	@Override
	public QDrantClientRequest<GenericBooleanStatusResponse> updateCollectionClusterSetup(String collectionName,
		CollectionUpdateClusterSetupRequest request) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/cluster", request, GenericBooleanStatusResponse.class);
	}

	// Snapshot methods

	@Override
	public QDrantClientRequest<SnapshotListResponse> listCollectionSnapshots(String collectionName) {
		assertCollectionName(collectionName);
		return getRequest("collections/" + collectionName + "/snapshots", SnapshotListResponse.class);
	}

	@Override
	public QDrantClientRequest<GenericBooleanStatusResponse> deleteCollectionSnapshot(String collectionName, String snapshotName) {
		assertCollectionName(collectionName);
		return deleteRequest("collections/" + collectionName + "/snapshots/" + snapshotName, GenericBooleanStatusResponse.class);
	}

	@Override
	public QDrantClientRequest<GenericBooleanStatusResponse> recoverSnapshot(String collectionName, SnapshotRecoverRequest request) {
		assertCollectionName(collectionName);
		Objects.requireNonNull(request, "A recovery request must be provided");
		return putRequest("collections/" + collectionName + "/snapshots/recover", request, GenericBooleanStatusResponse.class);
	}

	@Override
	public QDrantClientRequest<SnapshotResponse> createCollectionSnapshot(String collectionName) {
		assertCollectionName(collectionName);
		return postRequest("collections/" + collectionName + "/snapshots", SnapshotResponse.class);
	}

	@Override
	public QDrantClientRequest<QDrantBinaryResponse> downloadCollectionSnapshot(String collectionName, String snapshotName) {
		assertCollectionName(collectionName);
		return getRequest("collections/" + collectionName + "/snapshots/" + snapshotName, QDrantBinaryResponse.class);
	}

	// Storage

	@Override
	public QDrantClientRequest<SnapshotListResponse> listStorageSnapshots() {
		return getRequest("snapshots", SnapshotListResponse.class);
	}

	@Override
	public QDrantClientRequest<SnapshotCreateResponse> createStorageSnapshot() {
		return postRequest("snapshots", SnapshotCreateResponse.class);
	}

	@Override
	public QDrantClientRequest<QDrantBinaryResponse> downloadStorageSnapshot(String snapshotName) {
		return getRequest("snapshots/" + snapshotName, QDrantBinaryResponse.class);
	}

	// Service

	@Override
	public QDrantClientRequest<ServiceTelemetryResponse> collectTelemetry() {
		return getRequest("telemetry", ServiceTelemetryResponse.class);
	}

	@Override
	public QDrantClientRequest<LockOptionResponse> setLockOptions(String errorMessage, boolean lockFlag) {
		LockRequest request = new LockRequest();
		request.setErrorMessage(errorMessage);
		request.setWrite(lockFlag);
		return postRequest("locks", request, LockOptionResponse.class);
	}

	@Override
	public QDrantClientRequest<LockOptionResponse> getLockOptions() {
		return getRequest("locks", LockOptionResponse.class);
	}

	// Collection Methods

	public QDrantClientRequest<CollectionListResponse> listCollections() {
		return getRequest("collections", CollectionListResponse.class);
	}

	public QDrantClientRequest<CollectionResponse> loadCollection(String collectionName) {
		return getRequest("collections/" + collectionName, CollectionResponse.class);
	}

	public QDrantClientRequest<GenericBooleanStatusResponse> createCollection(String collectionName, CollectionCreateRequest request) {
		return putRequest("collections/" + collectionName, request, GenericBooleanStatusResponse.class);
	}

	public QDrantClientRequest<GenericBooleanStatusResponse> updateCollection(String collectionName, CollectionUpdateRequest request, int timeout) {
		QDrantClientRequest<GenericBooleanStatusResponse> req = patchRequest("collections/" + collectionName, request,
			GenericBooleanStatusResponse.class);
		return req.addTimeout(timeout);
	}

	public QDrantClientRequest<GenericBooleanStatusResponse> deleteCollection(String collectionName, int timeout) {
		QDrantClientRequest<GenericBooleanStatusResponse> req = deleteRequest("collections/" + collectionName, GenericBooleanStatusResponse.class);
		return req.addTimeout(timeout);
	}

	public QDrantClientRequest<GenericBooleanStatusResponse> updateCollectionAliases(String collectionName, CollectionUpdateAliasesRequest request,
		int timeout) {
		QDrantClientRequest<GenericBooleanStatusResponse> req = postRequest("collections/aliases", request, GenericBooleanStatusResponse.class);
		return req.addTimeout(timeout);
	}

	public QDrantClientRequest<CollectionsAliasesListResponse> listCollectionAliases(String collectionName) {
		assertCollectionName(collectionName);
		return getRequest("collections/" + collectionName + "/aliases", CollectionsAliasesListResponse.class);
	}

	public QDrantClientRequest<CollectionIndexFieldResponse> createCollectionIndexField(String collectionName,
		CollectionCreateIndexFieldRequest request, boolean wait) {
		QDrantClientRequest<CollectionIndexFieldResponse> req = putRequest("collections/" + collectionName + "/index", request,
			CollectionIndexFieldResponse.class);
		return req.addWait(wait);
	}

	public QDrantClientRequest<CollectionIndexFieldResponse> deleteCollectionIndexField(String collectionName, String fieldName, boolean wait) {
		QDrantClientRequest<CollectionIndexFieldResponse> req = deleteRequest("collections/" + collectionName + "/index/" + fieldName,
			CollectionIndexFieldResponse.class);
		return req.addWait(wait);
	}

	private <T extends RestResponse> QDrantClientRequest<T> deleteRequest(String path, Class<T> responseClass) {
		return QDrantClientRequest.create(DELETE, path, this, okClient, responseClass);
	}

	private <T extends RestResponse> QDrantClientRequest<T> getRequest(String path, Class<T> responseClass) {
		return QDrantClientRequest.create(GET, path, this, okClient, responseClass);
	}

	private <T extends RestResponse> QDrantClientRequest<T> postRequest(String path, RestRequestModel request, Class<T> responseClass) {
		return QDrantClientRequest.create(POST, path, this, okClient, request, responseClass);
	}

	private <T extends RestResponse> QDrantClientRequest<T> postRequest(String path, Class<T> responseClass) {
		return QDrantClientRequest.create(POST, path, this, okClient, responseClass);
	}

	private <T extends RestResponse> QDrantClientRequest<T> putRequest(String path, RestRequestModel request, Class<T> responseClass) {
		return QDrantClientRequest.create(PUT, path, this, okClient, request, responseClass);
	}

	private <T extends RestResponse> QDrantClientRequest<T> patchRequest(String path, RestRequestModel request, Class<T> responseClass) {
		return QDrantClientRequest.create(PATCH, path, this, okClient, request, responseClass);
	}

	private void assertCollectionName(String collectionName) {
		Objects.requireNonNull(collectionName, "A collection name must be specified");
	}

}
