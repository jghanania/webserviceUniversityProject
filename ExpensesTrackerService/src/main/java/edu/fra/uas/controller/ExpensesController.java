package edu.fra.uas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import edu.fra.uas.model.CategoryTotal;
import edu.fra.uas.model.Expense;
import edu.fra.uas.service.ExpenseService;

/**
 * This controller handles GraphQL queries and mutations for managing expenses.
 * It acts as a bridge between the GraphQL schema and the ExpenseService.
 */
@Controller
@SchemaMapping(typeName = "Expenses") // Maps this controller to the GraphQL type "Expenses"
public class ExpensesController {

    private static final Logger log = LoggerFactory.getLogger(ExpensesController.class);

    @Autowired
    private ExpenseService expenseService;

    /**
     * Handles the "ExpenseById" GraphQL query.
     * Retrieves a specific expense by its ID.
     *
     * @param id The ID of the expense to retrieve.
     * @return The expense with the specified ID.
     */
    @QueryMapping(name = "ExpenseById")
    public Expense getExpenseById(@Argument Long id) {
        log.debug("getExpenseById() is called");
        
        return expenseService.getExpenseById(id); 
    }

    /**
     * Handles the "expensesFromCategories" GraphQL query.
     * Retrieves a list of expenses belonging to the specified categories.
     *
     * @param categories The list of categories to filter expenses by.
     * @return A list of expenses in the specified categories.
     */
    @QueryMapping(name = "expensesFromCategories")
    public List<Expense> getExpensesFromCategories(@Argument List<String> categories, @Argument int user) {
        log.debug("getExpensesFromCategories() is called with categories: {}, user: {}", categories, user);
        return expenseService.getExpensesFromCategories(categories, user);
    }

    /**
     * Handles the "totalExpensesByCategory" GraphQL query.
     * Calculates the total value of expenses for each category and currency combination.
     *
     * @return A list of category totals, each including the category, currency, and total value.
     */
    @QueryMapping(name = "totalExpensesByCategory")
    public List<CategoryTotal> getTotalExpensesByCategory(@Argument int user) {
        log.debug("getTotalExpensesByCategory() is called with user: {}", user);
        return expenseService.getTotalExpensesByCategory(user);
    }

    /**
     * Handles the "addExpense" GraphQL mutation.
     * Creates a new expense with the provided details.
     *
     * @param user The user ID associated with the expense.
     * @param category The category of the expense.
     * @param value The monetary value of the expense.
     * @param currency The currency of the expense.
     * @return The newly created expense.
     */
    @MutationMapping
    public Expense addExpense(@Argument int user, @Argument String category, @Argument int value, @Argument String currency) {
        log.debug("addExpense() is called with user: {}, category: {}, value: {}, currency: {}", user, category, value, currency);

        // Create a new Expense object and populate its fields
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setCategory(category);
        expense.setValue(value);
        expense.setCurrency(currency);

        // Save the expense using the service and return the result
        return expenseService.createExpense(expense);
    }
}
