package lghsbc.cwierkacz.demo.messaging.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class PostMessageDto {

    @NotNull
    @Size(min = 1, max = 140)
    private final String text;

    @JsonCreator
    PostMessageDto(@JsonProperty("text") String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
