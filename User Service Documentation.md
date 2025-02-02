# UserService Documentation

## Beschreibung
Ein einfacher RESTful UserService, der CRUD-Operationen für Benutzerdaten bereitstellt.

## Features
- **User erstellen** (`POST /users`)
   POST http://localhost:8082/users Body: raw + JSON
- **User abrufen** (`GET /users/{id}`)
  GET http://localhost:8082/users/1
- **User löschen** (`DELETE /users/{id}`)
DELETE http://localhost:8082/users/2
- **Liste abrufen** (`GET /users`)
GET http://localhost:8082/users 


## Technologien
- Spring Boot 3.4.2
- H2-Datenbank
- Maven

## Installation
1. Klone das Repository:
   ```bash
   git clone <REPO_URL>
