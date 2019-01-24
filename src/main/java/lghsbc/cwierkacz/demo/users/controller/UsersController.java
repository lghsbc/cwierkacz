package lghsbc.cwierkacz.demo.users.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lghsbc.cwierkacz.demo.users.controller.dto.FollowerDto;
import lghsbc.cwierkacz.demo.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static lghsbc.cwierkacz.demo.users.controller.UsersController.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
@Api(description = "Set of endpoints for user management")
public class UsersController {
    static final String API_ENDPOINT = "/users";

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "{userId}/followed")
    @ApiOperation("Subscribe user for following some other user")
    public void follow(@PathVariable String userId, @RequestBody @Valid FollowerDto follower) {
        userService.addFollowers(userId, follower.getFollowerId());
    }

}
