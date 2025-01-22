package edu.fra.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import edu.fra.uas.model.Expense;

@Service
public class ExpenseService {

    @Autowired
    private RestTemplate restTemplate;

    private String expenseServiceUrl = "http://localhost:8081";  // URL for expense service

    // Fetch an expense using GraphQL query
    public ResponseEntity<?> getExpenseById(Long userId, Long expenseId) {
        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  ExpenseById(id: \\\"%d\\\") {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            expenseId
        );
        return executeGraphQL(query);
    }

    // Fetch total expenses by category
    public ResponseEntity<?> getTotalExpensesByCategory(Long userId) {
        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  totalExpensesByCategory(user: %d) {\\n    category\\n    currency\\n    totalValue\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            userId
        );
        return executeGraphQL(query);
    }

    // Fetch expenses by category
    public ResponseEntity<?> getExpensesByCategory(Long userId, String category) {
        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  expensesFromCategories(categories: \\\"%s\\\", user: %d) {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            category, userId
        );
        return executeGraphQL(query);
    }

    // Add a new expense via GraphQL mutation
    public ResponseEntity<?> addExpense(Long userId, Expense expense) {
        String mutation = String.format(
            "{\"query\":\"mutation MyMutation {\\n  addExpense(currency: \\\"%s\\\", user: %d, value: %d, category: \\\"%s\\\") {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyMutation\"}",
            expense.getCurrency(), userId, expense.getValue(), expense.getCategory()
        );
        return executeGraphQL(mutation);
    }

    // Helper function to execute GraphQL query/mutation
    private ResponseEntity<?> executeGraphQL(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String body = query;  // Directly use the formatted query as the body
        
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(expenseServiceUrl + "/graphql", HttpMethod.POST, entity, String.class);
    }
}