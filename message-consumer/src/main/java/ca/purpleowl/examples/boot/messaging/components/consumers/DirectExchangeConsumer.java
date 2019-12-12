package ca.purpleowl.examples.boot.messaging.components.consumers;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log
public class DirectExchangeConsumer {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "onTheFlyQueue"),
                    exchange = @Exchange(name = RabbitMQConfig.DIRECT_EXCHANGE_NAME),
                    key = RabbitMQConfig.SIMPLE_KEY
            ),
            ackMode = "AUTO"
    )
    public void processMessage(final Message message) {
        log.info(String.format("Generic Message received!!  '%s'", message));
    }
}
