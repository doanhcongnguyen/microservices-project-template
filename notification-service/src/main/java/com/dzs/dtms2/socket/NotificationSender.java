package com.dzs.dtms2.socket;

import com.dzs.dtms2.domain.queue.GeneralMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class NotificationSender {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private Environment env;

    public void dispatchMessage(GeneralMessage message) {
        log.info("Send to web socket: {}", message);
        // TODO: need send to user here..
//        messagingTemplate.convertAndSendToUser(CommonUtils.getLoggedInUsername(), "/queue/notification", message);
        messagingTemplate.convertAndSend(env.getProperty("web_socket.notification_topic_name"), message);
    }
}
