package ca.purpleowl.examples.boot.messaging.components;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import ca.purpleowl.examples.boot.messaging.messages.CustomMessage;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class CustomMessageReceiver {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(final CustomMessage message) {
        log.info(String.format("Message received!!  '%s'", message));
    }
}
