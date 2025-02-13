# Documentation: Expenses Tracker Service
## API Type: GraphQL
### Planned functions
# Documentation: Expenses Tracker Service
## API Type: GraphQL
### Planned functions
## 1. Add Expense (Mutation)

| *Feld*    | *Typ*  | *Beschreibung*                                             |
|-------------|----------|---------------------------------------------------------------|
| id        | long     | Eine eindeutige ID für die Ausgabe.                           |
| value     | int      | Der Betrag der Ausgabe (z. B. 100).                           |
| currency  | string   | Die Währung der Ausgabe (z. B. EUR).                          |
| category  | string   | Die Kategorie der Ausgabe (z. B. "Lebensmittel", "Transport").|
| user      | int      | Die ID des Benutzers, der die Ausgabe tätigt.                 |

### Argumente

| *Argument* | *Typ*  | *Beschreibung*                                |
|--------------|----------|------------------------------------------------|
| value      | int      | Der Betrag der Ausgabe.                        |
| currency   | string   | Die Währung der Ausgabe.                       |
| category   | string   | Die Kategorie der Ausgabe.                     |
| user       | int      | Die ID des Benutzers.                          |

*Was es tut*:  
Um eine neue Ausgabe hinzuzufügen, wird eine Funktion implementiert, die die eingegebenen Daten verarbeitet und speichert. Zunächst wird der Betrag und die Währung erfasst, zum Beispiel 100 EUR. Dann wird eine eindeutige ID generiert, um die Ausgabe eindeutig zu identifizieren. Zusätzlich werden Informationen wie die Kategorie der Ausgabe erfasst.

Da die aktuelle Währung von der Webseite alle 60 Minuten abgefragt wird, stellt dies eine Mutation der Daten dar, da sich der Wechselkurs ändern kann und somit auch der Wert der Ausgabe in einer anderen Währung.

---

## 2. List Expenses (Query)

| *Feld*    | *Typ*  | *Beschreibung*                                             |
|-------------|----------|---------------------------------------------------------------|
| id        | long     | Eine eindeutige ID der Ausgabe.                               |
| value     | int      | Der Betrag der Ausgabe.                                       |
| currency  | string   | Die Währung, in der die Ausgabe angegeben ist.                |
| category  | string   | Die Kategorie der Ausgabe.                                    |
| user      | int      | Die ID des Benutzers, der die Ausgabe getätigt hat.           |

### Argumente

| *Argument* | *Typ*  | *Beschreibung*                                             |
|--------------|----------|---------------------------------------------------------------|
| user       | int      | Die ID des Benutzers, dessen Ausgaben angezeigt werden sollen.|

*Was es tut*:  
Mit dieser Funktion können alle Ausgaben eines bestimmten Benutzers abgerufen werden. Der Benutzer erhält eine Liste der Ausgaben, die er getätigt hat, basierend auf seiner Benutzer-ID. Diese Funktion ist eine *Abfrage (Query)*, da sie lediglich die Daten abruft und keine Änderungen an der Datenbank vornimmt. Ein Benutzer kann seine Ausgaben einsehen, indem er seine Benutzer-ID an die Funktion übergibt.

---

## 3. Delete Expense (Mutation)

| *Feld*    | *Typ*  | *Beschreibung*                                             |
|-------------|----------|---------------------------------------------------------------|
| id        | long     | Eine eindeutige ID der Ausgabe.                               |
| value     | int      | Der Betrag der Ausgabe.                                       |
| currency  | string   | Die Währung der Ausgabe.                                      |
| category  | string   | Die Kategorie der Ausgabe.                                    |
| user      | int      | Die ID des Benutzers, der die Ausgabe getätigt hat.           |

### Argumente

| *Argument* | *Typ*  | *Beschreibung*                                             |
|--------------|----------|---------------------------------------------------------------|
| IDs        | JSON     | Eine Liste von IDs, die gelöscht werden sollen (im JSON-Format).|

