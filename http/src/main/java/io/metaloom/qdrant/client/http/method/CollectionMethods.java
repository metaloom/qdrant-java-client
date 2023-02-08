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
import io.metaloom.qdrant.client.http.model.collection.CollectionsAliasesListResponse;

/**
 * API methods which are used to interact with collections.
 */
public interface CollectionMethods {

	/**
	 * Get list name of all existing collections.
	 * 
	 * @return
	 */
	QDrantClientRequest<CollectionListResponse> listCollections();

	/**
	 * Get detailed information about specified existing collection.
	 * 
	 * @param collectionName
	 * @return
	 */
	QDrantClientRequest<CollectionResponse> loadCollection(String collectionName);

	/**
	 * Create new collection with given parameters.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> createCollection(String collectionName, CollectionCreateRequest request);

	/**
	 * Update parameters of the existing collection.
	 * 
	 * @param collectionName
	 * @param request
	 * @param timeout
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> updateCollection(String collectionName, CollectionUpdateRequest request, int timeout);

	/**
	 * Drop collection and all associated data.
	 * 
	 * @param collectionName
	 * @param timeout
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> deleteCollection(String collectionName, int timeout);

	/**
	 * Update aliases of the collections.
	 * 
	 * @param collectionName
	 * @param request
	 * @param timeout
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> updateCollectionAliases(String collectionName, CollectionUpdateAliasesRequest request,
		int timeout);

	/**
	 * Get list of all aliases for a collection.
	 * 
	 * @param collectionName
	 * @return
	 */
	QDrantClientRequest<CollectionsAliasesListResponse> listCollectionAliases(String collectionName);

	/**
	 * Create index for field in collection.
	 * 
	 * @param collectionName
	 * @param request
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<CollectionIndexFieldResponse> createCollectionIndexField(String collectionName, CollectionCreateIndexFieldRequest request,
		boolean wait);

	/**
	 * Delete field index for collection.
	 * 
	 * @param collectionName
	 * @param fieldName
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<CollectionIndexFieldResponse> deleteCollectionIndexField(String collectionName, String fieldName, boolean wait);

}
