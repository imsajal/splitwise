package org.demo.service;

import org.demo.model.Group;
import org.demo.model.GroupExpense;
import org.demo.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {

    Map<String, Group> groupById;
    UserService userService;

    public GroupService(UserService userService) {
        this.groupById = new HashMap<>();
        this.userService = userService;
    }

    public void addUserToGroup(String groupId, User user){

    }

    public void addExpenseToGroup(GroupExpense expense){

    }

    public void createGroup(List<User> members, String groupId){
        userService.addUsers(members);
        Group group = new Group(members, groupId);
        groupById.put(group.getGroupId(), group);
    }

    public void showGroupExpense(String groupId) {
    }




}
