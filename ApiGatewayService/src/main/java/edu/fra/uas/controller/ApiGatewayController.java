package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.fra.uas.service.*;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {
    
    private static final Logger log = LoggerFactory.getLogger(ApiGatewayController.class);

    @Autowired
    private ExpenseService expenseService;

    // Get a specific expense using GraphQL from ExpenseService
    @GetMapping(value = "/user/{userId}/expenses/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExpense(@PathVariable("userId") Long userId, @PathVariable("expenseId") Long expenseId) {
        log.debug("Fetching expense with ID {} for user {}", expenseId, userId);
        
        // Call the ExpenseService's GraphQL endpoint to fetch the expense
        return expenseService.getExpenseById(userId, expenseId);
    }

}
