package lghsbc.cwierkacz.demo.controller;

import lghsbc.cwierkacz.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static lghsbc.cwierkacz.demo.controller.UsersController.API_ENDPOINT;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSuccessfullyAddFollower() throws Exception {
        String userId = "owner324";
        String followerId = "follower";

        mockMvc.perform(put(API_ENDPOINT + "/" + userId + "/followed")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"followerId\":\"" + followerId + "\"}"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).addFollowers(userId, followerId);
    }

    @Test
    public void shouldRejectInvalidBodyInAddFollower() throws Exception {
        String userId = "owner324";
        String invalidFollower = "";

        mockMvc.perform(put(API_ENDPOINT + "/" + userId + "/followed")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"followerId\":\"" + invalidFollower + "\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(userService, never()).addFollowers(userId, invalidFollower);
    }
}