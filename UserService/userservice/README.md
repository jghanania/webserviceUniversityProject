
**📂 Speicherort:** Diese Datei sollte im Ordner `UserService/README.md` gespeichert werden, damit die Dokumentation direkt mit dem Service verknüpft ist.# UserService Documentation

## # UserService Documentation

## 1. Funktionsbeschreibung
Der **UserService** stellt eine REST-API zur Verwaltung von Benutzern bereit. 
Er ermöglicht das Abrufen, Erstellen, Bearbeiten und Löschen von Benutzerdaten.

## 2. Schnittstellendefinition

| Methode  | Endpoint        | Beschreibung                           |
|----------|---------------|--------------------------------------|
| **GET**  | `/users`      | Gibt eine Liste aller User zurück   |
| **GET**  | `/users/{id}` | Ruft einen bestimmten User ab       |
| **POST** | `/users`      | Erstellt einen neuen User           |
| **PUT**  | `/users/{id}` | Aktualisiert einen bestehenden User |
| **DELETE** | `/users/{id}` | Löscht einen User                  |

## 3. User Stories


| Als...  | möchte ich …                                      | damit/weil/denn … |
|---------|-------------------------------------------------|-------------------|
| **Nutzer** | eine Liste aller vorhandenen User abfragen | ich eine Übersicht der vorhandenen User habe. |
| **Nutzer** | eine UserID angeben | ich den dazugehörigen User finde. |
| **Nutzer** | einen User angeben | ich einen neuen User erstelle. |
| **Nutzer** | einen User mit UserID angeben | ich den passenden User editiere. |
| **Nutzer** | eine UserID angeben | ich den entsprechenden User lösche. |

## 4. Features
- **User erstellen** (`POST /users`)  
  **Request:**  
  - **Methode:** `POST`
  - **URL:** `http://localhost:8082/users`
  - **Body (JSON):**  
    ```json
    {
      "name": "Max Mustermann",
      "email": "max@example.com"
    }
    ```
  - **Response:**  
    ```json
    {
      "id": 1,
      "name": "Max Mustermann",
      "email": "max@example.com"
    }
    ```

- **User abrufen** (`GET /users/{id}`)  
  **Request:**  
  - **Methode:** `GET`
  - **URL:** `http://localhost:8082/users/1`
  - **Response:**  
    ```json
    {
      "id": 1,
      "name": "Max Mustermann",
      "email": "max@example.com"
    }
    ```

- **User löschen** (`DELETE /users/{id}`)  
  **Request:**  
  - **Methode:** `DELETE`
  - **URL:** `http://localhost:8082/users/2`
  - **Response:**  
    ```json
    {
      "message": "User mit ID 2 wurde gelöscht"
    }
    ```

- **Liste aller User abrufen** (`GET /users`)  
  **Request:**  
  - **Methode:** `GET`
  - **URL:** `http://localhost:8082/users`
  - **Response:**  
    ```json
    [
      {
        "id": 1,
        "name": "Max Mustermann",
        "email": "max@example.com"
      },
      {
        "id": 2,
        "name": "Erika Musterfrau",
        "email": "erika@example.com"
      }
    ]
    ```

## Technologien
- **Spring Boot 3.4.2**
- **H2-Datenbank**
- **Maven**

## Installation
1. Klone das Repository:
   ```bash
   git clone <REPO_URL>
