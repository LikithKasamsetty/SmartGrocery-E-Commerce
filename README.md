# SmartGrocery-E-Commerce

A grocery e-commerce backend built with Java 21 and Spring Boot.

## Tech stack
Java 21, Spring Boot 4.1.1, Spring Data JPA, Hibernate, PostgreSQL, Maven, Bean Validation

## Architecture
Controller → Service → Repository → PostgreSQL
(DTOs, @Transactional, global exception handling)

## Features
- CRUD APIs for products
- Search by name, price, quantity
- Request validation with clear error messages
- Consistent JSON error responses (400, 404, 500)

## API endpoints
| Method | URL | Description |
|---|---|---|
| POST | /products | Create a product (201) |
| POST | /products/bulk | Create many products |
| GET | /products | List all products |
| GET | /products/{id} | Get one product |
| PUT | /products/{id} | Update a product |
| DELETE | /products/{id} | Delete a product (204) |
| GET | /products/search/name?name= | Search by name |
| GET | /products/search/cheaper?price= | Cheaper than a price |
| GET | /products/search/high-price?price= | More expensive than a price |
| GET | /products/search/low-stock?quantity= | Low stock |

## How to run
1. Install Java 21 and PostgreSQL.
2. Create a database named `smartgrocery`.
3. Set the environment variable `DB_PASSWORD` to your PostgreSQL password.
4. Run `SmartGroceryApplication`.
5. The API starts on `http://localhost:8080`.

## Roadmap
Spring Security + JWT, Swagger, JUnit/Mockito tests, Docker, Spring AI.
