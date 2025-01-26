package edu.fra.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.fra.uas.model.Expense;
import edu.fra.uas.model.CategoryTotal;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConverterService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CONVERSION_SERVICE_URL = "http://localhost:8082/api/converter/convert";

    /**
     * Converts the currency of the given expense to the target currency.
     *
     * @param expense       The expense to convert.
     * @param targetCurrency The target currency to convert to.
     * @return The modified Expense with the updated value and currency.
     */
    public Expense convert(Expense expense, String targetCurrency) {
        String currentCurrency = expense.getCurrency();
        double value = expense.getValue();

        // If the current currency is the same as the target currency, no conversion is needed
        if (currentCurrency.equalsIgnoreCase(targetCurrency)) {
            return expense;
        }

        // Build the REST request URL
        String url = String.format("%s?fromCurrency=%s&toCurrency=%s&amount=%f", 
                CONVERSION_SERVICE_URL, currentCurrency, targetCurrency, value);

        // Send the REST request and get the converted amount as a response
        Double convertedValue = restTemplate.getForObject(url, Double.class);

        // Update the expense with the new currency and value
        expense.setValue(convertedValue);
        expense.setCurrency(targetCurrency);

        return expense;
    }

    /**
     * Converts the currency of a list of expenses to the target currency.
     *
     * @param expenses      The list of expenses to convert.
     * @param targetCurrency The target currency to convert to.
     * @return The list of modified expenses with the updated values and currencies.
     */
    public List<Expense> convertExpenses(List<Expense> expenses, String targetCurrency) {
        // Map over the list of expenses and convert each one
        return expenses.stream()
                .map(expense -> convert(expense, targetCurrency))
                .collect(Collectors.toList());
    }

    /**
     * Converts the currency of a list of category totals to the target currency.
     *
     * @param categoryTotals The list of category totals to convert.
     * @param targetCurrency The target currency to convert to.
     * @return The list of modified category totals with the updated values and currencies.
     */
    public List<CategoryTotal> convertCategoryTotals(List<CategoryTotal> categoryTotals, String targetCurrency) {
        // Convert all CategoryTotal objects
        List<CategoryTotal> converted = categoryTotals.stream()
                .map(categoryTotal -> convertCategoryTotal(categoryTotal, targetCurrency))
                .collect(Collectors.toList());

        // Merge category totals with the same category and currency
        return mergedCategoryTotals(converted);
    }

    /**
     * Converts the currency of a single category total to the target currency.
     *
     * @param categoryTotal The category total to convert.
     * @param targetCurrency The target currency to convert to.
     * @return The modified category total with the updated value and currency.
     */
    private CategoryTotal convertCategoryTotal(CategoryTotal categoryTotal, String targetCurrency) {
        String currentCurrency = categoryTotal.getCurrency();
        double value = categoryTotal.getTotalValue();

        // If the current currency is the same as the target currency, no conversion is needed
        if (currentCurrency.equalsIgnoreCase(targetCurrency)) {
            return categoryTotal;
        }

        // Build the REST request URL
        String url = String.format("%s?fromCurrency=%s&toCurrency=%s&amount=%f",
                CONVERSION_SERVICE_URL, currentCurrency, targetCurrency, value);

        // Send the REST request and get the converted amount as a response
        Double convertedValue = restTemplate.getForObject(url, Double.class);

        // Update the category total with the new currency and value
        categoryTotal.setTotalValue(convertedValue);
        categoryTotal.setCurrency(targetCurrency);

        return categoryTotal;
    }

    /**
     * Merges a list of CategoryTotal objects by summing their totalValue if they have
     * the same category and currency.
     *
     * @param categoryTotals The list of category totals to merge.
     * @return A merged list of category totals.
     */
    private List<CategoryTotal> mergedCategoryTotals(List<CategoryTotal> categoryTotals) {
        // Group by category and currency, then sum the total values
        Map<String, CategoryTotal> mergedMap = categoryTotals.stream()
                .collect(Collectors.toMap(
                        // Create a key combining category and currency
                        ct -> ct.getCategory() + "_" + ct.getCurrency(),
                        // Use the CategoryTotal as the initial value
                        ct -> ct,
                        // Merge function: sum the totalValue of duplicates
                        (ct1, ct2) -> {
                            ct1.setTotalValue(ct1.getTotalValue() + ct2.getTotalValue());
                            return ct1;
                        }
                ));

        // Return the merged CategoryTotal objects as a list
        return mergedMap.values().stream().collect(Collectors.toList());
    }
}
