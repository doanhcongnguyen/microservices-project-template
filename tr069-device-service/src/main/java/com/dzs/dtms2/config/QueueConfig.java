package com.dzs.dtms2.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class QueueConfig {

    private AmqpAdmin amqpAdmin;

    @Value("${rabbit.tr_069_device_service_income_queue}")
    private String incomeQueue;

    public QueueConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct
    public void createQueues() {
        amqpAdmin.declareQueue(new Queue(incomeQueue, true));
    }
}