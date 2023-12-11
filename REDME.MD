# Quote service application #

### Tech stack:
1) Java version: `11`
2) DBMS: Postgres. 
I got some probmles with h2, for example - unique constraints, so i decided to use postgres
3) Kafka: transport beetwen auth-service and core-service

### Auth-Service usecases
1)  sign-up users, after successful signing up, login of user is transfered to core-service via kafka
2)  authenticate users, response with jwt token

### Core-Service usecases
1)  create quote
2)  update quote content
3)  upvote or downvote qoute
4)  retrieve random quote
5)  delete quote

### To start appcliation use `docker-compose up` from root path
### Open api spec:
1) core-service: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
2) auth-service: http://localhost:9090/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

### Postman collection for testing api
`postman/QUOTES.postman_collection.json`

