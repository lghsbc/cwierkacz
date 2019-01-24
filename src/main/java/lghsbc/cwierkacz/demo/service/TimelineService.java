package lghsbc.cwierkacz.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lghsbc.cwierkacz.demo.configuration.Message;
import org.springframework.stereotype.Service;

@Service
public class TimelineService {
    private final MessageService messageService;
    private final UserService userService;

    public TimelineService(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    public List<Message> getFollowedMessages(String userId) {
        return userService.getFollowers(userId).stream()
                .flatMap(followedUserId -> messageService.getMessagesByUser(followedUserId).stream())
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
