package org.demo.model;

import org.demo.enums.ExpenseType;

import java.util.List;

public class Expense {

    private String paidBy;
    private List<String> paidTo;
    private ExpenseType expenseType;
    private Double amount;
    private List<Double> splitValues;

    public Expense(String paidBy, List<String> paidTo, ExpenseType expenseType, Double amount, List<Double> splitValues) {
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.expenseType = expenseType;
        this.amount = amount;
        this.splitValues = splitValues;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public List<String> getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(List<String> paidTo) {
        this.paidTo = paidTo;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Double> getSplitValues() {
        return splitValues;
    }

    public void setSplitValues(List<Double> splitValues) {
        this.splitValues = splitValues;
    }
}
