package lghsbc.cwierkacz.demo.co.service;

import lghsbc.cwierkacz.demo.users.service.UserService;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    private UserService underTest = new UserService();

    @Test
    public void shouldReturnEmptySetIfUserDoesNotHaveFollowers() {
        String userId = "inExistentUserId";

        Set<String> result = underTest.getFollowers(userId);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldNotAddFollowingRelationInBothDirections() {
        String userIdA = "userA";
        String userIdB = "userB";
        underTest.addFollowers(userIdA, userIdB);

        Set<String> followersOfUserA = underTest.getFollowers(userIdA);
        Set<String> followersOfUserB = underTest.getFollowers(userIdB);

        assertThat(followersOfUserA).containsExactlyInAnyOrder(userIdB);
        assertThat(followersOfUserB).isEmpty();
    }

    @Test
    public void shouldReturnFollowersForGivenUser() {
        String userIdA = "userA";
        String userIdB = "userB";
        underTest.addFollowers(userIdA, userIdB);
        underTest.addFollowers(userIdB, userIdA);

        Set<String> followersOfUserA = underTest.getFollowers(userIdA);
        Set<String> followersOfUserB = underTest.getFollowers(userIdB);

        assertThat(followersOfUserA).containsExactlyInAnyOrder(userIdB);
        assertThat(followersOfUserB).containsExactlyInAnyOrder(userIdA);
    }

    @Test
    public void shouldAddNewFollowerToExistentOne() {
        String userId = "user";
        String follower1 = "follower1";
        String follower2 = "follower2";
        underTest.addFollowers(userId, follower1);

        underTest.addFollowers(userId, follower2);
        Set<String> followers = underTest.getFollowers(userId);

        assertThat(followers).containsExactlyInAnyOrder(follower1, follower2);
    }
}