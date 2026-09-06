package com.fingerprint.v4.sdk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EventDeserializer.class)
public abstract class EventMixin {}
