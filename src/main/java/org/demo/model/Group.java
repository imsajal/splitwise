package org.demo.model;

import java.util.*;

public class Group {

    private String groupId;
    Map<String, Map<String, Double>> balanceSheet;
    List<User> members;

    public Group(List<User> members, String groupId) {
        this.groupId = groupId;
        this.balanceSheet = new HashMap<>();
        this.members = members;
    }

    public Map<String, Map<String, Double>> getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(Map<String, Map<String, Double>> balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
