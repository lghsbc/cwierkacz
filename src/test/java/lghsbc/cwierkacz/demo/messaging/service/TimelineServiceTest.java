package lghsbc.cwierkacz.demo.messaging.service;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lghsbc.cwierkacz.demo.messaging.domain.Message;
import lghsbc.cwierkacz.demo.users.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TimelineServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private TimelineService underTest;

    @Test
    public void shouldReturnNoMessagesWhenUserDoesNotFollowAnybody() {
        String user = "user";

        List<Message> followedMessages = underTest.getFollowedMessages(user);

        assertThat(followedMessages).isEmpty();
    }

    @Test
    public void shouldNotIncludeOwnMessages() {
        String user = "user";
        String userA = "userA";
        when(userService.getFollowers(user)).thenReturn(ImmutableSet.of(userA));
        when(messageService.getMessagesByUser(userA)).thenReturn(ImmutableList.of(createMessage(userA)));

        List<Message> followedMessages = underTest.getFollowedMessages(user);

        assertThat(followedMessages).hasSize(1);
        assertThat(followedMessages.stream().map(Message::getUserId)).containsExactlyInAnyOrder(userA);
        verify(messageService, never()).getMessagesByUser(user);
    }

    @Test
    public void shouldReturnMessagesOnlyOfFollowedPeople() {
        String user = "user";
        String userA = "userA";
        String userB = "userB";
        when(userService.getFollowers(user)).thenReturn(ImmutableSet.of(userA));
        when(messageService.getMessagesByUser(userA)).thenReturn(ImmutableList.of(createMessage(userA)));

        List<Message> followedMessages = underTest.getFollowedMessages(user);

        assertThat(followedMessages).hasSize(1);
        assertThat(followedMessages.stream().map(Message::getUserId)).containsExactlyInAnyOrder(userA);
        verify(messageService, never()).getMessagesByUser(userB);
    }

    @Test
    public void shouldReturnMessagesInReverseChronologicalOrder() {
        String user = "user";
        String userA = "userA";
        String userB = "userB";
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        LocalDateTime tomorrow = today.plusDays(1);
        String message1 = "message1";
        String message2 = "message2";
        String message3 = "message3";

        when(userService.getFollowers(user)).thenReturn(ImmutableSet.of(userA, userB));
        when(messageService.getMessagesByUser(userA)).thenReturn(ImmutableList.of(
                createMessage(userA, message1, yesterday),
                createMessage(userA, message3, tomorrow)
        ));
        when(messageService.getMessagesByUser(userB)).thenReturn(ImmutableList.of(createMessage(userB, message2, today)));


        List<Message> followedMessages = underTest.getFollowedMessages(user);


        assertThat(followedMessages.stream().map(Message::getText)).containsExactly(message3, message2, message1);
    }

    private Message createMessage(String userA) {
        return createMessage(userA, RandomStringUtils.random(5), LocalDateTime.now());
    }

    private Message createMessage(String userA, String text, LocalDateTime date) {
        return new Message(userA, text, date);
    }
}