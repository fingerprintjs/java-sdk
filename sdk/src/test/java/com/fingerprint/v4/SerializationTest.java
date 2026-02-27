package com.fingerprint.v4;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fingerprint.v4.model.Event;
import com.fingerprint.v4.model.EventRuleAction;
import com.fingerprint.v4.sdk.JSON;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SerializationTest {

  private InputStream getFileAsIOStream(final String fileName) {
    InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream(fileName);

    if (ioStream == null) {
      throw new IllegalArgumentException(fileName + " is not found");
    }
    return ioStream;
  }

  private static ObjectMapper getMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    return mapper;
  }

  @Test
  public void deserializeSerializeEvent() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();
    Event event =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_200.json"), Event.class);

    ObjectMapper springLikeObjectMapper = getMapper();
    springLikeObjectMapper.writeValueAsString(event);
  }

  @Test
  public void deserializeEventWithUnknownBotResultValue() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    ObjectNode eventNode =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_200.json"), ObjectNode.class);

    // Set bot to an unknown enum value
    eventNode.put("bot", "unknown_future_value");

    assertDoesNotThrow(
        () -> {
          // Convert the modified ObjectNode back to an Event object to test deserialization
          sdkObjectMapper.treeToValue(eventNode, Event.class);
        });
  }

  @Test
  public void deserializeEventWithUnknownSdkPlatformValue() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    ObjectNode eventNode =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_200.json"), ObjectNode.class);

    eventNode.withObject("/sdk").put("platform", "unknown_future_value");

    assertDoesNotThrow(
        () -> {
          // Convert the modified ObjectNode back to an Event object to test deserialization
          sdkObjectMapper.treeToValue(eventNode, Event.class);
        });
  }

  @Test
  public void deserializeEventWithUnknownRuleActionTypeValue() throws IOException {
    ObjectMapper sdkObjectMapper = JSON.getDefault().getMapper();

    ObjectNode eventNode =
        sdkObjectMapper.readValue(
            getFileAsIOStream("mocks/events/get_event_ruleset_200.json"), ObjectNode.class);

    eventNode.withObject("/rule_action").put("type", "unknown_future_value");

    // Convert the modified ObjectNode back to an Event object to test deserialization
    Event event = sdkObjectMapper.treeToValue(eventNode, Event.class);

    assertInstanceOf(EventRuleAction.UnknownEventRuleAction.class, event.getRuleAction());
  }
}
