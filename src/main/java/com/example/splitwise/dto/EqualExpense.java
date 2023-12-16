package com.example.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class EqualExpense extends Expense
{
    public EqualExpense(Double amount)
    {
        super(amount);
    }
}
