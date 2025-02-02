package edu.fra.uas.ConverterService.service;

import edu.fra.uas.ConverterService.model.CurrencyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Currency conversion service that contains the business logic.
 * Among other things, it lists all supported currencies
 * and provides a function to convert from a source currency to a target currency.
 */
//apikeys: 3a00ed58c0429e54c480e5cc4ee2d15a
//apikeys: 447f1d385a8365162bdf13d4c49d8f64
//apikeys: a2c3a729f8948fd37d82edf502199254

@Service
public class CurrencyService {
    private String apiKey = "3a00ed58c0429e54c480e5cc4ee2d15a";
    private final String CURRENCYLAYER_API_LIST_URL = "https://api.currencylayer.com/list?access_key=" + apiKey;
    private final String CURRENCYLAYER_API_CONVERT_URL = "https://api.currencylayer.com/convert?access_key=" + apiKey;
    private final String CURRENCYLAYER_API_MULTIPLE_CHANGE = "https://api.currencylayer.com/live?access_key=" + apiKey;
    /**
     * Retrieves all supported currencies.
     * @return Map with currency codes and their names
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getSupportedCurrencies() {
        RestTemplate restTemplate = new RestTemplate();

        // Request to the Currency Layer API to get conversion rates
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(CURRENCYLAYER_API_LIST_URL, Map.class);

        // Return the currencies
        return (Map<String, String>) response.get("currencies");
    }

    /**
     * Performs a conversion between two currencies.
     * @param from The source currency (e.g., "USD")
     * @param to The target currency (e.g., "EUR")
     * @param amount The amount in the source currency
     * @return The converted amount in the target currency
     */
    public double convertCurrency(String from, String to, double amount) {
        RestTemplate restTemplate = new RestTemplate();

        // Construct request URL with parameters
        String url = CURRENCYLAYER_API_CONVERT_URL +
                     "&from=" + from +
                     "&to=" + to +
                     "&amount=" + amount;

        // Retrieve API response
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Error handling: Check if the API response was successful
        if (response == null || !(Boolean) response.get("success")) {
            throw new RuntimeException("Fehler bei der Währungsumrechnung: " + response);
        }

        // Extract the converted amount from the response
        Double result = (Double) response.get("result");
        if (result == null) {
            throw new RuntimeException("Kein Ergebnis für die Währungsumrechnung verfügbar.");
        }

        return result;
    }

    /**
     * Converts a given amount from one currency to multiple target currencies.
     * @param fromCurrency The source currency
     * @param amount The amount in the source currency
     * @return A map with target currencies and their converted values
     */
    public Map<String, Double> convertToMultiple(String fromCurrency, Double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = CURRENCYLAYER_API_MULTIPLE_CHANGE +
        "&source=" + fromCurrency;
       
        // Fetch live data
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Extract exchange rates
        @SuppressWarnings("unchecked")
        Map<String, Object> rates = (Map<String, Object>) response.get("quotes");
        Map<String, Double> doubleMap = convertValuesToDouble(rates);
        // doubleMap.forEach((key, value) -> System.out.println(key + ": " + value));
        //Multiply exchange rates with the given amount
        doubleMap.replaceAll((key, value) -> value * amount);
        return doubleMap;
    }


    /**
     * Converts all values in a Map<String, Object> to a Map<String, Double>.
     * @param rawMap The original map with values of type Object.
     * @return A new map with values of type Double.
     */
    public Map<String, Double> convertValuesToDouble(Map<String, Object> rawMap) {
        Map<String, Double> doubleMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Number) {
                // Wenn der Wert numerisch ist, konvertiere ihn zu Double
                doubleMap.put(key, ((Number) value).doubleValue());
            } else {
                // Wenn der Wert kein numerischer Typ ist, ignoriere ihn oder wirf eine Ausnahme
                System.out.println("Warnung: Wert für Schlüssel '" + key + "' ist kein unterstützter numerischer Typ (" + value.getClass() + ").");
            }
        }

        return doubleMap;
    }
}
