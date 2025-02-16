package org.demo.service.strategy;

import org.demo.model.Expense;

import java.util.Collections;
import java.util.Map;

public class ExactSplitStrategy implements ExpenseSplitStrategy{

    @Override
    public Map<String, Double> splitExpense(Expense expense) {
        return Collections.emptyMap();
    }

    @Override
    public boolean validateExpense(Expense expense) {
        return false;
    }
}
