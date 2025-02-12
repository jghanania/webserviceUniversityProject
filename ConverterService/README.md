# ConverterService Dokumentation

**Speicherort:** Diese Datei sollte im Ordner `ConverterService/README.md` gespeichert werden, damit die Dokumentation direkt mit dem Service verknüpft ist.

## Übersicht
Der ConverterService ist eine Spring Boot-Anwendung, die als Microservice für die Umrechnung von Währungen fungiert. Er ruft aktuelle Wechselkurse von der CurrencyLayer API ab und ermöglicht Echtzeit-Umrechnungen zwischen verschiedenen Währungen, die alle 60 Minuten aktualisiert werden. Dieser Service ist Teil eines größeren Systems und kommuniziert mit einem API-Gateway, das als zentraler Zugriffspunkt für alle Clients dient.

## Architektur
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

## Technologie-Stack
- **Spring Boot** – Framework für die REST-API
- **Java 17** – Programmiersprache
- **RestTemplate** – HTTP-Anfragen an die externe API
- **CurrencyLayer API** – Externe API für Wechselkurse

## Verbesserungsmöglichkeiten
1. **API-Schlüssel aus dem Code entfernen** und in `application.properties` auslagern.
2. **Fehlertoleranz erhöhen**, indem Fehler von der API abgefangen und entsprechende Meldungen zurückgegeben werden.
3. **Caching einführen**, um Performance zu verbessern und API-Anfragen zu reduzieren.

## Implementierte User Stories

| **ID** | **User Story** | **Beschreibung** |
|-------|----------------------------------|--------------------------------------------------------------|
| 1 | Währungsumrechnung für einen bestimmten Betrag | *Als Nutzer* möchte ich einen Betrag in einer bestimmten Währung in eine andere Währung umrechnen können, *damit* ich den aktuellen Wert in meiner Zielwährung kenne. |
| 2 | Liste der unterstützten Währungen abrufen | *Als Nutzer* möchte ich eine Liste aller unterstützten Währungen abrufen, *damit* ich weiß, welche Währungen ich umrechnen kann. |
| 3 | Mehrere Währungen auf einmal umrechnen | *Als Nutzer* möchte ich einen Betrag in mehrere Währungen gleichzeitig umrechnen, *damit* ich die aktuellen Wechselkurse für verschiedene Währungen vergleichen kann. |

## Noch nicht implementierte User Stories

| **ID** | **User Story** | **Beschreibung** |
|-------|----------------------------------|--------------------------------------------------------------|
| 4 | Caching für bessere Performance einführen | *Als Nutzer* möchte ich, dass Währungsumrechnungen schneller erfolgen, *damit* ich nicht unnötig lange auf eine Antwort warten muss. |
| 5 | Unterstützung für historische Wechselkurse | *Als Nutzer* möchte ich Wechselkurse zu einem bestimmten Datum abrufen können, *damit* ich historische Daten für meine Finanzplanung nutzen kann. |
| 6 | API-Key in der Konfigurationsdatei speichern | *Als Entwickler* möchte ich den API-Schlüssel nicht im Code speichern, *damit* keine Sicherheitsrisiken entstehen. |
| 7 | Unterstützung für Kryptowährungen hinzufügen | *Als Nutzer* möchte ich auch Kryptowährungen wie Bitcoin oder Ethereum umrechnen können, *damit* ich die aktuellen Wechselkurse mit Fiat-Währungen vergleichen kann. |


## Setup & Installation
### 1. Voraussetzungen
- **Java 17+** installiert
- **Maven** installiert

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

## Lizenz
Dieses Projekt steht unter der **MIT-Lizenz**.

