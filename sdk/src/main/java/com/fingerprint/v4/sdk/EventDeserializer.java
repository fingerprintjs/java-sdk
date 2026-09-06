package com.fingerprint.v4.sdk;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fingerprint.v4.model.Event;
import com.fingerprint.v4.model.EventDevice;
import com.fingerprint.v4.model.EventEdge;
import java.io.IOException;

/**
 * Treats a missing, null, or empty Event {@code source} as {@code device}.
 * Unknown non-empty values fail. Never rewrites {@code edge}.
 */
public final class EventDeserializer extends JsonDeserializer<Event> {
  @Override
  public Event deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    JsonNode node = parser.getCodec().readTree(parser);
    if (node == null || !node.isObject()) {
      throw JsonMappingException.from(parser, "event JSON must be an object");
    }

    ObjectNode object = (ObjectNode) node;
    JsonNode source = object.get("source");
    if (source == null || source.isNull() || (source.isTextual() && source.asText().isEmpty())) {
      object.put("source", "device");
      source = object.get("source");
    }

    if (!source.isTextual()) {
      throw JsonMappingException.from(parser, "unknown Event source: " + source);
    }

    String value = source.asText();
    ObjectMapper implMapper = ((ObjectMapper) parser.getCodec()).copy();
    implMapper.addMixIn(Event.class, StripEventPolymorphism.class);
    if ("device".equals(value)) {
      return implMapper.treeToValue(object, EventDevice.class);
    }
    if ("edge".equals(value)) {
      return implMapper.treeToValue(object, EventEdge.class);
    }
    throw JsonMappingException.from(parser, "unknown Event source: " + value);
  }

  @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
  @JsonDeserialize(using = JsonDeserializer.None.class)
  abstract static class StripEventPolymorphism {}
}
