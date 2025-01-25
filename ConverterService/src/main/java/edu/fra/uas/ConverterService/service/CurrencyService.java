package edu.fra.uas.ConverterService.service;

import edu.fra.uas.ConverterService.model.CurrencyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Währungsumrechnungsservice, welches die Geschäftslogik enthält.
 * Unter anderem werden alle unterstützten Währungen gelistet 
 * und eine Funktion zum Umrechnen von einer Ausgangswährung in eine Zielwährung. 
 */
@Service
public class CurrencyService {
    private String apiKey = "3a00ed58c0429e54c480e5cc4ee2d15a";
    private final String CURRENCYLAYER_API_LIST_URL = "https://api.currencylayer.com/list?access_key=" + apiKey;
    private final String CURRENCYLAYER_API_CONVERT_URL = "https://api.currencylayer.com/convert?access_key=" + apiKey;
    private final String CURRENCYLAYER_API_MULTIPLE_CHANGE = "https://api.currencylayer.com/live?access_key=" + apiKey;
    /**
     * Abrufen aller unterstützten Währungen.
     * @return Map mit Währungscodes und deren Namen
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getSupportedCurrencies() {
        RestTemplate restTemplate = new RestTemplate();

        // Anfrage an die Currency Layer API um Umrechnungskurse zu bekommen
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(CURRENCYLAYER_API_LIST_URL, Map.class);

        // Rückgabe der Währungen
        return (Map<String, String>) response.get("currencies");
    }

    /**
     * Führt eine Umrechnung zwischen zwei Währungen durch.
     * @param from Die Quellwährung (z. B. "USD")
     * @param to Die Zielwährung (z. B. "EUR")
     * @param amount Der Betrag in der Quellwährung
     * @return Der umgerechnete Betrag in der Zielwährung
     */
    public double convertCurrency(String from, String to, double amount) {
        RestTemplate restTemplate = new RestTemplate();

        // Anfrage-URL mit Parametern zusammenstellen
        String url = CURRENCYLAYER_API_CONVERT_URL +
                     "&from=" + from +
                     "&to=" + to +
                     "&amount=" + amount;

        // API-Antwort abrufen
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Fehlerbehandlung: Überprüfen, ob die API-Antwort erfolgreich ist
        if (response == null || !(Boolean) response.get("success")) {
            throw new RuntimeException("Fehler bei der Währungsumrechnung: " + response);
        }

        // Umgerechneten Betrag aus der Antwort extrahieren
        Double result = (Double) response.get("result");
        if (result == null) {
            throw new RuntimeException("Kein Ergebnis für die Währungsumrechnung verfügbar.");
        }

        return result;
    }

    public Map<String, Double> convertToMultiple(String fromCurrency, Double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = CURRENCYLAYER_API_MULTIPLE_CHANGE +
        "&source=" + fromCurrency;
       
        // Live-Daten abrufen
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Wechselkurse extrahieren
        @SuppressWarnings("unchecked")
        Map<String, Object> rates = (Map<String, Object>) response.get("quotes");
        Map<String, Double> doubleMap = convertValuesToDouble(rates);
        // doubleMap.forEach((key, value) -> System.out.println(key + ": " + value));
        doubleMap.replaceAll((key, value) -> value * amount);
        return doubleMap;
    }


    /**
     * Konvertiert alle Werte in einer Map<String, Object> zu einer Map<String, Double>.
     * @param rawMap Die ursprüngliche Map mit Werten vom Typ Object.
     * @return Eine neue Map mit Werten vom Typ Double.
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
