package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.fra.uas.service.*;
import edu.fra.uas.model.CategoryTotal;
import edu.fra.uas.model.Expense;
import edu.fra.uas.model.User;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {

    private static final Logger log = LoggerFactory.getLogger(ApiGatewayController.class);

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private UserService userService;

    // Get a list of all users
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        log.debug("Fetching all users");
        List<User> response = userService.getAllUsers(); // Call UserService to fetch all users

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(response); // Return 200 OK with the user list
    }

    // Get a specific user by ID
    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        log.debug("Fetching user with ID {}", userId);
        User response = userService.getUserById(userId);

        if (response == null) {
            return ResponseEntity.notFound().build(); // Return 404 when not found
        }
        return ResponseEntity.ok(response);
    }

    // Create a new user
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        log.debug("Adding new user: {}", user);
        User response = userService.addUser(user); // Call UserService to create a new user
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Delete a user by ID
    @DeleteMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        log.debug("Deleting user with ID {}", userId);
        
        boolean deleted = userService.deleteUser(userId);
        
        if (!deleted) {
            return ResponseEntity.notFound().build(); // Return 404 when not found
        }
    
        return ResponseEntity.noContent().build(); // 204 No Content on successful deletion
    }

    // Get all expenses for a user
    @GetMapping(value = "/users/{userId}/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllExpensesForUser(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "currency", required = false) String currency) throws Exception {

        // Validate if the user exists
        userService.validateUserExists(userId);

        log.debug("Fetching all expenses for user {}", userId);

        // Fetch all expenses from the ExpenseService
        List<Expense> response = expenseService.getAllExpensesForUser(userId);

        // If currency is provided, modify the expense by converting it
        if (currency != null && !currency.isEmpty()) {
            log.debug("Converting expense to currency {}", currency);
            response = converterService.convertExpenses(response, currency);
        }

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }

        return ResponseEntity.ok(response); // Return 200 OK with the user list
    }

    // Get a specific expense using GraphQL from ExpenseService
    @GetMapping(value = "/users/{userId}/expenses/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExpense(
            @PathVariable("userId") Long userId,
            @PathVariable("expenseId") Long expenseId,
            @RequestParam(value = "currency", required = false) String currency) throws Exception {

        // Validate if the user exists
        userService.validateUserExists(userId);

        log.debug("Fetching expense with ID {} for user {}", expenseId, userId);

        // Call the ExpenseService's GraphQL endpoint to fetch the expense
        Expense expense = expenseService.getExpenseById(userId, expenseId);

        // If currency is provided, modify the expense by converting it
        if (currency != null && !currency.isEmpty()) {
            log.debug("Converting expense to currency {}", currency);
            expense = converterService.convert(expense, currency);
        }

        if (expense == null) {
            return ResponseEntity.notFound().build(); // Return 404 when not found
        }
        // Return the expense in the response
        return ResponseEntity.ok(expense);
    }

    // Get total expenses by category from ExpenseService
    @GetMapping(value = "/users/{userId}/categories/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTotalExpensesByCategory(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "currency", required = false) String currency) throws Exception {

        // Validate if the user exists
        userService.validateUserExists(userId);

        log.debug("Fetching total expenses by category for user {}", userId);
        // Call the ExpenseService to get total expenses by category
        List<CategoryTotal> response = expenseService.getTotalExpensesByCategory(userId);

        // If a currency is provided, convert the category totals
        if (currency != null && !currency.isEmpty()) {
            response = converterService.convertCategoryTotals(response, currency);
        }

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(response);
    }

    // Get expenses for a specific category from ExpenseService
    @GetMapping(value = "/users/{userId}/categories/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExpensesByCategory(
            @PathVariable("userId") Long userId,
            @PathVariable("category") String category,
            @RequestParam(value = "currency", required = false) String currency) throws Exception {

        // Validate if the user exists
        userService.validateUserExists(userId);

        log.debug("Fetching expenses for category {} for user {}", category, userId);
        // Call the ExpenseService to fetch expenses by category
        List<Expense> response = expenseService.getExpensesByCategory(userId, category);

        // If a currency is provided, convert the expenses
        if (currency != null && !currency.isEmpty()) {
            response = converterService.convertExpenses(response, currency);
        }

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/users/{userId}/expenses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExpense(@PathVariable("userId") Long userId, @RequestBody Expense expense)
            throws Exception {

        // Validate if the user exists
        userService.validateUserExists(userId);

        log.debug("Adding new expense for user {}: {}", userId, expense);

        // Call ExpenseService's mutation to add a new expense
        Expense createdExpense = expenseService.addExpense(userId, expense);

        // If the createdExpense is null, return 422 Unprocessable Entity
        if (createdExpense == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("error", "Expense could not be created"));
        }

        // Build the Location URI for the created expense
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{expenseId}")
                .buildAndExpand(createdExpense.getId())
                .toUri();

        // Return 201 Created with Location header
        return ResponseEntity.created(location).body(createdExpense);
    }

    // Delete an expense for a user
    @DeleteMapping(value = "/users/{userId}/expenses/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteExpense(
            @PathVariable("userId") Long userId,
            @PathVariable("expenseId") Long expenseId) throws Exception {

        // Validate if the user exists
        userService.validateUserExists(userId);

        log.debug("Deleting expense with ID {} for user {}", expenseId, userId);

        // Delete the expense via ExpenseService
        Expense deletedExpense = expenseService.deleteExpense(expenseId);

        if (deletedExpense == null) {
            return ResponseEntity.notFound().build(); // Return 404 when not found
        }
        return ResponseEntity.noContent().build();
    }

}