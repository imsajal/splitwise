package org.demo.service;

import java.util.HashMap;
import java.util.Map;

public class UserBalanceSheetService {

    Map<String, Map<String, Double>> usersBalanceSheet;

    public UserBalanceSheetService() {
        this.usersBalanceSheet = new HashMap<>();
    }

    public void show(String userId) {
    }

    public void showAll() {
    }

    public void updateUserBalances(Map<String, Double> dueBalances) {
    }

}
