package ca.purpleowl.examples.boot.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageProducerApplication {
    public static void main(String... args) {
        SpringApplication.run(MessageProducerApplication.class, args);
    }
}
