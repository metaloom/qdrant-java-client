package io.metaloom.qdrant.client.grcp;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grcp.impl.QDrantGRCPClientImpl;

public interface QDrantGRCPClient extends QDrantGRCPClientMethods, ClientSettings {

	static QDrantGRCPClientImpl.Builder builder() {
		return QDrantGRCPClientImpl.builder();
	}

	/**
	 * Close the prepared transport channel.
	 */
	void close();
}
