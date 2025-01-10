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