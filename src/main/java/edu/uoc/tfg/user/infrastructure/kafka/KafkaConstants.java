package edu.uoc.tfg.user.infrastructure.kafka;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class KafkaConstants {
    // topic items
    public static final String TOPIC_SESSION_ADD_CRM = "sessions.add.CRM";
    public static final String TOPIC_SESSION_ADD_CORE = "sessions.add.CORE";


}
