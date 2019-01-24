package lghsbc.cwierkacz.demo.configuration;

import java.time.LocalDateTime;

public class Message {
    private final String userId;
    private final String text;
    private final LocalDateTime createdAt;

    public Message(String userId, String text, LocalDateTime createdAt) {
        this.userId = userId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
