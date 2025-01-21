package edu.fra.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Service
public class ExpenseService {

    @Autowired
    private RestTemplate restTemplate;

    private String expenseServiceUrl = "http://localhost:8081";  // URL for expense service

    // Fetch an expense using GraphQL query
    public ResponseEntity<?> getExpenseById(Long userId, Long expenseId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String query = String.format(
            "{\"query\":\"query MyQuery {\\n  ExpenseById(id: \\\"%d\\\") {\\n    category\\n    currency\\n    id\\n    user\\n    value\\n  }\\n}\",\"operationName\":\"MyQuery\"}",
            expenseId
        );

        HttpEntity<String> entity = new HttpEntity<>(query, headers);
        return restTemplate.exchange(expenseServiceUrl + "/graphql", HttpMethod.POST, entity, String.class);
    }
}
