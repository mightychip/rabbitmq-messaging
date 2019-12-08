package ca.purpleowl.examples.boot.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String GENERIC_QUEUE_NAME = "genericExampleQueue";
    public static final String QUEUE_NAME = "exampleQueue";
    public static final String EXCHANGE_NAME = "exampleExchange";
    public static final String ROUTING_KEY = "messages.key";

    @Bean
    public TopicExchange exampleTopicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue exampleQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Queue genericExampleQueue() {
        return new Queue(GENERIC_QUEUE_NAME);
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
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
