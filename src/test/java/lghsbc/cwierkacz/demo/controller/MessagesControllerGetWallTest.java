package lghsbc.cwierkacz.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.common.collect.ImmutableList;
import lghsbc.cwierkacz.demo.configuration.Message;
import lghsbc.cwierkacz.demo.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static lghsbc.cwierkacz.demo.controller.MessagesController.API_ENDPOINT;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessagesControllerGetWallTest {

    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEmptyWall() throws Exception {
        String userId = "owner324";

        mockMvc.perform(get(API_ENDPOINT + "/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    public void shouldReturnWall() throws Exception {
        String userId = "owner324";
        String text = "message";
        LocalDateTime createdAt = LocalDateTime.now();
        when(messageService.getMessagesByUser(userId)).thenReturn(ImmutableList.of(new Message(userId, text, createdAt)));

        mockMvc.perform(get(API_ENDPOINT + "/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", equalTo(userId)))
                .andExpect(jsonPath("$[0].text", equalTo(text)))
                .andExpect(jsonPath("$[0].createdAt", equalTo(createdAt.format(ISO_DATE_TIME))));
    }
}