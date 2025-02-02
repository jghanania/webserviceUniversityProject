# ConverterService Dokumentation

**📂 Speicherort:** Diese Datei sollte im Ordner `ConverterService/README.md` gespeichert werden, damit die Dokumentation direkt mit dem Service verknüpft ist.

## 📊 Übersicht
Der **ConverterService** ist eine Spring Boot Anwendung, die Währungsumrechnungen durchführt. Er verwendet die **CurrencyLayer API**, um aktuelle Wechselkurse zu beziehen und Umrechnungen zwischen verschiedenen Währungen zu ermöglichen.

## 📚 Architektur
Der Service besteht aus den folgenden Hauptkomponenten:

### 1. **`ConverterController.java` (REST-API Controller)**
Dieser Controller stellt REST-Endpunkte bereit, um Währungen zu konvertieren und unterstützte Währungen abzurufen.

**Verfügbare Endpunkte:**

| Methode | Endpoint | Beschreibung |
|---------|---------|-------------|
| GET | `/api/converter/supported-currencies` | Liste aller Währungen |
| GET | `/api/converter/convert?fromCurrency=USD&toCurrency=EUR&amount=100` | Währungsumrechnung |
| GET | `/api/converter/convertToMultipleCurrencies?fromCurrency=USD&amount=100` | Umrechnung in mehrere Währungen |

### 2. **`CurrencyService.java` (Service-Klasse)**
Diese Klasse enthält die Logik zur Kommunikation mit der **CurrencyLayer API** und verarbeitet die Antworten.

**Hauptmethoden:**
- `getSupportedCurrencies()`  
  ➡ Fragt die API `https://api.currencylayer.com/list` ab und gibt die Liste aller Währungen zurück.

- `convertCurrency(String from, String to, double amount)`  
  ➡ Fragt die API `https://api.currencylayer.com/convert` mit den angegebenen Währungen und Betrag ab und gibt den umgerechneten Betrag zurück.

- `convertToMultiple(String fromCurrency, Double amount)`  
  ➡ Fragt die API `https://api.currencylayer.com/live` ab, um den angegebenen Betrag in mehrere Währungen gleichzeitig umzurechnen.

## 🚀 Technologie-Stack
- **Spring Boot** – Framework für die REST-API
- **Java 17** – Programmiersprache
- **RestTemplate** – HTTP-Anfragen an die externe API
- **CurrencyLayer API** – Externe API für Wechselkurse

## 🔧 Verbesserungsmöglichkeiten
1. **API-Schlüssel aus dem Code entfernen** und in `application.properties` auslagern.
2. **Fehlertoleranz erhöhen**, indem Fehler von der API abgefangen und entsprechende Meldungen zurückgegeben werden.
3. **Caching einführen**, um Performance zu verbessern und API-Anfragen zu reduzieren.

## 🛠 Setup & Installation
### 1. Voraussetzungen
- **Java 17+** installiert
- **Maven** installiert
- Api-Gateway muss zuerst ausgeführt werden

### 2. Projekt ausführen
1. Repository klonen:
   ```bash
   git clone <repository-url>
   cd ConverterService
   ```
2. Anwendung starten:
   ```bash
   mvn spring-boot:run
   ```

3. API im Browser oder mit **Postman** testen:
   ```bash
   http://localhost:8080/api/converter/convert?fromCurrency=USD&toCurrency=EUR&amount=100
   ```

## 📖 Lizenz
Dieses Projekt steht unter der **MIT-Lizenz**.

