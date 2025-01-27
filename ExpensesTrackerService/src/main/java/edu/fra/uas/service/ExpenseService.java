package edu.fra.uas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.model.CategoryTotal;
import edu.fra.uas.model.Expense;
import edu.fra.uas.repository.ExpenseRepository;

/**
 * ExpenseService class provides business logic related to managing expenses.
 * It interacts with the ExpenseRepository to create, read, update, and delete expense data.
 * Additionally, it performs aggregate operations, such as calculating the total expenses by category and currency.
 */
@Service
public class ExpenseService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    // Counter to generate unique expense IDs
    private long nextExpenseId = 1;

    /**
     * Creates a new Expense and stores it in the repository.
     * The expense ID is automatically assigned.
     * 
     * @param expense The expense object to be created.
     * @return The created expense object.
     */
    public Expense createExpense(Expense expense) {
        expense.setId(nextExpenseId++); // Assign a unique ID to the new expense
        log.debug("createExpense: " + expense);

        expenseRepository.put(expense.getId(), expense); // Save the expense in the repository
        return expenseRepository.get(expense.getId()); // Return the saved expense
    }

    /**
     * Retrieves all expenses stored in the repository.
     * 
     * @return An iterable collection of all expenses.
     */
    public Iterable<Expense> getAllExpenses() {
        log.debug("getAllExpenses");

        return expenseRepository.values();
    }

    /**
     * Retrieves all expenses of a user stored in the repository.
     * 
     * @param user the id of the user
     * @return An iterable collection of all expenses.
     */
    public Iterable<Expense> getAllExpensesFromUser(int user) {
        log.debug("getExpensesFromUser: user: {}", user);

        List<Expense> filteredExpenses = new ArrayList<>();
        for (Expense expense : expenseRepository.values()) {
            if (expense.getUser() == user) {
                filteredExpenses.add(expense);
            }
        }
        return filteredExpenses;
    }

    /**
     * Retrieves an expense by its ID.
     * 
     * @param id The ID of the expense to retrieve.
     * @return The expense with the given ID, or null if not found.
     */
    public Expense getExpenseById(long id) {
        log.debug("getExpense: " + id);

        return expenseRepository.get(id);
    }

    /**
     * Updates an existing expense in the repository.
     * 
     * @param expense The expense object with updated information.
     * @return The updated expense object.
     */
    public Expense updateExpense(Expense expense) {
        log.debug("updateExpense: " + expense);

        expenseRepository.put(expense.getId(), expense); // Replace the old expense with the updated one
        return expenseRepository.get(expense.getId());
    }

    /**
     * Deletes an expense by its ID.
     * 
     * @param id The ID of the expense to delete.
     * @return The deleted expense, or null if not found.
     */
    public Expense deleteExpense(long id) {
        log.debug("deleteExpense: " + id);

        return expenseRepository.remove(id); // Remove the expense from the repository and return it
    }

    /**
     * Retrieves all expenses that belong to one or more specified categories.
     * 
     * @param categories The list of categories to filter expenses by.
     * @return A list of expenses belonging to the specified categories.
     */
    public List<Expense> getExpensesFromCategories(List<String> categories, int user) {
        log.debug("getExpensesFromCategoriesForUser: {}, user: {}", categories, user);

        List<Expense> filteredExpenses = new ArrayList<>(); // List to store filtered expenses
        for (Expense expense : expenseRepository.values()) {
            // If the expense category is in the list of specified categories, add it to the list
            if (categories.contains(expense.getCategory()) && expense.getUser() == user) {
                filteredExpenses.add(expense);
            }
        }
        return filteredExpenses;
    }

    /**
     * Calculates the total value of expenses grouped by category and currency.
     * The result is returned as a list of CategoryTotal objects, each containing:
     * - Category
     * - Currency
     * - Total value of expenses for that category and currency.
     * 
     * @return A list of CategoryTotal objects, each representing the total value of expenses by category and currency.
     */
    public List<CategoryTotal> getTotalExpensesByCategory(int user) {
        log.debug("getTotalExpensesByCategoryForUser: user: {}", user);
        
        // Map to hold the total expenses grouped by category and currency
        Map<String, Double> categoryCurrencyTotals = new HashMap<>();
    
        // Iterate through all expenses and aggregate their values by category and currency
        for (Expense expense : expenseRepository.values()) {
            if (expense.getUser() == user) {
                // Create a unique key combining the category and currency to group the totals
                String key = expense.getCategory() + "|" + expense.getCurrency();
                // Update the total for the category-currency pair
                categoryCurrencyTotals.put(
                    key,
                    categoryCurrencyTotals.getOrDefault(key, 0D) + expense.getValue()
                );
            }

        }
    
        // Convert the map entries into CategoryTotal objects
        List<CategoryTotal> totals = new ArrayList<>();
        for (Map.Entry<String, Double> entry : categoryCurrencyTotals.entrySet()) {
            // Split the key into category and currency
            String[] keyParts = entry.getKey().split("\\|");
            String category = keyParts[0];
            String currency = keyParts[1];
            // Add a new CategoryTotal object with the computed total value
            totals.add(new CategoryTotal(category, currency, entry.getValue()));
        }
    
        return totals;
    }
}
