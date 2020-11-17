package com.dzs.dtms2.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DIConfig {

    @Value("${rabbit.notification_service_income_queue}")
    private String notificationServiceIncomeQueue;

    @Bean
    public Queue notificationServiceIncomeQueue() {
        return new Queue(notificationServiceIncomeQueue);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
