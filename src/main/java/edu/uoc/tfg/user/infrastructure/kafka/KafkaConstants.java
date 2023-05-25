package edu.uoc.tfg.user.infrastructure.kafka;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class KafkaConstants {
    // topic items
    public static final String TOPIC_SESSION_CRM = "sessions.CRM";
    public static final String TOPIC_SESSION_CORE = "sessions.CORE";
}
