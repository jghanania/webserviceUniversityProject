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
import java.util.Optional;

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
     * Handles the "expenseById" GraphQL query.
     * Retrieves a specific expense by its ID.
     *
     * @param id The ID of the expense to retrieve.
     * @return The expense with the specified ID.
     */
    @QueryMapping(name = "expenseById")
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
     * Calculates the total value of expenses for each category and currency
     * combination.
     *
     * @return A list of category totals, each including the category, currency, and
     *         total value.
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
     * @param user     The user ID associated with the expense.
     * @param category The category of the expense.
     * @param value    The monetary value of the expense.
     * @param currency The currency of the expense.
     * @return The newly created expense.
     */
    @MutationMapping
    public Expense addExpense(
            @Argument int user,
            @Argument String category,
            @Argument double value,
            @Argument String currency) {

        log.debug("addExpense() is called with user: {}, category: {}, value: {}, currency: {}", user, category, value,
                currency);

        // Create a new Expense object and populate its fields
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setCategory(category);
        expense.setValue(value);
        expense.setCurrency(currency);

        // Save the expense using the service and return the result
        return expenseService.createExpense(expense);
    }

    /**
     * Handles the "allExpenses" GraphQL query.
     * Retrieves all expenses stored in the repository.
     *
     * @return An iterable collection of all expenses.
     */
    @QueryMapping(name = "allExpenses")
    public Iterable<Expense> getAllExpenses(@Argument Integer user) {
        log.debug("getAllExpenses() is called with user: {}", user);

        if (user != null) {
            return expenseService.getAllExpensesFromUser(user);
        }
        return expenseService.getAllExpenses();
    }

    /**
     * Updates an existing expense with the provided details.
     *
     * @param user     The ID of the user attempting to update the expense.
     * @param id       The unique identifier of the expense to be updated.
     * @param category (Optional) The new category of the expense.
     * @param value    (Optional) The new value of the expense.
     * @param currency (Optional) The new currency of the expense.
     * @return The updated {@link Expense} object if the update is successful,
     *         otherwise {@code null}.
     */
    @MutationMapping
    public Expense updateExpense(
            @Argument int user,
            @Argument Long id,
            @Argument Optional<String> category,
            @Argument Optional<Double> value,
            @Argument Optional<String> currency) {

        log.debug("updateExpense() is called with user: {}, id: {}, category: {}, value: {}, currency: {}",
                user, id, category.orElse(null), value.orElse(null), currency.orElse(null));

        // Fetch the existing expense from the database
        Expense existingExpense = expenseService.getExpenseById(id);

        // if Expense doesn't exist return null
        if (existingExpense == null) {
            return null;
        }

        // Ensure that the expense belongs to the correct user
        if (existingExpense.getUser() != user) {
            return null;
        }

        // Update only provided fields
        category.ifPresent(existingExpense::setCategory);
        value.ifPresent(existingExpense::setValue);
        currency.ifPresent(existingExpense::setCurrency);

        return expenseService.updateExpense(existingExpense);
    }

    /**
     * Handles the "deleteExpense" GraphQL mutation.
     * Deletes an expense by its ID.
     *
     * @param id The ID of the expense to delete.
     * @return The deleted expense, or null if not found.
     */
    @MutationMapping
    public Expense deleteExpense(@Argument Long id) {
        log.debug("deleteExpense() is called with id: {}", id);

        return expenseService.deleteExpense(id);
    }
}
