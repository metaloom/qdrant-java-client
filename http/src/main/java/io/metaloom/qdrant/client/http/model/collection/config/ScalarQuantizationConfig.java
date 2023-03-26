package io.metaloom.qdrant.client.http.model.collection.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ScalarQuantizationConfig implements RestModel {

	private String type;

	private float quantile;

	@JsonProperty("always_ram")
	private boolean alwaysRam;

	public String getType() {
		return type;
	}

	public ScalarQuantizationConfig setType(String type) {
		this.type = type;
		return this;
	}

	public float getQuantile() {
		return quantile;
	}

	public ScalarQuantizationConfig setQuantile(float quantile) {
		this.quantile = quantile;
		return this;
	}

	public boolean isAlwaysRam() {
		return alwaysRam;
	}

	public ScalarQuantizationConfig setAlwaysRam(boolean alwaysRam) {
		this.alwaysRam = alwaysRam;
		return this;
	}

}
