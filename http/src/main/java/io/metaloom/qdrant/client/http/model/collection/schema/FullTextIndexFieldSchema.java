package io.metaloom.qdrant.client.http.model.collection.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FullTextIndexFieldSchema implements PayloadFieldSchema {

	@JsonProperty("type")
	private String type;

	@JsonProperty("tokenizer")
	private TokenizerType tokenizer;

	@JsonProperty("min_token_len")
	private Integer minTokenLen;

	@JsonProperty("max_token_len")
	private Integer maxTokenLen;

	private Boolean lowercase;

	public String getType() {
		return type;
	}

	public FullTextIndexFieldSchema setType(String type) {
		this.type = type;
		return this;
	}

	public TokenizerType getTokenizer() {
		return tokenizer;
	}

	public FullTextIndexFieldSchema setTokenizer(TokenizerType tokenizer) {
		this.tokenizer = tokenizer;
		return this;
	}

	public Integer getMinTokenLen() {
		return minTokenLen;
	}

	public FullTextIndexFieldSchema setMinTokenLen(Integer minTokenLen) {
		this.minTokenLen = minTokenLen;
		return this;
	}

	public Integer getMaxTokenLen() {
		return maxTokenLen;
	}

	public FullTextIndexFieldSchema setMaxTokenLen(Integer maxTokenLen) {
		this.maxTokenLen = maxTokenLen;
		return this;
	}

	public Boolean getLowercase() {
		return lowercase;
	}

	public FullTextIndexFieldSchema setLowercase(Boolean lowercase) {
		this.lowercase = lowercase;
		return this;
	}

}
