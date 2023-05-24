package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductQuantizationConfig {

	// TODO use CompressionRatio
	private String compression;

	@JsonProperty("always_ram")
	private Boolean alwaysRam;

	public String getCompression() {
		return compression;
	}

	public ProductQuantizationConfig setCompression(String compression) {
		this.compression = compression;
		return this;
	}

	public Boolean getAlwaysRam() {
		return alwaysRam;
	}

	public ProductQuantizationConfig setAlwaysRam(Boolean alwaysRam) {
		this.alwaysRam = alwaysRam;
		return this;
	}

}
