package lghsbc.cwierkacz.demo.service;

import java.util.List;

import lghsbc.cwierkacz.demo.configuration.Message;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageServiceTest {

    private MessageService underTest = new MessageService();

    @Test
    public void shouldReturnEmptyListIfUserDoesNotHaveMessages(){
        String userId = "inExistentUserId";

        List<Message> result = underTest.getMessagesByUser(userId);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnMessagesForGivenUser(){
        String userIdA = "userA";
        String userIdB = "userB";
        String messageOfUserA = "message1";
        String messageOfUserB = "message2";
        underTest.addNewMessageForUser(userIdA, messageOfUserA);
        underTest.addNewMessageForUser(userIdB, messageOfUserB);

        List<Message> messagesOfUserA = underTest.getMessagesByUser(userIdA);
        List<Message> messagesOfUserB = underTest.getMessagesByUser(userIdB);

        assertThat(messagesOfUserA.stream().map(Message::getText)).containsExactlyInAnyOrder(messageOfUserA);
        assertThat(messagesOfUserB.stream().map(Message::getText)).containsExactlyInAnyOrder(messageOfUserB);
    }

    @Test
    public void shouldAddMessagesToExistentList(){
        String userId = "user";
        String message1 = "message1";
        String message2 = "message2";
        underTest.addNewMessageForUser(userId, message1);

        underTest.addNewMessageForUser(userId, message2);
        List<Message> messages = underTest.getMessagesByUser(userId);

        assertThat(messages.stream().map(Message::getText)).containsExactlyInAnyOrder(message1, message2);
    }

    @Test
    public void shouldGetMessagesInRevetedOrder(){
        String userId = "user";
        String message1 = "message1";
        String message2 = "message2";
        String message3 = "message3";
        underTest.addNewMessageForUser(userId, message1);
        underTest.addNewMessageForUser(userId, message2);
        underTest.addNewMessageForUser(userId, message3);

        List<Message> messages = underTest.getMessagesByUser(userId);

        assertThat(messages.stream().map(Message::getText)).containsExactly(message3, message2, message1);
    }
}