package com.example.splitwise.service.impl;

import com.example.splitwise.dto.*;
import com.example.splitwise.service.ValidationService;

public class ValidationServiceImpl implements ValidationService
{

    @Override
    public boolean validateExpense(SplitExpense splitExpense)
    {
        boolean isSplitExpenseValid = false;

        switch(splitExpense.getExpenseType()){
            case EQUAL:

                isSplitExpenseValid = true;

                break;

            case EXACT:

                Double totalAmount = 0.0;
                for(Expense expense :splitExpense.getExpenses()){
                    totalAmount += expense.getValue();

                }

                isSplitExpenseValid = Double.compare(totalAmount, splitExpense.getTotalAmount()) == 0;

                break;

            case PERCENTAGE:

                Double totalPercentage = 0.0;
                for(Expense expense :splitExpense.getExpenses()){
                    totalPercentage += expense.getValue();

                }

                isSplitExpenseValid = Double.compare(totalPercentage, 100.00) == 0;
                break;
        }

       return isSplitExpenseValid;
    }
}
