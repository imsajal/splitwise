package org.demo.model;

import org.demo.enums.ExpenseType;

import java.util.List;

public class GroupExpense extends Expense{

    String groupId;
    public GroupExpense(String paidBy, List<String> paidTo, ExpenseType expenseType, Double amount, List<Double> splitValues, String groupId) {
        super(paidBy, paidTo, expenseType, amount, splitValues);
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
