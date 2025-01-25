package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.fra.uas.service.*;
import edu.fra.uas.model.CategoryTotal;
import edu.fra.uas.model.Expense;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {
    
    private static final Logger log = LoggerFactory.getLogger(ApiGatewayController.class);

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ConverterService converterService;
    
     // Get a specific expense using GraphQL from ExpenseService
    @GetMapping(value = "/user/{userId}/expenses/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExpense(@PathVariable("userId") Long userId, @PathVariable("expenseId") Long expenseId) throws Exception {
        log.debug("Fetching expense with ID {} for user {}", expenseId, userId);
        // Call the ExpenseService's GraphQL endpoint to fetch the expense
        Expense expense = expenseService.getExpenseById(userId, expenseId);
        return ResponseEntity.ok(expense);
    }


    // Get total expenses by category from ExpenseService
    @GetMapping(value = "/user/{userId}/categories/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTotalExpensesByCategory(@PathVariable("userId") Long userId) throws Exception {
        log.debug("Fetching total expenses by category for user {}", userId);
        // Call the ExpenseService to get total expenses by category
        List<CategoryTotal> response =  expenseService.getTotalExpensesByCategory(userId);
        return ResponseEntity.ok(response);
    }

    // Get expenses for a specific category from ExpenseService
    @GetMapping(value = "/user/{userId}/categories/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExpensesByCategory(@PathVariable("userId") Long userId, @PathVariable("category") String category) throws Exception {
        log.debug("Fetching expenses for category {} for user {}", category, userId);
        // Call the ExpenseService to fetch expenses by category
        List<Expense> response = expenseService.getExpensesByCategory(userId, category);
        return ResponseEntity.ok(response);
    }

    // Create a new expense via GraphQL mutation in ExpenseService
    @PostMapping(value = "/user/{userId}/expenses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExpense(@PathVariable("userId") Long userId, @RequestBody Expense expense) throws Exception {
        log.debug("Adding new expense for user {}: {}", userId, expense);
        // Call ExpenseService's mutation to add a new expense
        Expense response = expenseService.addExpense(userId, expense);
        return ResponseEntity.ok(response);
    }

}