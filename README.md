# Sol Lingo Backend — Authentication API

A RESTful authentication service built with Spring Boot, backing the [Sol Lingo](https://play.google.com) language-learning app. Handles user registration and login with hashed password storage, backed by PostgreSQL and containerized with Docker.

## Tech Stack

- **Java 21** + **Spring Boot 3.3.4**
- **Spring Security** (BCrypt password hashing)
- **Spring Data JPA** + **PostgreSQL**
- **Docker** / **Docker Compose**
- **Maven**

## Features

- User registration with email/password
- User login with credential verification
- Password hashing via BCrypt (never stored in plain text)
- Structured error responses (e.g. `EMAIL_ALREADY_EXISTS`, `INVALID_CREDENTIALS`)
- Containerized PostgreSQL database via Docker Compose

## API Endpoints

| Method | Endpoint | Description |
|--------|-----------|--------------|
| `POST` | `/api/register` | Register a new user |
| `POST` | `/api/login` | Log in an existing user |
| `GET` | `/api/users` | List all registered users |

### Register

```
POST /api/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "yourpassword"
}
```

**Response**

```json
{
  "success": true,
  "email": "user@example.com",
  "message": "Success",
  "errorCode": null,
  "token": "temp-token"
}
```

### Login

```
POST /api/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "yourpassword"
}
```

**Response (success)**

```json
{
  "success": true,
  "email": "user@example.com",
  "message": "Success",
  "errorCode": null,
  "token": "temp-token"
}
```

**Response (failure)**

```json
{
  "success": false,
  "email": "user@example.com",
  "message": "Invalid email or password",
  "errorCode": "INVALID_CREDENTIALS",
  "token": null
}
```

## Running Locally

### Prerequisites

- Java 21
- Maven
- Docker & Docker Compose

### Steps

1. **Clone the repo**

   ```bash
   git clone https://github.com/dev2imp/Spring-Boot-Autentication-API-.git
   cd Spring-Boot-Autentication-API-
   ```

2. **Start the PostgreSQL database**

   ```bash
   docker-compose up -d
   ```

   This spins up a Postgres container on port `5432` with a database named `auth_db`.

3. **Run the Spring Boot app**

   ```bash
   mvn spring-boot:run
   ```

   The API will be available at `http://localhost:8080`.

4. **Test with Postman or curl**

   ```bash
   curl -X POST http://localhost:8080/api/register \
     -H "Content-Type: application/json" \
     -d '{"email":"test@example.com","password":"test1234"}'
   ```

### Running fully in Docker

The project also includes a multi-stage `Dockerfile` that builds and runs the app in a container:

```bash
docker build -t sollingo-backend .
docker run -p 8080:8080 sollingo-backend
```

## Deployment

This service has been deployed to **Google Cloud** and tested end-to-end with Postman.

## Project Structure

```
src/main/java/org/sollingo/
├── controllers/     # REST endpoints
├── services/        # Business logic
├── repository/      # Data access (Spring Data JPA)
├── entity/          # Database entities
├── dto/             # Request/response objects
└── config/          # Security configuration
```

## Notes / Future Improvements

- Replace the placeholder `"temp-token"` with real JWT generation and validation
- Move database credentials out of `application.properties` and into environment variables / a `.env` file (not committed to version control)
- Add input validation (email format, password strength) at the request level
- Add unit and integration tests

## Author

**Osman Inci**
[GitHub](https://github.com/dev2imp) · [LinkedIn](https://linkedin.com/in/osman-inci-868435221)