package ca.purpleowl.examples.boot.messaging.components;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class GenericMessageReceiver {
    @RabbitListener(queues = RabbitMQConfig.GENERIC_QUEUE_NAME)
    public void receiveMessage(final Message message) {
        log.info(String.format("Generic Message received!!  '%s'", message));
    }
}
