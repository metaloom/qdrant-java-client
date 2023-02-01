package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.service.LockOptionResponse;
import io.metaloom.qdrant.client.http.model.service.ServiceTelemetryResponse;

public interface ServiceMethods {

	/**
	 * Collect telemetry data including app info, system info, collections info, cluster info, configs and statistics.
	 *
	 * @param anonymize
	 *            If true, anonymize result
	 * @return
	 */
	QDrantClientRequest<ServiceTelemetryResponse> collectTelemetry(boolean anonymize);

	/**
	 * Set lock options. If write is locked, all write operations and collection creation are forbidden. Returns previous lock options.
	 * 
	 * @param errorMessage
	 * @param lockFlag
	 * @return
	 */
	QDrantClientRequest<LockOptionResponse> setLockOptions(String errorMessage, boolean lockFlag);

	/**
	 * Get lock options. If write is locked, all write operations and collection creation are forbidden.
	 *
	 * @return
	 */
	QDrantClientRequest<LockOptionResponse> getLockOptions();
}
