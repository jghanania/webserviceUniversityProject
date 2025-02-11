# Documentation: Expenses Tracker Service
## API Type: GraphQL
### Planned functions
#### Add expense
####  1. Add Expense 
*	id (long): Eine eindeutige ID für die Ausgabe.
*	value (int): Der Betrag der Ausgabe (z. B. 100).
*	currency (string): Die Währung, in der der Betrag angegeben ist (z. B. EUR).
*	category (string): Die Kategorie der Ausgabe (z. B. "Lebensmittel", "Transport").
*	user (int): Die ID des Benutzers, der die Ausgabe getätigt hat.
•	Argumente:
*	value: Der Betrag der Ausgabe.
*	currency: Die Währung, in der die Ausgabe getätigt wurde.
*	category: Die Kategorie der Ausgabe.
*	user: Die ID des Benutzers.


 #### 2.List Expenses

*	id (long): Eine eindeutige ID der Ausgabe.
*	value (int): Der Betrag der Ausgabe.
*	currency (string): Die Währung, in der die Ausgabe angegeben ist.
*	category (string): Die Kategorie der Ausgabe.
*	user (int): Die ID des Benutzers.
•	Argumente:
*	user (int): Die ID des Benutzers, dessen Ausgaben angezeigt werden sollen.

#### 3. Delete Expense
*	id (long): Eine eindeutige ID der Ausgabe.
*	value (int): Der Betrag der Ausgabe.
*	currency (string): Die Währung, in der die Ausgabe angegeben ist.
*	category (string): Die Kategorie der Ausgabe.
*	user (int): Die ID des Benutzers.
•	Argumente:
*	IDs: Eine Liste von IDs, die gelöscht werden sollen (im JSON-Format).


#### List Expenses Per Category

	category (string): Die Kategorie der Ausgabe (z. B. "Lebensmittel", "Transport").
*	currency (string): Die Währung, in der die Ausgaben angegeben sind.
*	totalValue (int): Der Gesamtbetrag der Ausgaben in dieser Kategorie.
•	Argumente:
*	user (long): Die ID des Benutzers, dessen Ausgaben nach Kategorien gruppiert angezeigt werden sollen.


#### List Expenses From Categories

*	id (long): Eine eindeutige ID der Ausgabe.
*	value (int): Der Betrag der Ausgabe.
*	currency (string): Die Währung, in der die Ausgabe angegeben ist.
*	category (string): Die Kategorie der Ausgabe.
*	user (int): Die ID des Benutzers.
•	Argumente:
*	Category list: Eine Liste von Kategorien im JSON-Format, für die Ausgaben abgefragt werden sollen.
*	user: Die ID des Benutzers, dessen Ausgaben angezeigt werden sollen.





# GRAPHQL commands example
    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  totalExpensesByCategory(user: 1) {\n    category\n    currency\n    totalValue\n  }\n}","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expenseById(id: \"2\") {\n    category\n    currency\n    id\n    user\n        value\n  }\n}\n\n","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expensesFromCategories(categories: \"FOOD\", user: 1) {\n    category\n    c    urrency\n    id\n    user\n    value\n  }\n}","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  __typename\n}\n\nmutation MyMutation {\n  addExpense(currency: \"EUR\", user    : 1, value: 100, category: \"FOOD\") {\n    category\n    currency\n    id\n    user\n    value\n  }\n}","operationName":"MyMutation"}'
