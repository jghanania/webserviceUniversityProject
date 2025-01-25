package edu.fra.uas.ConverterService.service;

import edu.fra.uas.ConverterService.model.CurrencyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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

    /**
     * Abrufen aller unterstützten Währungen.
     * @return Map mit Währungscodes und deren Namen
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getSupportedCurrencies() {
        RestTemplate restTemplate = new RestTemplate();

        // Anfrage an die API
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
}
