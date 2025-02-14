# API Gateway - Dokumentation

## Überblick

Das API Gateway dient als Vermittler zwischen Clients und mehreren Microservices und bietet eine einheitliche Schnittstelle für die Verwaltung von Ausgaben, Währungsumrechnungen und Benutzerinformationen. Es bearbeitet Anfragen und leitet diese an die entsprechenden Services weiter, während es Sicherheit, Validierung und Datenumwandlung gewährleistet.

## Dienste

Das API Gateway verbindet sich mit den folgenden Diensten:

- **ExpenseTrackerService**: Verwaltet Ausgaben, einschließlich Abruf, Erstellung, Aktualisierung und Löschung.
- **ConverterService**: Wandelt Ausgabenbeträge zwischen verschiedenen Währungen um.
- **UserService**: Verwaltet Benutzerinformationen und Validierung.

## Konfiguration

Das API Gateway ist auf externe Dienste angewiesen, deren URLs in `application.properties` konfiguriert sind:

```properties
# Expense Tracker Service URL
expenseServiceUrl=http://localhost:8081

# Currency Converter Service URL
converterServiceUrl=http://localhost:8082/api/converter/convert

# User Service URL
userServiceUrl=http://localhost:8083/users
```

Stellen Sie sicher, dass diese Dienste laufen und erreichbar sind, damit das Gateway ordnungsgemäß funktioniert.

## Nutzung

Die Nutzung wird ausführlich in der [Hauptdokumentation](../README.md) des Projekts beschrieben. Das sind Beispielbefehle zum Testen des Gateways.

`curl -X GET http://localhost:8080/api/user/1/expenses/3`

`curl -X GET http://localhost:8080/api/user/1/categories/sum`

`curl -X GET http://localhost:8080/api/user/1/categories/FOOD`

`curl -X POST http://localhost:8080/api/user/1/expenses -H "Content-Type: application/json" -d '{"value": 100 ,"category": "FOOD","currency": "EUR"}'`

## Abhängigkeiten

Das API Gateway verwendet die folgenden Abhängigkeiten:

- **Spring Boot**: Framework zum Erstellen von Microservices.
- **Spring Web**: Handhabt RESTful API-Anfragen.
- **Spring Boot Starter Web**: Stellt einen eingebetteten Server bereit.
- **RestTemplate**: Verwaltet HTTP-Anfragen an Microservices.
- **Jackson**: Serialisiert und deserialisiert JSON-Daten.



