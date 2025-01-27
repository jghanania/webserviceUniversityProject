package edu.fra.uas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fra.uas.model.Expense;
import edu.fra.uas.model.CategoryTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper; // For dynamic deserialization

    // Get the service address from application.properties
    @Value("${expenseServiceUrl}")
    private String expenseServiceUrl;

    // Fetch an expense using GraphQL query
    public Expense getExpenseById(Long userId, Long expenseId) throws Exception {
        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  ExpenseById(id: \\\"%d\\\") {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            expenseId
        );

        Map<String, Object> response = executeGraphQL(query);

        // Extract ExpenseById from the response
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return objectMapper.convertValue(data.get("ExpenseById"), Expense.class);
    }

    // Fetch total expenses by category
    public List<CategoryTotal> getTotalExpensesByCategory(Long userId) throws Exception {
        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  totalExpensesByCategory(user: %d) {\\n    category\\n    currency\\n    totalValue\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            userId
        );

        Map<String, Object> response = executeGraphQL(query);

        // Extract totalExpensesByCategory from the response
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return objectMapper.convertValue(
            data.get("totalExpensesByCategory"),
            new TypeReference<List<CategoryTotal>>() {}
        );
    }

    // Fetch expenses by category
    public List<Expense> getExpensesByCategory(Long userId, String category) throws Exception {
        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  expensesFromCategories(categories: \\\"%s\\\", user: %d) {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            category, userId
        );

        Map<String, Object> response = executeGraphQL(query);

        // Extract expensesFromCategories from the response
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return objectMapper.convertValue(
            data.get("expensesFromCategories"),
            new TypeReference<List<Expense>>() {}
        );
    }

    // Add a new expense via GraphQL mutation
    public Expense addExpense(Long userId, Expense expense) throws Exception {
        String mutation = String.format(
            "{\"query\":\"mutation MyMutation {\\n  addExpense(currency: \\\"%s\\\", user: %d, value: %f, category: \\\"%s\\\") {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyMutation\"}",
            expense.getCurrency(), userId, expense.getValue(), expense.getCategory()
        );

        Map<String, Object> response = executeGraphQL(mutation);

        // Extract the new Expense from the response
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return objectMapper.convertValue(data.get("addExpense"), Expense.class);
    }

    // Helper function to execute GraphQL query/mutation
    private Map<String, Object> executeGraphQL(String query) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        ResponseEntity<String> responseEntity =
            restTemplate.exchange(expenseServiceUrl + "/graphql", HttpMethod.POST, entity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Parse the response body into a Map
            String responseBody = responseEntity.getBody();
            return objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
        } else {
            throw new RuntimeException("Failed to execute GraphQL query: " + responseEntity.getStatusCode());
        }
    }
}
