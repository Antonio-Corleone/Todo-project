# 📝 Modern Todo List API - Spring Boot 3.4 & JWT

A complete Todo List Backend project, built to practice advanced backend programming techniques, JWT security, and modern CI/CD deployment processes.

---

## 🚀 Key Features
- **System security:** Authentication and authorization based on JWT (JSON Web Token).
- **Advanced queries:** Supports Pagination, Sorting, and Dynamic Filtering (JPA Specification).
- **Standard architecture:** Implements Layered Architecture (Controller - Service - Repository) and uses DTO (Java Records).
- **Centralized error handling:** System error management via `@RestControllerAdvice`.
- **Automation (DevOps):** Configured for Docker Multi-stage build and GitHub Actions CI/CD.

---

## 🛠️ Technologies Used
- **Language:** Java 21
- **Framework:** Spring Boot 3.4 (Spring Security, Spring Data JPA, Validation)
- **Database:** H2 Database (In-memory) / Ready for PostgreSQL
- **API Documentation:** Swagger UI (OpenAPI 3)
- **DevOps:** Docker, Docker Compose, GitHub Actions
- **Cloud Hosting:** Render

---

## 📦 Project Structure
```
src/main/java/antonio/todo/
├── config/              # Security, Swagger, Bean configuration
├── controller/          # REST Endpoints
├── exception/           # Centralized error handling (Custom Exceptions, Global Handler)
├── model/
│   ├── dto/             # Data Transfer Objects (Records)
│   └── entity/          # JPA Entities
├── repository/
│   └── specification/   # JPA Dynamic Queries (Specifications)
├── service/             # Business logic
├── utils/               # Utilities (JwtUtils)
└── TodoApplication.java
```

---

## ⚙️ Installation & Running

### 1. Run Locally
**Requirements:** JDK 21+ and Maven.

```bash
# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

### 2. Run with Docker
**Requirements:** Docker Desktop is running.

```bash
# Package and run the container
docker-compose up --build -d
```

---

## 📖 API Usage Instructions
- **Swagger UI:** Go to http://localhost:8080/swagger-ui/index.html while the app is running.
- **Login:** Use API endpoint `/api/auth/login` to get your accessToken.
- **Default credentials (Init Data):** `admin` / `password123`
- **Authentication:** Click the Authorize button in Swagger and paste your token in the format: `Bearer <your_token>`.
- **Usage:** Call any Todo API endpoint with full filter and pagination features.

---

## ⛓️ CI/CD Workflow
The project integrates GitHub Actions for automatic error checking and image building every time changes are pushed to the main branch.

- **CI:** GitHub Actions automatically build Maven project and check Dockerfile.
- **CD:** Automatically deploy updated version to Render via Docker.

---

## 👤 Author
- Antonio Corleone - Backend Developer
- GitHub: [https://github.com/Antonio-Corleone](https://github.com/Antonio-Corleone)

