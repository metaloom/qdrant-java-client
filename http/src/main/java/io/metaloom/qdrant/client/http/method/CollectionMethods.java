package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateIndexFieldRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionIndexFieldResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionListResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateRequest;

/**
 * API methods which are used to interact with collections.
 */
public interface CollectionMethods {

	QDrantClientRequest<CollectionListResponse> listCollections();

	QDrantClientRequest<CollectionResponse> loadCollection(String collectionName);

	QDrantClientRequest<GenericBooleanStatusResponse> createCollection(String collectionName, CollectionCreateRequest request);

	QDrantClientRequest<GenericBooleanStatusResponse> updateCollection(String collectionName, CollectionUpdateRequest request, int timeout);

	QDrantClientRequest<GenericBooleanStatusResponse> deleteCollection(String collectionName, int timeout);

	QDrantClientRequest<GenericBooleanStatusResponse> updateCollectionAliases(String collectionName, CollectionUpdateAliasesRequest request,
		int timeout) ;

	QDrantClientRequest<CollectionIndexFieldResponse> createCollectionIndexField(String collectionName, CollectionCreateIndexFieldRequest request,
		boolean wait);

	QDrantClientRequest<CollectionIndexFieldResponse> deleteCollectionIndexField(String collectionName, String fieldName, boolean wait);

}
