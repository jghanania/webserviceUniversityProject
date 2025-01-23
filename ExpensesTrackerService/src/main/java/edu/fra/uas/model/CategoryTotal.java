package edu.fra.uas.model;

/**
 * This class represents a summary of expenses for a specific category and currency.
 * It holds the total value of all expenses for a given category and currency pair.
 */
public class CategoryTotal {
    private String category;  // The category of the expenses (e.g., "Food", "Entertainment")
    private String currency;  // The currency of the expenses (e.g., "USD", "EUR")
    private long totalValue;  // The total value of expenses for the given category and currency


    // Constructor with all fields
    public CategoryTotal(String category, String currency, long totalValue) {
        this.category = category;
        this.currency = currency;
        this.totalValue = totalValue;
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

    public long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(long totalValue) {
        this.totalValue = totalValue;
    }
}
