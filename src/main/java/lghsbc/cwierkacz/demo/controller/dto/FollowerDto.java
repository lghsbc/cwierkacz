package lghsbc.cwierkacz.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class FollowerDto {
    @NotEmpty
    private final String followerId;

    @JsonCreator
    FollowerDto(@JsonProperty("followerId") String followerId) {
        this.followerId = followerId;
    }

    public String getFollowerId() {
        return followerId;
    }
}
