package com.example.splitwise.service;

import com.example.splitwise.dto.Expense;
import com.example.splitwise.dto.SplitExpense;

import java.util.List;

public interface ExpenseService
{
    void addExpense(SplitExpense splitExpense);

    List<String> getUserBalance(String id);

    List<String> getAllBalances();
}
