package org.demo.service.strategy;

import org.demo.model.Expense;

import java.util.Map;

public interface ExpenseSplitStrategy {

    Map<String, Double> splitExpense(Expense expense);

    boolean validateExpense(Expense expense);
}
