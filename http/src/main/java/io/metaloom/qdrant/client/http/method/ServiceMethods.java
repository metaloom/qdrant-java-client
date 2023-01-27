package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.service.LockOptionResponse;
import io.metaloom.qdrant.client.http.model.service.LockRequest;
import io.metaloom.qdrant.client.http.model.service.ServiceTelemetryResponse;

public interface ServiceMethods extends HTTPMethods {

	/**
	 * Collect telemetry data including app info, system info, collections info, cluster info, configs and statistics
	 *
	 * @param anonymize
	 *            If true, anonymize result
	 * @return
	 */
	default RequestBuilder<ServiceTelemetryResponse> collectTelemetry(boolean anonymize) {
		RequestBuilder<ServiceTelemetryResponse> req = getBuilder("telemetry");
		req.addQueryParameter("anonymize", String.valueOf(anonymize));
		return req;
	}

	/**
	 * Set lock options. If write is locked, all write operations and collection creation are forbidden. Returns previous lock options
	 * 
	 * @param errorMessage
	 * @param lockFlag
	 * @return
	 */
	default RequestBuilder<LockOptionResponse> setLockOptions(String errorMessage, boolean lockFlag) {
		LockRequest request = new LockRequest();
		request.setErrorMessage(errorMessage);
		request.setWrite(lockFlag);
		return postBuilder("locks", request);
	}

	/**
	 * Get lock options. If write is locked, all write operations and collection creation are forbidden.
	 *
	 * @return
	 */
	default RequestBuilder<LockOptionResponse> getLockOptions() {
		return getBuilder("locks");
	}
}
