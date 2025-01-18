package edu.fra.uas.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
