package io.metaloom.qdrant.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.metaloom.qdrant.client.http.model.collection.AliasOperation;
import io.metaloom.qdrant.client.http.model.collection.CreateAliasOperation;
import io.metaloom.qdrant.client.http.model.collection.DeleteAliasOperation;
import io.metaloom.qdrant.client.http.model.collection.RenameAliasOperation;

public class AliasOperationDeserializer extends JsonDeserializer<AliasOperation> {

	@Override
	public AliasOperation deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

		if (node.has("create_alias")) {
			return mapper.convertValue(node, CreateAliasOperation.class);
		}
		if (node.has("rename_alias")) {
			return mapper.convertValue(node, RenameAliasOperation.class);
		}
		if (node.has("delete_alias")) {
			return mapper.convertValue(node, DeleteAliasOperation.class);
		}
		throw new RuntimeException(
			"Error while deserializing JSON. Unable to find match alias operation implementation for " + node.toPrettyString());
	}

}
