package edu.uoc.tfg.user.infrastructure.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic showAddTopicCRM() {
        return new NewTopic(KafkaConstants.TOPIC_SESSION_ADD_CRM, 1, (short) 1);
    }

    @Bean
    public NewTopic showAddTopicCORE() {
        return new NewTopic(KafkaConstants.TOPIC_SESSION_ADD_CORE, 1, (short) 1);
    }
}
