package lghsbc.cwierkacz.demo.messaging.controller;

import lghsbc.cwierkacz.demo.messaging.controller.dto.GetMessageDto;
import lghsbc.cwierkacz.demo.messaging.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoTransformer {
    GetMessageDto toGetMessageDto(Message message) {
        return new GetMessageDto(message.getUserId(), message.getText(), message.getCreatedAt());
    }
}
