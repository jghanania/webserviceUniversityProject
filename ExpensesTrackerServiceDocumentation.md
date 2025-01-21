# Documentation: Expenses Tracker Service
## API Type: GraphQL
### Planned functions
#### Add expense
* Type: Mutation
* http request: Post
* Fields:
  * 
* Arguments:
  * Value (float)
  * currency (string)
  * Category (string)

#### List Expenses
* Type: Query
* http request: Get
* Fields:
  * 
* Arguments: None

#### Delete Expense
* Type: Mutation
* http request: Post
* Fields:
  * 
* Arguments:
  * IDs (json list of ints)

#### List Expenses Per Category
* Type: Query
* http request: Get
* Fields:
  * 
* Arguments: None

#### List Expenses From Category
* Type: Query
* http request: Post
* Fields:
  * 
* Arguments:
  * Category list (json list)




# GRAPHQL commands example
curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  totalExpensesByCategory(user: 1) {\n    category\n    currency\n    totalValue\n  }\n}","operationName":"MyQuery"}'


curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  ExpenseById(id: \"2\") {\n    category\n    currency\n    id\n    user\n    value\n  }\n}\n\n","operationName":"MyQuery"}'


curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  expensesFromCategories(categories: \"FOOD\", user: 1) {\n    category\n    currency\n    id\n    user\n    value\n  }\n}","operationName":"MyQuery"}'


curl -X POST http://localhost:8081/graphql -H "Content-Type: application/json" -d '{"query":"query MyQuery {\n  __typename\n}\n\nmutation MyMutation {\n  addExpense(currency: \"EUR\", user: 1, value: 100, category: \"FOOD\") {\n    category\n    currency\n    id\n    user\n    value\n  }\n}","operationName":"MyMutation"}'



# API Gateway Command example

curl -X GET http://localhost:8080/api/user/1/expenses/3