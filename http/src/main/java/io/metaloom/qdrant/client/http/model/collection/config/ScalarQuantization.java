package io.metaloom.qdrant.client.http.model.collection.config;

public class ScalarQuantization implements QuantizationConfig {

	private ScalarQuantizationConfig scalar;

	public ScalarQuantizationConfig getScalar() {
		return scalar;
	}

	public ScalarQuantization setScalar(ScalarQuantizationConfig scalar) {
		this.scalar = scalar;
		return this;
	}
}
