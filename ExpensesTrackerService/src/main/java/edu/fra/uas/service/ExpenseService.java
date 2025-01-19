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
        Map<String, Long> categoryTotals = new HashMap<>();
        for (Expense expense : expenseRepository.values()) {
            categoryTotals.put(
                expense.getCategory(),
                categoryTotals.getOrDefault(expense.getCategory(), 0L) + expense.getValue()
            );
        }
        List<CategoryTotal> totals = new ArrayList<>();
        for (Map.Entry<String, Long> entry : categoryTotals.entrySet()) {
            totals.add(new CategoryTotal(entry.getKey(), entry.getValue()));
        }
        return totals;
    }
    
}
