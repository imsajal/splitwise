package com.example.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class ExactExpense extends Expense
{
    private Double amount;

    public ExactExpense(Double amount)
    {
        super(amount);
        this.amount = amount;
    }
}
