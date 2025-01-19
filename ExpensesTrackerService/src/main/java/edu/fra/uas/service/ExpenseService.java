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
 * This class represents the service for managing expenses.
 */
@Service
public class ExpenseService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    private long nextExpenseId = 1;

    public Expense createExpense(Expense expense) {
        expense.setId(nextExpenseId++);
        log.debug("createExpense: " + expense);
        expenseRepository.put(expense.getId(), expense);
        return expenseRepository.get(expense.getId());
    }

    public Iterable<Expense> getAllExpenses() {
        log.debug("getAllExpenses");
        return expenseRepository.values();
    }

    public Expense getExpenseById(long id) {
        log.debug("getExpense: " + id);
        return expenseRepository.get(id);
    }

    public Expense updateExpense(Expense expense) {
        log.debug("updateExpense: " + expense);
        expenseRepository.put(expense.getId(), expense);
        return expenseRepository.get(expense.getId());
    }

    public Expense deleteExpense(long id) {
        log.debug("deleteExpense: " + id);
        return expenseRepository.remove(id);
    }
    public List<Expense> getExpensesFromCategories(List<String> categories) {
        log.debug("getExpensesFromCategories: " + categories);
        List<Expense> filteredExpenses = new ArrayList<>();
        for (Expense expense : expenseRepository.values()) {
            if (categories.contains(expense.getCategory())) {
                filteredExpenses.add(expense);
            }
        }
        return filteredExpenses;
    }

    public List<CategoryTotal> getTotalExpensesByCategory() {
        log.debug("getTotalExpensesByCategory");
    
        // Map to hold category-currency pairs and their totals
        Map<String, Long> categoryCurrencyTotals = new HashMap<>();
    
        for (Expense expense : expenseRepository.values()) {
            // Create a unique key combining category and currency
            String key = expense.getCategory() + "|" + expense.getCurrency();
            categoryCurrencyTotals.put(
                key,
                categoryCurrencyTotals.getOrDefault(key, 0L) + expense.getValue()
            );
        }
    
        // Convert the map entries into CategoryTotal objects
        List<CategoryTotal> totals = new ArrayList<>();
        for (Map.Entry<String, Long> entry : categoryCurrencyTotals.entrySet()) {
            String[] keyParts = entry.getKey().split("\\|");
            String category = keyParts[0];
            String currency = keyParts[1];
            totals.add(new CategoryTotal(category, currency, entry.getValue()));
        }
    
        return totals;
    }
    
    
}
