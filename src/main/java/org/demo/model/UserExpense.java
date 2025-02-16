package org.demo.model;

import org.demo.enums.ExpenseType;

import java.util.List;

public class UserExpense extends Expense{

    public UserExpense(String paidBy, List<String> paidTo, ExpenseType expenseType, Double amount, List<Double> splitValues) {
        super(paidBy, paidTo, expenseType, amount, splitValues);
    }
}
