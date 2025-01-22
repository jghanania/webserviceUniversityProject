# API Gateway Command example

    curl -X GET http://localhost:8080/api/user/1/expenses/3

    curl -X GET http://localhost:8080/api/user/1/categories/sum

    curl -X GET http://localhost:8080/api/user/1/categories/FOOD

    curl -X POST http://localhost:8080/api/user/1/expenses -H "Content-Type: application/json" -d '{"value": 100 ,"category": "FOOD","currency": "EUR"}'