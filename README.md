# Web application of an animal shelter
This is an initial version of an application that supports animal shelter management.

## Basic requirements
The application is built with use of Spring Boot framework.<br>
Data is stored in a PostgreSQL database. <br>
The project allows for adding new animals, modifying and removing existing ones from the database and viewing a list of animals.

### Architecture

The application follows a layered Controller/Service/Repository architecture where controllers handle HTTP requests, service is responsible for business logic and the repository manages connection with the database 

### DTO and data validation

There is a seperate class (AnimalResponse) responsible for delivering only the necessary data to the user. Validation (in AnimalRequest class) ensures data meets defined rules before being processed by the application. 

### Excption handling

The GlobalExceptionHandler collects all validation errors and formats them into readable responses.

### JWT Security

JWT Security ensures that only authenticated users can access API. It also forbids a regular user from creating, updating or deleting an animal. These actions are reserved for admin. 

### Unit tests

Unit tests verify the basic functionality and return values of the service methods.

## Execution

Prerequisite: Installed docker desktop app, IntelliJ IDEA and Postman.

1. Create a new folder.
2. Open it in terminal.
3. insert a command: `git clone https://github.com/payonk123/ZTPAI-project.git . `
4. Then insert a command: `docker compose up -d`
5. Open project in IntelliJ IDEA and run 'ProjektZtpaiApplictaion'
6. Test functionalities via Postman. 

###### TEST EXAMPLES:

`POST http://localhost:8080/api/auth/register
Registration, admin: {"username": "admin", "password": "adminpass", "email": "admin@example.com", "role": "ADMIN"}`

`POST http://localhost:8080/api/auth/login
Login, admin: {"username": "admin", "password": "adminpass"}`


`POST http://localhost:8080/api/auth/register
Registration, user: {"username": "user2", "password": "userpass2", "email": "user2@example.com", "role": "USER"}`

`POST http://localhost:8080/api/auth/login
Login, user: {"username": "user2", "password": "userpass2"}`


`[ADMIN] POST http://localhost:8080/api/animals
{"name": "Luna", "age": 1}`

`[ADMIN] PUT http://localhost:8080/api/animals/{id}
{"name": "Luna", "age": 2}`

`[ADMIN] DELETE http://localhost:8080/api/animals/{id}`

`GET http://localhost:8080/api/animals/{id}`

`GET http://localhost:8080/api/animals`

VALIDATION ERRORS:

`POST http://localhost:8080/api/animals
{"name": "LunaLunaLunaLunaLunaLunaLunaLunaLuna", "age": 101}`

`POST http://localhost:8080/api/animals/{id}
{"name": null, "age": -1}`

`POST http://localhost:8080/api/animals/{id}
{"name": "L", "age": 1}`

