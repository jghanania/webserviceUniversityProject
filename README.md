# Ausgabentracker

## Projektbeschreibung

Der **Ausgabentracker** ist eine Webanwendung zur Verwaltung von Finanzen. Die Anwendung ermöglicht es Benutzern, ihre **Einnahmen und Ausgaben** zu verfolgen und Währungen in **Echtzeit** umzurechnen. Die Bedienung erfolgt über die Konsole, einen GraphQL-Client oder Postman.

## Projektarchitektur

Die Anwendung basiert auf einer **Microservice-Architektur** mit folgenden Hauptkomponenten:

- **API Gateway**: Verbindet die verschiedenen Services und steuert die Anfragen.
- **Backend-Services**:
  - **Expenses Tracker Service**: Verwalten von Einnahmen und Ausgaben.
  - **Währungsrechner Service**: Echtzeit-Umrechnung von Währungen.
  - **User Service**: Verwaltung von Benutzerdaten.

## Features

- Verwaltung von Einnahmen und Ausgaben mit detaillierten Berichten
- Echtzeit-Währungsumrechnung für verschiedene Währungen
- Intuitive Bedienung über Konsole, GraphQL-Client oder Postman
- API Gateway zur zentralen Steuerung der Services

## Technologien & Werkzeuge

- **Backend**: Java mit Spring Boot
- **API Typen**: REST API & GraphQL, CurrencyLayer API (extern)
- **Entwicklungsumgebung**: Visual Studio Code
-  **Keine Datenbank

## Installation & Setup

### Voraussetzungen

- Java
- Git
- Visual Studio Code

### Lokale Installation

1. **Repository klonen**
   ```bash
   git clone https://github.com/WebApps-WiSe-24/webapp-mdm.git
   cd webapp-mdm

2. **


## Nutzung

- Einnahmen und Ausgaben erfassen und verwalten
- Währungen in Echtzeit umrechnen
- Abfragen über GraphQL-Client oder Postman durchführen

## Roadmap

- Verbesserung der Konsolenausgabe
- Erweiterung der Analysemöglichkeiten
- Unterstützung für Kryptowährungen

## Beitragende

- Emre
- Melissa
- Dani
- Jean-Gabriel

## Lizenz

Dieses Projekt steht unter der **MIT-Lizenz**.

