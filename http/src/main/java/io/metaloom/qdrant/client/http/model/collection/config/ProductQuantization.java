package io.metaloom.qdrant.client.http.model.collection.config;

public class ProductQuantization implements QuantizationConfig {

	private ProductQuantizationConfig product;

	public ProductQuantizationConfig getProduct() {
		return product;
	}

	public ProductQuantization setProduct(ProductQuantizationConfig product) {
		this.product = product;
		return this;
	}
}
