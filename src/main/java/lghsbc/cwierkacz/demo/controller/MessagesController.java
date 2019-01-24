package lghsbc.cwierkacz.demo.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import lghsbc.cwierkacz.demo.controller.dto.GetMessageDto;
import lghsbc.cwierkacz.demo.controller.dto.PostMessageDto;
import lghsbc.cwierkacz.demo.service.MessageService;
import lghsbc.cwierkacz.demo.service.TimelineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lghsbc.cwierkacz.demo.controller.MessagesController.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class MessagesController {

    static final String API_ENDPOINT = "/messages";
    private final MessageService messageService;
    private final MessageDtoTransformer transformer;
    private final TimelineService timelineService;

    public MessagesController(MessageService messageService, MessageDtoTransformer transformer, TimelineService timelineService) {
        this.messageService = messageService;
        this.transformer = transformer;
        this.timelineService = timelineService;
    }

    @PostMapping("{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void postMessage(@PathVariable String userId, @RequestBody @Valid PostMessageDto messageDto) {
        messageService.addNewMessageForUser(userId, messageDto.getText());
    }

    @GetMapping("{userId}")
    public List<GetMessageDto> getMessagesFromWall(@PathVariable String userId) {
        return messageService.getMessagesByUser(userId).stream()
                .map(transformer::toGetMessageDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{userId}/following")
    public List<GetMessageDto> getFollowedMessages(@PathVariable String userId){
        return timelineService.getFollowedMessages(userId).stream()
                .map(transformer::toGetMessageDto)
                .collect(Collectors.toList());
    }
}