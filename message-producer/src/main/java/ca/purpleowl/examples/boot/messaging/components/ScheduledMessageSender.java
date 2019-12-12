package ca.purpleowl.examples.boot.messaging.components;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import ca.purpleowl.examples.boot.messaging.messages.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ScheduledMessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ScheduledMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        final var message = new CustomMessage("This is a test", 420);
        sendMessage(message);
    }

    private void sendMessage(Serializable message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.GENERIC_QUEUE_NAME, message);
    }
}
