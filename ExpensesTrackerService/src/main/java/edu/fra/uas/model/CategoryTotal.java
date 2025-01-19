package edu.fra.uas.model;

public class CategoryTotal {
    private String category;
    private long totalValue;

    public CategoryTotal(String category, long totalValue) {
        this.category = category;
        this.totalValue = totalValue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(long totalValue) {
        this.totalValue = totalValue;
    }
}
