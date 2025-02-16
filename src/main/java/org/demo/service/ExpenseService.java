package org.demo.service;

import org.demo.model.Expense;
import org.demo.model.GroupExpense;
import org.demo.model.UserExpense;
import org.demo.service.strategy.ExpenseSplitStrategy;
import org.demo.service.strategy.ExpenseSplitStrategyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpenseService {

    List<Expense> expenses;
    UserBalanceSheetService userBalanceSheetService;
    GroupService groupService;
    ExpenseSplitStrategyFactory expenseSplitStrategyFactory;

    public ExpenseService(UserBalanceSheetService userBalanceSheetService, GroupService groupService,
                          ExpenseSplitStrategyFactory expenseSplitStrategyFactory) {
        this.userBalanceSheetService = userBalanceSheetService;
        expenses = new ArrayList<>();
        this.groupService = groupService;
        this.expenseSplitStrategyFactory = expenseSplitStrategyFactory;
    }

    public void processExpense(Expense expense){
        ExpenseSplitStrategy expenseSplitStrategy = ExpenseSplitStrategyFactory.getSplitStrategy(expense.getExpenseType());
        expenseSplitStrategy.validateExpense(expense);
        Map<String, Double> dueBalances = expenseSplitStrategy.splitExpense(expense);
        userBalanceSheetService.updateUserBalances(dueBalances);
        expenses.add(expense);
    }

    public void addExpense(UserExpense userExpense){
        processExpense(userExpense);
    }

    public void addExpense(GroupExpense groupExpense){
       processExpense(groupExpense);
       groupService.addExpenseToGroup(groupExpense);
    }

}
