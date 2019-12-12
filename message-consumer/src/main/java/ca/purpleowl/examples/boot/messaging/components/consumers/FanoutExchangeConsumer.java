package ca.purpleowl.examples.boot.messaging.components.consumers;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log
public class FanoutExchangeConsumer {
    @RabbitListener(queues = RabbitMQConfig.ROOT_TOPIC_QUEUE_NAME)
    public void processRootMessage(Message message) {
        log.info(String.format("Root message receieved!! '%s'", message.toString()));
    }

    @RabbitListener(queues = RabbitMQConfig.LEFT_TOPIC_QUEUE_NAME)
    public void processLeftMessage(Message message) {
        log.info(String.format("Left fanout message receieved!! '%s'", message.toString()));
    }

    @RabbitListener(queues = RabbitMQConfig.RIGHT_TOPIC_QUEUE_NAME)
    public void processRightMessage(Message message) {
        log.info(String.format("Right fanout message receieved!! '%s'", message.toString()));
    }
}
