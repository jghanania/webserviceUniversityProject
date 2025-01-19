package edu.fra.uas.model;

public class CategoryTotal {
    private String category;
    private String currency; // Add currency field
    private long totalValue;

    public CategoryTotal(String category, String currency, long totalValue) {
        this.category = category;
        this.currency = currency;
        this.totalValue = totalValue;
    }

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
