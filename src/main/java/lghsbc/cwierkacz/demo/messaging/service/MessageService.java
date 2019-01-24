package lghsbc.cwierkacz.demo.messaging.service;

import lghsbc.cwierkacz.demo.messaging.domain.Message;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class MessageService {

    private final Map<String, List<Message>> messagesByUsers = new ConcurrentHashMap<>();

    public void addNewMessageForUser(String userId, String text) {
        messagesByUsers.putIfAbsent(userId, new CopyOnWriteArrayList<>());

        List<Message> messages = messagesByUsers.get(userId);
        Message message = new Message(userId, text, LocalDateTime.now());
        messages.add(message);
    }

    public List<Message> getMessagesByUser(String userId){
        List<Message> messages = messagesByUsers.getOrDefault(userId, Collections.emptyList());
        return messages.stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
