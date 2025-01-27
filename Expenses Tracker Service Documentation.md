# Documentation: Expenses Tracker Service
## API Type: GraphQL
### Planned functions
#### Add expense
* Type: Mutation
* Fields:
  * id (long)
  * value (int)
  * currency (string)
  * category (string)
  * user (int)
* Arguments:
  * value (int)
  * currency (string)
  * category (string)
  * user (int)

#### List Expenses
* Type: Query
* Fields:
  * id (long)
  * value (int)
  * currency (string)
  * category (string)
  * user (int)
* Arguments:
  * user (int)

#### Delete Expense
* Type: Mutation
* Fields:
  * id (long)
  * value (int)
  * currency (string)
  * category (string)
  * user (int)
* Arguments:
  * IDs (json list of ints)

#### List Expenses Per Category
* Type: Query
* Fields:
  * category (string)
  * currency (string)
  * totalValue (int)
* Arguments: 
  * user (long)

#### List Expenses From Categories
* Type: Query
* http request: Post
* Fields:
  * id (long)
  * value (int)
  * currency (string)
  * category (string)
  * user (int)
* Arguments:
  * Category list (json list)
  * user (int)




# GRAPHQL commands example
    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  totalExpensesByCategory(user: 1) {\n    category\n    currency\n    totalValue\n  }\n}","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expenseById(id: \"2\") {\n    category\n    currency\n    id\n    user\n        value\n  }\n}\n\n","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expensesFromCategories(categories: \"FOOD\", user: 1) {\n    category\n    c    urrency\n    id\n    user\n    value\n  }\n}","operationName":"MyQuery"}'


    curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  __typename\n}\n\nmutation MyMutation {\n  addExpense(currency: \"EUR\", user    : 1, value: 100, category: \"FOOD\") {\n    category\n    currency\n    id\n    user\n    value\n  }\n}","operationName":"MyMutation"}'
