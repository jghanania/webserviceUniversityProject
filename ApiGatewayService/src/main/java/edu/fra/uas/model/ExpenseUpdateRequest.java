package edu.fra.uas.model;

/**
 * Represents an update request for an expense.
 * This class is used to capture partial updates to an expense entity,
 * allowing users to modify one or more fields without requiring all fields to
 * be set.
 */
public class ExpenseUpdateRequest {

    // Fields representing optional expense details
    private String category; // The category of the expense
    private String currency; // The currency of the expense
    private Double value;    // The value/amount of the expense

    // Constructors
    public ExpenseUpdateRequest() {
    }

    public ExpenseUpdateRequest(String category, String currency, Double value) {
        this.category = category;
        this.currency = currency;
        this.value = value;
    }

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
