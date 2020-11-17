package com.dzs.dtms2.dispatcher;

import com.dzs.dtms2.domain.queue.GeneralMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageDispatcher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Jackson2JsonMessageConverter converter;

    @Autowired
    private Queue notificationServiceIncomeQueue;

    public void sendMessageToDeviceService(GeneralMessage message) {
        log.info("Sent response message to Notification service: {}", message);
        this.template.convertAndSend(notificationServiceIncomeQueue.getName(),
                converter.toMessage(message, this.createMessageProperties()));
    }

    // TODO: fix error: Could not convert incoming message with content-type [null], 'json' keyword missing
    public MessageProperties createMessageProperties() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        return messageProperties;
    }
}