package edu.fra.uas.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents an expense entry in the system.
 * It contains information about the user, category, value, and currency of an expense.
 * Implements Serializable to allow it to be serialized if needed for communication or persistence.
 */
public class Expense implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(Expense.class);

    private long id;           // Unique identifier for the expense
    private long user;         // User associated with the expense
    private String category;   // Category of the expense (e.g., "Food", "Entertainment")
    private double value;        // Value of the expense (in the specified currency)
    private String currency;   // Currency of the expense (e.g., "USD", "EUR")

    // Default constructor
    public Expense() {
        log.debug("Expense created without values");
    }

    // Constructor with all fields
    public Expense(long id, long user, String category, double value, String currency) {
        log.debug("Expense created with values - id: " + id + ", user: " + user + ", category: " + category + 
                  ", value: " + value + ", currency: " + currency);
        this.id = id;
        this.user = user;
        this.category = category;
        this.value = value;
        this.currency = currency;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // Override equals method to compares two Expense objects. Returns true if all fields are equal.
    @Override
    public boolean equals(Object object) {
        if (object == null) 
            return false;
        if (object == this) 
            return true;
        if (this.getClass() != object.getClass()) 
            return false;
        Expense expense = (Expense) object;
        return this.id == expense.id &&
               this.user == expense.user &&
               (this.category == null ? expense.category == null : this.category.equals(expense.category)) &&
               this.value == expense.value &&
               (this.currency == null ? expense.currency == null : this.currency.equals(expense.currency));
    }


    // Override toString method
    @Override
    public String toString() {
        return "Expense [id=" + id + ", user=" + user + ", category=" + category + ", value=" + value + ", currency=" + currency + "]";
    }
}
