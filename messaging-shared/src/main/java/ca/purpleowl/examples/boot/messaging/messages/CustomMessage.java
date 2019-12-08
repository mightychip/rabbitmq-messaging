package ca.purpleowl.examples.boot.messaging.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class CustomMessage implements Serializable {
    private final String text;
    private final int priority;

    public CustomMessage(@JsonProperty("text") String text,
                         @JsonProperty("priority") int priority) {
        this.text = text;
        this.priority = priority;
    }
}
