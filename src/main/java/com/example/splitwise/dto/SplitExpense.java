package com.example.splitwise.dto;

import com.example.splitwise.enums.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SplitExpense
{
    private Double totalAmount;
    private String paidBy;
    private Integer totalNumberOfUsersInvolved;
    private List<String> involvedUsersIds;
    private ExpenseType ExpenseType;
    private List<Expense> expenses;
}
