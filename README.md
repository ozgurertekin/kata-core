# Kata Core Backend

This is  a Spring Boot backend project for products, offers, and shopping cart total calculation.
The implementation is for a code challenge and many decisions are taken based on some requirements and assumptions.
The production ready implementation would be more different which would be enriched by checks, controls, exception handling etc.
But at least this example shows the coding style and how any problem is approached. 
Spring Boot backend project for products, offers, and shopping cart total calculation.
- Money values are handled with `BigDecimal`.
- Offer calculations support percentage and exact-price discount modes.
- All prices assumed as the same currency.

## Tech Stack

- Java 21
- Spring Boot 3.2.x
- Spring Data JPA
- H2 in-memory database
- Maven

## Run Locally

```bash
mvn spring-boot:run
```

Swagger UI default URL:

- `http://localhost:8080/swagger-ui/index.html`

H2 console:

- `http://localhost:8080/h2-console`

## Build and Test

Compile:

```bash
mvn clean compile
```

Run all tests:

```bash
mvn test
```

Run a single test class:

```bash
mvn -Dtest=ProductRepositoryTest test
```

## Seed Data

Default seed data is in:

- `src/main/resources/data.sql`

Test seed data is in:

- `src/test/resources/data.sql`

## API Endpoints

Get all products:

- `GET /v1/products`

Calculate cart total:

- `POST /v1/cart/calculate`

Example request body:

```json
[
  {
    "productDTO": {
      "id": 1,
      "name": "Laptop Stand",
      "priceAmount": 29.90
    },
    "purchasedAmount": 2
  }
]
```