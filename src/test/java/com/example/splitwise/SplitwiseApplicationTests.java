package com.example.splitwise;

import com.example.splitwise.controller.ExpenseController;
import com.example.splitwise.dto.*;
import com.example.splitwise.enums.Command;
import com.example.splitwise.enums.ExpenseType;
import com.example.splitwise.model.User;
import com.example.splitwise.service.ExpenseService;
import com.example.splitwise.service.ValidationService;
import com.example.splitwise.service.impl.ExpenseServiceImpl;
import com.example.splitwise.service.impl.ValidationServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.example.splitwise.constants.SplitwiseConstants.NO_BALANCES;


class SplitwiseApplicationTests {


    // check for rounding off point
    public static void main(String args[]) {
        ValidationService validationService = new ValidationServiceImpl();
        ExpenseService expenseService = new ExpenseServiceImpl(validationService);
        ExpenseController expenseController = new ExpenseController(expenseService);

        String u1_id = UUID.randomUUID().toString();
        String u2_id = UUID.randomUUID().toString();
        String u3_id = UUID.randomUUID().toString();
        String u4_id = UUID.randomUUID().toString();

        User u1 = new User(u1_id, "sajal", "sajal@gmail.com", "12345678");
        User u2 = new User(u2_id, "sidhi", "sidhi@gmail.com", "12345678");
        User u3 = new User(u3_id, "sourabh", "sourabh@gmail.com", "12345678");
        User u4 = new User(u4_id, "ajay", "ajay@gmail.com", "12345678");

        Scanner scanner = new Scanner(System.in);


        while (true) {
            String input[] = scanner.nextLine().split(" ");

            Command command = Command.valueOf(input[0].toUpperCase());

            switch (command) {

                case EXPENSE:
                    expenseController.addExpense(getSplitExpense(input));
                    break;

                case SHOW:

                    if (input.length > 1) {
                        printBalances(expenseController.getUserBalance(input[1]));
                    } else {
                        printBalances(expenseController.getAllBalances());
                    }

                    break;

                case QUIT:
                    scanner.close();
                    return;
            }
        }
    }

    private static SplitExpense getSplitExpense(String[] input) {

        //  EXPENSE <user-id-of-person-who-paid> <totalAmount> <no-of-users> <space-separated-list-of-users>
        //  <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>

        String paidBy = input[1];
        Double totalAmount = Double.valueOf(input[2]);
        Integer totalNumberOfUsers = Integer.valueOf(input[3]);

        List<String> involvedUsersIds = new ArrayList<>();

        int index = 4;
        for (; index <= 3 + totalNumberOfUsers; index++) {
            involvedUsersIds.add(input[index]);
        }

        ExpenseType expenseType = ExpenseType.valueOf(input[index++].toUpperCase());

        List<Expense> expenses = new ArrayList<>();

        switch (expenseType) {

            case EQUAL:
                for (; index <= index + totalNumberOfUsers; index++) {
                    expenses.add(new EqualExpense(totalAmount));
                }
                break;

            case EXACT:

                for (; index <= index + totalNumberOfUsers; index++) {
                    expenses.add(new ExactExpense(Double.valueOf(input[index])));
                }

                break;

            case PERCENTAGE:

                for (; index <= index + totalNumberOfUsers; index++) {
                    expenses.add(new PercentageExpense(Double.valueOf(input[index])));
                }
                break;

        }

        return new SplitExpense(totalAmount, paidBy, totalNumberOfUsers,
                involvedUsersIds, expenseType, expenses);

    }

    private static void printBalances(List<String> balances) {
        if (balances.size() == 0) System.out.println(NO_BALANCES);
        else {
            for (String balance : balances) {
                System.out.println(balance);
            }
        }
    }

}
