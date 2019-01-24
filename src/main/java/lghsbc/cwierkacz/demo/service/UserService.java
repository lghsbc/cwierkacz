package lghsbc.cwierkacz.demo.service;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Map<String, Set<String>> followers = new ConcurrentHashMap<>();

    public void addFollowers(String userId, String followerId) {
        followers.putIfAbsent(userId, ConcurrentHashMap.newKeySet());

        Set<String> followers = this.followers.get(userId);
        followers.add(followerId);
    }

    public Set<String> getFollowers(String userId){
        return followers.getOrDefault(userId, Collections.emptySet());
    }
}
