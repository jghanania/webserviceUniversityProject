package edu.fra.uas.ConverterService.controller;

import edu.fra.uas.ConverterService.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/converter")
public class ConverterController {

    private final CurrencyService currencyService;

    // Konstruktor für Dependency Injection
    public ConverterController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Endpunkt zum Abrufen aller unterstützten Währungen.
     * @return Map mit Währungscodes und deren Namen
     */
    @GetMapping("/supported-currencies")
    public Map<String, String> getSupportedCurrencies() {
        return currencyService.getSupportedCurrencies();
    }

    /**
     * Endpunkt: Betrag zwischen zwei Währungen umrechnen.
     * @param from Die Quellwährung (z. B. "USD")
     * @param to Die Zielwährung (z. B. "EUR")
     * @param amount Der Betrag in der Quellwährung
     * @return Der umgerechnete Betrag in der Zielwährung
     */
    @GetMapping("/convert")
    public double convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        return currencyService.convertCurrency(from, to, amount);
    }
}
