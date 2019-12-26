package ca.purpleowl.examples.boot.messaging.rest.controllers;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import ca.purpleowl.examples.boot.messaging.messages.CustomMessage;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/direct")
    public ResponseEntity<CustomMessage> sendMessage() {
        CustomMessage message = new CustomMessage("This is a test message...", 420);

        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_NAME, RabbitMQConfig.SIMPLE_KEY, message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/fanout")
    public void doFanout() {
        CustomMessage leftMessage = new CustomMessage("This is a test message... sent left", 420);
        CustomMessage rightMessage = new CustomMessage("This is a test message... send right", 710);

        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.LEFT_TOPIC_KEY, leftMessage);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.RIGHT_TOPIC_KEY, rightMessage);

    }
}
