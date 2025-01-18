package edu.fra.uas.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.model.Expense;
import edu.fra.uas.service.ExpenseService;
import jakarta.annotation.PostConstruct;

@Component
public class InitData {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(InitData.class);
    
    @Autowired
    ExpenseService expenseService;

    @PostConstruct
    public void init() {
        log.debug("### Initialize Data ###");

        log.debug("create expense 1");
        Expense expense = new Expense();
        expense.setCategory("ENTERTAINMENT");
        expense.setCurrency("EUR");
        expense.setUser(1);
        expense.setValue(100);
        expenseService.createExpense(expense);

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
