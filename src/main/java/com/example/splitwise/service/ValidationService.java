package com.example.splitwise.service;

import com.example.splitwise.dto.Expense;
import com.example.splitwise.dto.SplitExpense;

public interface ValidationService
{
    boolean validateExpense(SplitExpense splitExpense);
}
