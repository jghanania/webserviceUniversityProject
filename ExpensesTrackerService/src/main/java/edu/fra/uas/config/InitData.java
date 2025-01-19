package edu.fra.uas.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.model.Expense;
import edu.fra.uas.service.ExpenseService;
import jakarta.annotation.PostConstruct;

/**
 * This class is responsible for initializing mock data in the application.
 * It runs automatically during application startup to populate the system
 * with predefined sample expenses.
 */
@Component
public class InitData {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(InitData.class);
    
    @Autowired
    ExpenseService expenseService;

    @PostConstruct
    public void init() {
        log.debug("### Initialize Data ###");

        // Creating and adding predefined expenses to the system
        log.debug("create expense 1");
        Expense expense = new Expense();
        expense.setCategory("ENTERTAINMENT"); // Setting the category
        expense.setCurrency("EUR");          // Setting the currency
        expense.setUser(1);                  // Setting the user ID
        expense.setValue(100);               // Setting the value
        expenseService.createExpense(expense); // Adding the expense to the service

        log.debug("create expense 1");
        expense = new Expense();
        expense.setCategory("ENTERTAINMENT");
        expense.setCurrency("EUR");
        expense.setUser(1);
        expense.setValue(20);
        expenseService.createExpense(expense);

        log.debug("create expense 1");
        expense = new Expense();
        expense.setCategory("TRANSPORT");
        expense.setCurrency("EUR");
        expense.setUser(1);
        expense.setValue(100);
        expenseService.createExpense(expense);

        log.debug("create expense 1");
        expense = new Expense();
        expense.setCategory("FOOD");
        expense.setCurrency("EUR");
        expense.setUser(1);
        expense.setValue(100);
        expenseService.createExpense(expense);

        log.debug("create expense 1");
        expense = new Expense();
        expense.setCategory("FOOD");
        expense.setCurrency("USD");
        expense.setUser(1);
        expense.setValue(50);
        expenseService.createExpense(expense);

        log.debug("create expense 1");
        expense = new Expense();
        expense.setCategory("FOOD");
        expense.setCurrency("EUR");
        expense.setUser(1);
        expense.setValue(70);
        expenseService.createExpense(expense);

        log.debug("### Data initialized ###");
    }

}
