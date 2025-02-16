package org.demo.service;

import org.demo.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    Map<String, User>  userById = new HashMap<>();

    public void addUsers(List<User> users) {

        users.forEach(user ->
        {
            if(!userById.containsKey(user.getId()))
                userById.put(user.getId(), user);
        });
    }

    public User getUserById(String id) {
        User user = null;
        if(userById.containsKey(id)) user = userById.get(id);
        return user;
    }


}
