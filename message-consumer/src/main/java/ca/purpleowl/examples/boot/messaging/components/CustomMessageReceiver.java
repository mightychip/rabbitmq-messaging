package ca.purpleowl.examples.boot.messaging.components;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import ca.purpleowl.examples.boot.messaging.exceptions.CustomMessageReceiptException;
import ca.purpleowl.examples.boot.messaging.messages.CustomMessage;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class CustomMessageReceiver {
    @RabbitListener(queues = RabbitMQConfig.CUSTOM_QUEUE_NAME,
                    ackMode = "AUTO")
    public void receiveMessage(final CustomMessage message) throws CustomMessageReceiptException {
        log.info(String.format("Message received!!  '%s'", message));

//        if(message.getPriority() > 100) {
//            throw new CustomMessageReceiptException("Priority too high!!");
//        }
    }
}
