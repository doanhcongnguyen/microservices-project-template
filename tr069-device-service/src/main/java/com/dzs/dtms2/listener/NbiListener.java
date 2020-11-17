package com.dzs.dtms2.listener;

import com.dzs.dtms2.business.BusinessJob;
import com.dzs.dtms2.domain.queue.GeneralMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NbiListener {

    @Autowired
    private BusinessJob job;

    @RabbitListener(queues = "${rabbit.tr_069_device_service_income_queue}")
    public void receive(GeneralMessage message) {
        log.info("Receive message from North: {}", message);
        job.syncDevice(message);
    }
}

