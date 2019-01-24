package lghsbc.cwierkacz.demo.messaging.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lghsbc.cwierkacz.demo.messaging.controller.dto.GetMessageDto;
import lghsbc.cwierkacz.demo.messaging.controller.dto.PostMessageDto;
import lghsbc.cwierkacz.demo.messaging.service.MessageService;
import lghsbc.cwierkacz.demo.messaging.service.TimelineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static lghsbc.cwierkacz.demo.messaging.controller.MessagesController.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
@Api(description = "Sets of endpoints for messages")
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
    @ApiOperation("Creates new message for user.")
    public void postMessage(@PathVariable String userId, @RequestBody @Valid PostMessageDto messageDto) {
        messageService.addNewMessageForUser(userId, messageDto.getText());
    }

    @GetMapping("{userId}")
    @ApiOperation("Returns all user's messages in reverse chronological order")
    public List<GetMessageDto> getMessagesFromWall(@PathVariable String userId) {
        return messageService.getMessagesByUser(userId).stream()
                .map(transformer::toGetMessageDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{userId}/following")
    @ApiOperation("Returns all messages posted by other users who are followed by user in reverse chronological order.")
    public List<GetMessageDto> getFollowedMessages(@PathVariable String userId){
        return timelineService.getFollowedMessages(userId).stream()
                .map(transformer::toGetMessageDto)
                .collect(Collectors.toList());
    }
}