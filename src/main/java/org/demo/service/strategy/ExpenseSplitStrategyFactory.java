package org.demo.service.strategy;

import org.demo.enums.ExpenseType;

public class ExpenseSplitStrategyFactory {

    public static ExpenseSplitStrategy getSplitStrategy(ExpenseType expenseType) {
        ExpenseSplitStrategy expenseSplitStrategy = null;
        switch(expenseType){
            case EQUAL:
                expenseSplitStrategy = new EqualSplitStrategy();
                break;
            case PERCENTAGE:
                expenseSplitStrategy =  new PercentageSplitStrategy();
                break;
            case EXACT:
                expenseSplitStrategy =  new ExactSplitStrategy();
                break;

        }
        return expenseSplitStrategy;
    }

    // method to return expense service by expense type getExpenseSplitStrategy
}
