package com.dzs.dtms2.listener;

import com.dzs.dtms2.domain.queue.GeneralMessage;
import com.dzs.dtms2.socket.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NbiListener {

    @Autowired
    private NotificationSender sender;

    @RabbitListener(queues = "${rabbit.notification_service_income_queue}")
    public void receive(GeneralMessage message) {
        log.info("Receive message from North: {}", message);
        sender.dispatchMessage(message);
    }
}
