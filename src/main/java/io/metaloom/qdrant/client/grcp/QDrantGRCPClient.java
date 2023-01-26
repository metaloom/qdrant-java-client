package io.metaloom.qdrant.client.grcp;

import io.metaloom.qdrant.client.grcp.impl.QDrantGRCPClientImpl;
import io.metaloom.qdrant.client.http.ClientSettings;

public interface QDrantGRCPClient extends QDrantGRCPClientMethods, ClientSettings {

	static QDrantGRCPClientImpl.Builder builder() {
		return QDrantGRCPClientImpl.builder();
	}

	/**
	 * Close the prepared transport channel.
	 */
	void close();
}
