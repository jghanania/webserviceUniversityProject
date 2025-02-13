# Ausgaben Manager: Dokumentation

## Projektbeschreibung

Der **Ausgabentracker** ist eine Webanwendung zur Verwaltung von Finanzen. Die Anwendung ermöglicht es Benutzern, ihre **Ausgaben** zu erfassen und Währungen in **Echtzeit** umzurechnen. Nutzer können Ausgaben hinzufügen, aktualisieren, löschen und nach verschiedenen Kriterien filtern. Eine Benutzerverwaltung stellt sicher, dass nur autorisierte Nutzer auf ihre jeweiligen Daten zugreifen können. Die Bedienung erfolgt über die Konsole, der Webseite Swagger UI oder Postman.

## Features

- Verwaltung Ausgaben mit Kategorisierung
- Echtzeit-Währungsumrechnung für verschiedene Währungen
- Intuitive Bedienung über Konsole, Swagger UI oder Postman
- API Gateway zur zentralen Steuerung der Services

## Projektarchitektur

Die Anwendung basiert auf einer **Microservice-Architektur** mit folgenden Hauptkomponenten:

- Die **Api Gateway** dient als zentraller eingangspunkt für die Anwendung und kommuniziert mit den anderen Webservicen um die Anfragen von den Clients zu erfüllen.  
Port: 8080  
Schnittstellen: REST  
[Link zur Dokumentation](ApiGatewayService/README.md)

- Der **Expense Tracker Service** bietet eine GraphQL-Schnittstelle zur Verwaltung von Ausgaben. Er ermöglicht das Abrufen, Erstellen, Aktualisieren und Löschen von Ausgaben für verschiedene Benutzer.  
Port: 8081  
Schnittstellen: GraphQL  
[Link zur Dokumentation](Expense/README.md)

- Der **Converter Service** stellt eine API zur Währungsumrechnung zur Verfügung. Mit ihm können Benutzer Währungsbeträge zwischen verschiedenen Währungen konvertieren und sich über unterstützte Währungen informieren.  
Port: 8082  
Schnittstellen: REST  
[Link zur Dokumentation](ConverterService/README.md)

- Der **User Service** ermöglicht die Verwaltung von Benutzern über eine REST-API. Er unterstützt grundlegende Operationen wie das Erstellen, Abrufen, Auflisten und Löschen von Benutzern  
Port: 8083  
Schnittstellen: REST  
[Link zur Dokumentation](UserService/README.md)


![Graph](AdditionalAssets/ProjektArchitekturPretty.png)



## Technologien & Werkzeuge

- **Backend**: Java mit Spring Boot
- **API Typen**: REST API & GraphQL, CurrencyLayer API (extern)
- **Entwicklungsumgebung**: Visual Studio Code

## Installation & Setup

### Voraussetzungen

- Java
- Git
- Visual Studio Code

### Lokale Installation

**Repository klonen**
   ```bash
   git clone https://github.com/WebApps-WiSe-24/webapp-mdm
   cd webapp-mdm
   ```

## Nutzung

Stellen Sie sicher, dass der Server läuft, bevor Sie versuchen, den Service zu nutzen. Alle vier Spring Boot Anwendungen müssen dazu gestartet werden.
   

### Zugriff über Swagger UI
Durch **Swagger UI** bietet unsere Webanwendung eine interaktive und benutzerfreundliche Oberfläche um die Endpunkte zu durchsuchen und Anfragen zu stellen. Folgen Sie diesen einfachen Schritten, um die Swagger UI zu nutzen:

1. **Swagger UI öffnen**  
   - Starten Sie den API Gateway-Dienst.  
   - Rufen Sie Swagger UI im Browser auf: http://localhost:8080/swagger-ui/index.html
     
2. **API-Endpunkte testen**  
   - Wählen Sie einen Endpunkt aus der Liste.  
   - Klicken Sie auf **"Try it out"** und geben Sie die erforderlichen Parameter ein.  
   - Führen Sie die Anfrage mit **"Execute"** aus.  
   - Überprüfen Sie die Antwort im unteren Bereich der Swagger UI.  


#### **API-Endpunkte & Funktionen**

| Methode | Endpunkt | Beschreibung |
|---------|-------------------------------------|---------------------------------------------|
| **GET**    | `/api/users/{userId}/expenses/{expenseId}` | Holt eine einzelne Ausgabe eines Nutzers. |
| **PUT**    | `/api/users/{userId}/expenses/{expenseId}` | Aktualisiert eine Ausgabe eines Nutzers. |
| **DELETE** | `/api/users/{userId}/expenses/{expenseId}` | Löscht eine bestimmte Ausgabe eines Nutzers. |
| **GET**    | `/api/users` | Ruft eine Liste aller Benutzer ab. |
| **POST**   | `/api/users` | Erstellt einen neuen Benutzer. |
| **GET**    | `/api/users/{userId}/expenses` | Listet alle Ausgaben eines Nutzers auf. |
| **POST**   | `/api/users/{userId}/expenses` | Erstellt eine neue Ausgabe für einen Nutzer. |
| **GET**    | `/api/users/{userId}` | Holt die Informationen eines bestimmten Nutzers. |
| **DELETE** | `/api/users/{userId}` | Löscht einen Benutzer. |
| **GET**    | `/api/users/{userId}/categories/{category}` | Holt die Gesamtausgaben einer bestimmten Kategorie. |
| **GET**    | `/api/users/{userId}/categories/sum` | Holt die Gesamtausgaben aller Kategorien für einen Nutzer. |

 🚀  


## Roadmap

- Verbesserung der Konsolenausgabe
- Erweiterung der Analysemöglichkeiten
- Unterstützung für Kryptowährungen

## Beitragende

- Emre Bugday (EmoBu)
- Meeraf Golja (Meeraf1)
- Daniel Barranco Delgado (danielbarrancodelgado)
- Jean-Gabriel Hanania (jghanania)


