package com.example.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class PercentageExpense extends Expense
{
    private Double percentage;

    public PercentageExpense(Double percentage)
    {
        super(percentage);
        this.percentage = percentage;

    }
}
