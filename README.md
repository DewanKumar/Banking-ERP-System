# 🏦 Banking ERP System (Spring Boot)

A secure **Banking ERP backend system** built with **Java Spring Boot**, featuring **JWT-based authentication**, **role-based authorization**, and **RESTful APIs** for managing accounts and transactions.

This project follows **industry best practices** including layered architecture, Spring Security, and clean error handling.

---

## 🚀 Features

### 🔐 Security
- JWT-based **stateless authentication**
- **Role-based access control** (`ADMIN`, `USER`)
- Secured APIs using **Spring Security**
- Password encryption with **BCrypt**

### 🏦 Account Management
- Create, update, delete bank accounts
- Fetch accounts by:
  - ID
  - Account number
  - Account type
- Search accounts by balance (greater, less, equal)
- Paginated and sortable results

### 💸 Transaction Management
- Credit account
- Debit account
- Balance validation and business rules enforced

### 🧱 Architecture & Quality
- Layered architecture (Controller, Service, Repository)
- Global exception handling
- DTO validation using `jakarta.validation`
- Centralized API response structure
- Logging using SLF4J

---

## 🛠️ Tech Stack

| Category | Technology |
|--------|------------|
| Language | Java 17+ |
| Framework | Spring Boot |
| Security | Spring Security, JWT |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Validation | Jakarta Validation |
| Build Tool | Maven |
| API Testing | Postman |
| Logging | SLF4J |

---

## 🔑 Roles & Permissions

| Role | Permissions |
|------|------------|
| ADMIN | Create / Update / Delete accounts, Credit / Debit, View all data |
| USER | View allowed account data |

Authorization is enforced using:
```java
@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
```
## 🔐 Authentication Flow (JWT)

1. User logs in using **username** and **password**.
2. Server validates the credentials using **Spring Security**.
3. On successful authentication, a **JWT token** is generated and returned.
4. Client includes the token in the HTTP request header:
5. A **JWT authentication filter** intercepts every request, validates the token, and sets the user’s authentication context.

Access to APIs is granted or denied based on the user’s **role and permissions**.

## 📌 API Endpoints

### 🔑 Authentication
| Method | Endpoint | Description |
|------|---------|-------------|
| POST | `/auth/login` | Authenticate user and generate JWT token |
| POST | `/auth/register` | Register new user |

---

### 🏦 Account Management
| Method | Endpoint | Role | Description |
|------|----------|------|-------------|
| POST | `/api/account/create` | ADMIN | Create a new bank account |
| GET | `/api/account/Accounts` | ADMIN | Fetch all accounts |
| GET | `/api/account/id/{id}` | ADMIN, USER | Get account by ID |
| POST | `/api/account/Credit` | ADMIN | Credit amount to account |
| POST | `/api/account/Debit` | ADMIN | Debit amount from account |
| DELETE | `/api/account/id/{id}` | ADMIN | Delete account by ID |

---

▶️ How to Run Locally
1️⃣ Clone Repository
git clone https://github.com/your-username/banking-erp-system.git
cd banking-erp-system

2️⃣ Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/banking_erp
spring.datasource.username=root
spring.datasource.password=your_password

3️⃣ Run Application
mvn spring-boot:run
Application runs at: http://localhost:8080

---

##📈 What This Project Demonstrates

- Real-world Spring Security implementation
- JWT authentication & authorization
- Enterprise-level REST API design
- Backend debugging and problem-solving
- Clean, maintainable codebase

##👤 Author

**Dewan Kumar**
**Java Backend Developer (Spring Boot)**
