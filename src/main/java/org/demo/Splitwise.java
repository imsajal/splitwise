package org.demo;

import org.demo.enums.ExpenseType;
import org.demo.model.GroupExpense;
import org.demo.model.User;
import org.demo.model.UserExpense;
import org.demo.service.UserBalanceSheetService;
import org.demo.service.ExpenseService;
import org.demo.service.GroupService;
import org.demo.service.UserService;
import org.demo.service.strategy.ExpenseSplitStrategyFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Splitwise {

    private ExpenseService expenseService;
    private GroupService groupService;
    private UserService userService;
    private UserBalanceSheetService userBalanceSheetService;


    public Splitwise() {
        this.userService = new UserService();
        this.groupService = new GroupService(userService);
        this.expenseService = new ExpenseService(new UserBalanceSheetService(), groupService, new ExpenseSplitStrategyFactory());
        this.userBalanceSheetService = new UserBalanceSheetService();
    }

    public void demo() {

        // creating users and adding in app
        User sajal = new User("u1", "sajal", "", "" );
        User saloni = new User("u2", "saloni", "", "" );
        User vinay = new User("u3", "vinay", "", "" );
        User sanju = new User("u4", "sanju", "", "" );
        User manoj = new User("u5", "manoj", "", "" );

        userService.addUsers(Arrays.asList(sajal, saloni, vinay));

        List<User> groupMembers = new ArrayList<>();
        groupMembers.add(sanju);
        groupMembers.add(manoj);
        groupService.createGroup(groupMembers, "g1");


        // start adding expense

        List<String> inputs = Arrays.asList(
                "EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL",
                "EXPENSE u1 1250 2 u2 u3 EXACT 370 880",
                "EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20",
                "SHOWALL",
                "SHOW u1",
                "GROUPEXPENSE G1 u4 1000 2 u3 u4 PERCENT 50 50",
                "SHOWGROUPEXPENSE G1"
        );

        for(String input : inputs){

            String[] commands = input.split(" ");
            switch(commands[0]){
                case "EXPENSE" :
                    createExpense(commands, "");
                    break;
                case "GROUPEXPENSE" :
                    String groupId = commands[1];
                    createExpense(commands, groupId);
                    break;
                case "SHOW" :
                    userBalanceSheetService.show(commands[1]);
                    break;
                case "SHOWALL" :
                    userBalanceSheetService.showAll();
                    break;
                case "SHOWGROUPEXPENSE" :
                    groupService.showGroupExpense(commands[1]);
                    break;

            }
        }

    }

    private void createExpense(String[] commands, String groupId) {

        int p = groupId != "" ? 2:1;
        String paidBY = commands[++p];
        Double amount = Double.valueOf(commands[++p]);
        int noOfUsers = Integer.valueOf(commands[++p]);
        List<String> paidTo = new ArrayList<>();
        List<Double> splitValues = new ArrayList<>();
        for(int i = 0; i< noOfUsers; i++){
            paidTo.add(commands[++p]);
        }
        ExpenseType expenseType = ExpenseType.valueOf(commands[++p]);
        switch(expenseType){
            case EQUAL:
                break;
            case EXACT:
            case PERCENTAGE:
                for(int i = 0; i< noOfUsers; i++){
                    splitValues.add(Double.valueOf(commands[++p]));
                }
                break;
        }

        if(p+1 < commands.length) {groupId = commands[++p];
            expenseService.addExpense(new GroupExpense(paidBY, paidTo, expenseType, amount, splitValues, groupId));
        }
        else
        expenseService.addExpense(new UserExpense(paidBY, paidTo, expenseType, amount, splitValues));
    }


}
