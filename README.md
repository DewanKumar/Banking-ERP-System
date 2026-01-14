# Banking ERP System

A full-stack Banking ERP application developed using **Java 17** and **Spring Boot**, focused on secure authentication, role-based access control, and core banking operations such as account and transaction management.  
This project is built following clean layered architecture and industry-standard backend practices.

---

## 🚀 Features

- JWT-based authentication and authorization
- Role-Based Access Control (Admin / User)
- Secure login and logout using Spring Security
- Account management (create, view, update)
- Transaction management
- RESTful APIs
- Global exception handling
- DTO-based request/response validation
- Server-side rendering using Thymeleaf

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate (JPA)
- MySQL

### Frontend
- Thymeleaf
- HTML5
- CSS3
- JavaScript

---

## 📂 Project Architecture

src/main/java
├── controller # REST & MVC controllers
├── service # Business logic
├── repository # JPA repositories
├── dto # Data Transfer Objects
├── entity # JPA entities
├── exception # Global exception handling
└── security # Spring Security & JWT configuration

---

## 🔐 Authentication & Security

- Implemented **JWT-based authentication**
- Password encryption using **BCrypt**
- Secured endpoints using **Spring Security**
- Role-based authorization for protected resources

---

## ⚙️ Setup & Installation

## ⚙️ Setup & Installation

### Prerequisites
- Java 17+
- Maven
- MySQL

---

### Database Setup

1. Open MySQL and create the database:
   ```sql
   CREATE DATABASE banking_erp;


### Steps

1. Clone the repository
   ```bash
   git clone https://github.com/dewankumar/banking-erp.git

2. Configure database in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_erp
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
3. Run the application
mvn spring-boot:run
4. Open in browser
http://localhost:8080

