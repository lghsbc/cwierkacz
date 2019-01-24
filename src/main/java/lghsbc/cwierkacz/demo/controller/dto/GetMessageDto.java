package lghsbc.cwierkacz.demo.controller.dto;

import java.time.LocalDateTime;

public class GetMessageDto {
    private final String userId;
    private final String text;
    private final LocalDateTime createdAt;

    public GetMessageDto(String userId, String text, LocalDateTime createdAt) {
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
