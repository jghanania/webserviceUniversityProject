package edu.fra.uas.ConverterService.controller;

import edu.fra.uas.ConverterService.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/converter")
public class ConverterController {

    private final CurrencyService currencyService;

    // Constructor for Dependency Injection
    public ConverterController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Endpoint to retrieve all supported currencies.
     * @return Map with currency codes and their names
     */
    @GetMapping("/supported-currencies")
    public Map<String, String> getSupportedCurrencies() {
        return currencyService.getSupportedCurrencies();
    }

    /**
     * Endpoint: Convert an amount between two currencies.
     * @param from The source currency (e.g., "USD")
     * @param to The target currency (e.g., "EUR")
     * @param amount The amount in the source currency
     * @return The converted amount in the target currency
     */
    @GetMapping("/convert")
    public double convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount) {
        return currencyService.convertCurrency(fromCurrency, toCurrency, amount);
    }


    /**
     * Endpoint: Convert to multiple currencies.
     * @param fromCurrency The source currency
     * @param amount The amount in the source currency
     * @return A map with target currencies and their converted values
     */
    @GetMapping("/convertToMultipleCurrencies")
    public Map<String, Double> convertToMultipleCurrencies(
            @RequestParam String fromCurrency,
            @RequestParam Double amount) {
        return currencyService.convertToMultiple(fromCurrency, amount);
    }
}
