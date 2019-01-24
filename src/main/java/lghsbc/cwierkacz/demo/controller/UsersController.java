package lghsbc.cwierkacz.demo.controller;

import lghsbc.cwierkacz.demo.controller.dto.FollowerDto;
import lghsbc.cwierkacz.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static lghsbc.cwierkacz.demo.controller.UsersController.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class UsersController {
    static final String API_ENDPOINT = "/users";

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "{userId}/followed")
    public void follow(@PathVariable String userId, @RequestBody @Valid FollowerDto follower) {
        userService.addFollowers(userId, follower.getFollowerId());
    }

}
