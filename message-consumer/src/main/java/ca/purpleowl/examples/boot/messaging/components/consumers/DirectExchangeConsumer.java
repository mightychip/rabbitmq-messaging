package ca.purpleowl.examples.boot.messaging.components.consumers;

import ca.purpleowl.examples.boot.messaging.config.RabbitMQConfig;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * This is an example of consumers for a Direct Exchange.  Some are created completely with annotations, others will
 * depend on bindings and queues created in {@link RabbitMQConfig}.  Ideally, you should pick one or the other (using
 * a @Configuration annotated class or using annotation configuration in the Consumer).
 */
@Component
@Log
public class DirectExchangeConsumer {
    /**
     * This listener processes {@link Message} instances from the "onTheFlueQueue" queue.  For this situation, we use
     * the queue for simple Broadcasts.  There are, however, more advanced uses possible for Direct Exchanges.
     *
     * Here, we use the {@link QueueBinding} annotation to bind a queue which does not yet exist to an already
     * existing exchange.  Neither the Exchange nor the Queue need to already exist... they'll be declared by default,
     * but I wanted to reuse an existing exchange here to demonstrate two distinct queues bound to a single Direct
     * Exchange.
     *
     * The queue is specified with the {@link Queue} annotation.  Providing a name makes the queue durable by default,
     * and not providing a name creates a random name and a non-durable queue by default.  I felt like that was
     * particularly important.
     *
     * The exchange is specified via the {@link Exchange} annotation.  This creates the exchange if it doesn't exist,
     * but in this particular case the exchange already exists.
     *
     * This is bound using a Routing Key without wildcards.
     *
     * @param message - A {@link Message} instance from the queue to be processed.
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "onTheFlyQueue"),
                    exchange = @Exchange(name = RabbitMQConfig.DIRECT_EXCHANGE_NAME),
                    key = RabbitMQConfig.SIMPLE_KEY
            ),
            ackMode = "AUTO"
    )
    public void processMessageFromAnnotationConfiguredQueue(final Message message) {
        log.info(String.format("Generic Message received from annotation-configured queue!!  '%s'", message));
    }

    /**
     * This listener uses an already bound queue.  When you use a Queue that's already been bound, you just need to
     * specify the name.  If you'd like to consume messages from multiple queues, you can do that by providing an array
     * of Queue names.
     *
     * @param message - A {@link Message} instance from the queue to be processed.
     */
    @RabbitListener(queues = RabbitMQConfig.DIRECT_EXCHANGE_QUEUE_NAME)
    public void processMessageFromPreconfiguredQueue(final Message message) {
        log.info(String.format("Generic Message received from pre-configured queue!! %s", message));
    }
}
