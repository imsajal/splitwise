package com.example.splitwise.controller;

import com.example.splitwise.dto.Expense;
import com.example.splitwise.dto.SplitExpense;
import com.example.splitwise.service.ExpenseService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ExpenseController
{
    private ExpenseService expenseService;

    public void addExpense(SplitExpense splitExpense){
        expenseService.addExpense(splitExpense);
    }

    public List<String> getUserBalance(String id){
       return expenseService.getUserBalance(id);
    }

    public  List<String>  getAllBalances(){
        return expenseService.getAllBalances();
    }
}
