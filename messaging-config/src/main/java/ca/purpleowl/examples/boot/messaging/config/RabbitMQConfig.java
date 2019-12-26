package ca.purpleowl.examples.boot.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * I guess technically it isn't ideal to have all of your queues, exchanges, etc. defined in a way that they'll be
 * instantiated in every microservice in an application like this... but this is for ease of use.  It's an example.
 */
@Configuration
public class RabbitMQConfig {
    //queue names
    public static final String GENERIC_QUEUE_NAME = "genericExampleQueue";
    public static final String CUSTOM_QUEUE_NAME = "customMessageExampleQueue";
    public static final String ROOT_TOPIC_QUEUE_NAME = "rootFanoutQueue";
    public static final String LEFT_TOPIC_QUEUE_NAME = "leftFanoutQueue";
    public static final String RIGHT_TOPIC_QUEUE_NAME = "rightFanoutQueue";
    public static final String DIRECT_EXCHANGE_QUEUE_NAME = "directExchangeQueueName";

    //exchange names
    public static final String DIRECT_EXCHANGE_NAME = "exampleDirectExchange";
    public static final String TOPIC_EXCHANGE_NAME = "exampleTopicExchange";
    public static final String FANOUT_EXCHANGE_NAME = "exampleFanoutExchange";

    //routing keys
    public static final String ROUTING_KEY = "messages.key";
    public static final String SIMPLE_KEY = "purple.owl";
    public static final String ROOT_TOPIC_KEY = "purple.owl.*";
    public static final String RIGHT_TOPIC_KEY = "purple.owl.right";
    public static final String LEFT_TOPIC_KEY = "purple.owl.left";
    public static final String DIRECT_EXCHANGE_EXAMPLE_KEY = "direct.exchange.example";

    @Bean
    public DirectExchange exampleDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    public TopicExchange exampleTopicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public FanoutExchange exampleFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

    @Bean
    public Queue exampleQueue() {
        return new Queue(CUSTOM_QUEUE_NAME);
    }

    @Bean
    public Queue genericExampleQueue() {
        return new Queue(GENERIC_QUEUE_NAME);
    }

    @Bean
    public Queue rootTopicQueue() {
        return new Queue(ROOT_TOPIC_QUEUE_NAME);
    }

    @Bean
    public Queue rightTopicQueue() {
        return new Queue(RIGHT_TOPIC_QUEUE_NAME);
    }

    @Bean
    public Queue leftTopicQueue() {
        return new Queue(LEFT_TOPIC_QUEUE_NAME);
    }

    @Bean
    public Queue exampleDirectExchangeQueue() {
        return new Queue(DIRECT_EXCHANGE_QUEUE_NAME);
    }


    @Bean
    public Binding exampleBinding() {
        return BindingBuilder.bind(exampleQueue())
                             .to(exampleTopicExchange())
                             .with(ROUTING_KEY);
    }

    @Bean
    public Binding genericExampleBinding() {
        return BindingBuilder.bind(genericExampleQueue())
                             .to(exampleTopicExchange())
                             .with(ROUTING_KEY);
    }

    @Bean
    public Binding rootTopicBinding() {
        return BindingBuilder
                .bind(rootTopicQueue())
                .to(exampleTopicExchange())
                .with(ROOT_TOPIC_KEY);
    }

    @Bean
    public Binding rightTopicBinding() {
        return BindingBuilder
                .bind(rightTopicQueue())
                .to(exampleTopicExchange())
                .with(RIGHT_TOPIC_KEY);
    }

    @Bean
    public Binding leftTopicBinding() {
        return BindingBuilder
                .bind(leftTopicQueue())
                .to(exampleTopicExchange())
                .with(LEFT_TOPIC_KEY);
    }

    @Bean
    public Binding exampleDirectExchangeQueueBinding() {
        return BindingBuilder
                .bind(exampleDirectExchangeQueue())
                .to(exampleDirectExchange())
                .with(DIRECT_EXCHANGE_EXAMPLE_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
