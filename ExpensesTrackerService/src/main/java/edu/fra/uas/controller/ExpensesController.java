package edu.fra.uas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.fra.uas.model.Expense;
import edu.fra.uas.service.ExpenseService;;


@Controller
@SchemaMapping(typeName = "Expenses")
public class ExpensesController {

    private static final Logger log = LoggerFactory.getLogger(ExpensesController.class);

    @Autowired
    private ExpenseService expenseService;



    @QueryMapping(name="userById")
    public Expense getExpenseById(@Argument Long id) {
        log.debug("getExpenseById() is called");
        return expenseService.getExpenseById(id);
    }

    @MutationMapping
    public Expense addExpense(@Argument int user, @Argument String category, @Argument int value, @Argument String currency) {
        log.debug("addExpense() is called with user: {}, category: {}, value: {}, currency: {}", user, category, value, currency);
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setCategory(category);
        expense.setValue(value);
        expense.setCurrency(currency);
        return expenseService.createExpense(expense);
    }
}