*Was es tut*:  
Mit dieser Funktion können Benutzer eine oder mehrere Ausgaben löschen. Die IDs der zu löschenden Ausgaben werden in einer Liste übergeben. So kann der Benutzer gezielt Ausgaben entfernen, indem er die entsprechenden IDs angibt. Diese Funktion ist eine *Mutation*, da sie Änderungen an der Datenbank vornimmt, indem sie die angegebenen Ausgaben löscht.

---

## 4. List Expenses Per Category (Query)

| *Feld*    | *Typ*  | *Beschreibung*                                             |
|-------------|----------|---------------------------------------------------------------|
| category  | string   | Die Kategorie der Ausgabe (z. B. "Lebensmittel", "Transport").|
| currency  | string   | Die Währung, in der die Ausgaben angegeben sind.              |
| totalValue| int      | Der Gesamtbetrag der Ausgaben in dieser Kategorie.            |

### Argumente

| *Argument* | *Typ*  | *Beschreibung*                                             |
|--------------|----------|---------------------------------------------------------------|
| user       | long     | Die ID des Benutzers, dessen Ausgaben nach Kategorien gruppiert angezeigt werden sollen.|

*Was es tut*:  
Diese Funktion bietet eine Zusammenfassung der Ausgaben eines Nutzers, aufgeteilt nach Kategorien. Für jede Kategorie, wie zum Beispiel “Lebensmittel” oder “Transport”, wird der gesamte Ausgabenbetrag angezeigt. Dadurch kann der Nutzer seine Ausgaben in den verschiedenen Kategorien besser überblicken. Da diese Funktion die Daten nur abruft, handelt es sich um eine Abfrage (Query).

---

## 5. List Expenses From Categories (Query mit HTTP-POST)

| *Feld*    | *Typ*  | *Beschreibung*                                             |
|-------------|----------|---------------------------------------------------------------|
| id        | long     | Eine eindeutige ID der Ausgabe.                               |
| value     | int      | Der Betrag der Ausgabe.                                       |
| currency  | string   | Die Währung, in der die Ausgabe angegeben ist.                |
| category  | string   | Die Kategorie der Ausgabe.                                    |
| user      | int      | Die ID des Benutzers, der die Ausgabe getätigt hat.           |

### Argumente

| *Argument*     | *Typ*  | *Beschreibung*                                             |
|------------------|----------|---------------------------------------------------------------|
| Category list  | JSON     | Eine Liste von Kategorien im JSON-Format, für die Ausgaben abgefragt werden sollen.|
| user           | int      | Die ID des Benutzers, dessen Ausgaben angezeigt werden sollen.|

*Was es tut*:  
Mit dieser Funktion hat der Benutzer die Möglichkeit, eine Liste von Kategorien anzugeben, wie beispielsweise “Lebensmittel” oder “Transport”. Der Microservice liefert daraufhin alle Ausgaben, die in diese Kategorien fallen. Da eine Liste von Kategorien als Eingabeparameter übergeben wird, erfolgt die Anfrage über eine HTTP-POST-Anfrage. Diese Funktion erlaubt es, Ausgaben detailliert und gleichzeitig nach mehreren Kategorien abzufragen.




# GRAPHQL commands example
    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  totalExpensesByCategory(user: 1) {\n    category\n    currency\n    totalValue\n  }\n}","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expenseById(id: \"2\") {\n    category\n    currency\n    id\n    user\n        value\n  }\n}\n\n","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expensesFromCategories(categories: \"FOOD\", user: 1) {\n    category\n    c    urrency\n    id\n    user\n    value\n  }\n}","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  __typename\n}\n\nmutation MyMutation {\n  addExpense(currency: \"EUR\", user    : 1, value: 100, category: \"FOOD\") {\n    category\n    currency\n    id\n    user\n    value\n  }\n}","operationName":"MyMutation"}'
