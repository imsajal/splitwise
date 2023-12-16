package com.example.splitwise.service.impl;

import com.example.splitwise.dto.SplitExpense;
import com.example.splitwise.service.ExpenseService;
import com.example.splitwise.service.ValidationService;
import com.example.splitwise.service.exceptions.ExpenseInvalidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExpenseServiceImpl implements ExpenseService {
    private ValidationService validationService;

    public ExpenseServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    Map<String, Map<String, Double>> balances = new HashMap<>();

    @Override
    public void addExpense(SplitExpense split) {
        if (!validationService.validateExpense(split)) {
            throw new ExpenseInvalidException();
        }

        if (!balances.containsKey(split.getPaidBy()))
            balances.put(split.getPaidBy(), new HashMap<String, Double>());


        switch (split.getExpenseType()) {
            case EQUAL:
                Double splitAmount = split.getTotalAmount() / split.getTotalNumberOfUsersInvolved();
                for (int i = 0; i < split.getTotalNumberOfUsersInvolved(); i++) {
                    addSplitBalanceToUsers(split, splitAmount, split.getInvolvedUsersIds().get(i));
                }
                break;

            case EXACT:

                for (int i = 0; i < split.getTotalNumberOfUsersInvolved(); i++) {
                    addSplitBalanceToUsers(split, split.getExpenses().get(i).getValue(), split.getInvolvedUsersIds().get(i));
                }

                break;

            case PERCENTAGE:

                for (int i = 0; i < split.getTotalNumberOfUsersInvolved(); i++) {
                    addSplitBalanceToUsers(split, (split.getExpenses().get(i).getValue() * split.getTotalAmount()) / 100.00, split.getInvolvedUsersIds().get(i));
                }
                break;
        }


    }

    private void addSplitBalanceToUsers(SplitExpense split, Double splitAmount, String paidTo) {

        if (!balances.containsKey(paidTo)) {
            balances.put(paidTo, new HashMap<>());
        }

        Map<String, Double> paidByBalances = balances.get(split.getPaidBy());
        Map<String, Double> paidToBalances = balances.get(paidTo);
        if (!split.getPaidBy().equalsIgnoreCase(paidTo)) {
            if (!paidByBalances.containsKey(paidTo)) {
                paidByBalances.put(paidTo, 0.00);
            }
            paidByBalances.put(paidTo, paidByBalances.get(paidTo) + splitAmount);

            if (!paidToBalances.containsKey(split.getPaidBy())) {
                paidToBalances.put(split.getPaidBy(), 0.00);
            }
            paidToBalances.put(split.getPaidBy(), paidToBalances.get(split.getPaidBy()) - splitAmount);
        }
    }

    @Override
    public List<String> getUserBalance(String userId) {

        List<String> userBalances = new ArrayList<>();

        if (balances.containsKey(userId)) {

            for (Map.Entry<String, Double> entry : balances.get(userId).entrySet()) {

                if (entry.getValue() > 0.00) {
                    userBalances.add(entry.getKey() + " owes " + userId + " " + entry.getValue());
                } else {
                    userBalances.add(userId + " owes " + entry.getKey() + " " + entry.getValue());
                }
            }
        }

        return userBalances;
    }

    @Override
    public List<String> getAllBalances() {

        List<String> allBalances = new ArrayList<>();


        balances.keySet().stream().forEach(
                user ->
                {
                    Map<String, Double> userBalances = balances.get(user);
                    for(Map.Entry<String, Double> entry: userBalances.entrySet()){
                        if(entry.getValue() > 0.00){
                            allBalances.add(entry.getKey() + " owes " + user + " " + entry.getValue());
                        }
                    }
                }
        );

        return allBalances;
    }
}
