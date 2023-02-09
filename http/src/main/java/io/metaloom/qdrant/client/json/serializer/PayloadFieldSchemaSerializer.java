package io.metaloom.qdrant.client.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.metaloom.qdrant.client.http.model.collection.schema.FullTextIndexFieldSchema;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadFieldSchema;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadIndexSchemaType;
import io.metaloom.qdrant.client.http.model.collection.schema.TokenizerType;

public class PayloadFieldSchemaSerializer extends JsonSerializer<PayloadFieldSchema> {

	@Override
	public void serialize(PayloadFieldSchema value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value == null) {
			gen.writeNull();
		} else {
			if (value instanceof PayloadIndexSchemaType) {
				PayloadIndexSchemaType type = ((PayloadIndexSchemaType) value);
				gen.writeString(type.getName());
			} else if (value instanceof FullTextIndexFieldSchema) {
				FullTextIndexFieldSchema schema = ((FullTextIndexFieldSchema) value);
				gen.writeStartObject();
				gen.writeStringField("type", schema.getType());
				gen.writeBooleanField("lowercase", schema.getLowercase());
				TokenizerType tokenizer = schema.getTokenizer();
				gen.writeStringField("tokenizer", tokenizer == null ? null : tokenizer.getName());
				gen.writeNumberField("min_token_len", schema.getMinTokenLen());
				gen.writeNumberField("max_token_len", schema.getMaxTokenLen());
				gen.writeEndObject();
			} else {
				throw new RuntimeException("Encountered unknown field schema type. Got: " + value.getClass().getSimpleName());
			}
		}
	}

}
