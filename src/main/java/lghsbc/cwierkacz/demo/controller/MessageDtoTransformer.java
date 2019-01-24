package lghsbc.cwierkacz.demo.controller;

import lghsbc.cwierkacz.demo.configuration.Message;
import lghsbc.cwierkacz.demo.controller.dto.GetMessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoTransformer {
    GetMessageDto toGetMessageDto(Message message) {
        return new GetMessageDto(message.getUserId(), message.getText(), message.getCreatedAt());
    }
}
