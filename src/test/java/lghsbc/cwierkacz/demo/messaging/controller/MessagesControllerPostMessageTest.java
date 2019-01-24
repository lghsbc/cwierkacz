package lghsbc.cwierkacz.demo.messaging.controller;

import lghsbc.cwierkacz.demo.messaging.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static lghsbc.cwierkacz.demo.messaging.controller.MessagesController.API_ENDPOINT;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessagesControllerPostMessageTest {

    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPostMessageSuccessfully() throws Exception {
        String userId = "owner324";
        String text = "message";

        mockMvc.perform(post(API_ENDPOINT + "/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"" + text + "\"}"))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(messageService).addNewMessageForUser(userId, text);
    }

    @Test
    public void shouldRejectInvalidBodyInPostMessage() throws Exception {
        String userId = "userId";
        String invalidText = "";

        mockMvc.perform(post(API_ENDPOINT + "/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"" + invalidText + "\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(messageService, never()).addNewMessageForUser(anyString(), anyString());
    }
}