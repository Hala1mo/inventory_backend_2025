<div align="center">

# 📦 Inventory Management System 2025

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Swagger](https://img.shields.io/badge/Swagger-3.0-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)
[![Gradle](https://img.shields.io/badge/Gradle-8.5-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)

<p align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png" width="100%" />
</p>

</div>

<div align="center">
<p>A robust and modern inventory management system built with Spring Boot that allows businesses to track product movements across multiple locations, manage warehouse inventory, and generate comprehensive reports.</p>
</div>

## 📋 Project Overview

This inventory management system addresses real-world inventory tracking challenges with a scalable RESTful API architecture. The system tracks products, locations, and inventory movements with features including:

Product lifecycle management
Multiple warehouse/location tracking
Inventory movement operations (transfers, check-ins, check-outs)
Real-time inventory balance reporting
Dashboard analytics


## 🏗️ Architecture

The application follows a clean architecture pattern with:

- *Controller Layer:* REST endpoints for client interaction
- *Service Layer:* Business logic implementation
- *Repository Layer:* Data access and persistence
- *Model Layer:* Domain entities
- *DTO Layer:* Data transfer objects for API interactions
- *Mapper Layer:* Conversion between entities and DTOs

## 🔧 Tech Stack

- *Backend Framework:* Spring Boot 3.4.5
- *API Documentation:* OpenAPI (Swagger)
- *Database:* MySQL
- *Build Tool:* Gradle
- *Java Version:* 17

## 📊 Database Schema

### Entity Relationship

## Entity Relationship Diagram

## Entity Relationship Diagram

```mermaid
erDiagram
    Location ||--o{ ProductMovement : "from/to"
    Product ||--o{ ProductMovement : "has"

    Location {
        int id PK
        string name
        string country
        string city
        string address
        date createdAt
    }

    ProductMovement {
        int id PK
        string movementType
        int quantity
        string notes
        date createdAt
        int product_id FK
        int from_location_id FK
        int to_location_id FK
    }

    Product {
        int id PK
        string name
        string code
        string category
        decimal price
        string description
        string imageUrl
        string status
        date createdAt
    }

### Key Features

- *Products:* Manage products with categories, status tracking, and pricing
- *Locations:* Track warehouses and locations globally
- *Movement Types:* Support for IN, OUT, and TRANSFER operations
- *Validation:* Business rule enforcement for inventory movements
- *Audit Trail:* All movements timestamped and logged

## 🚀 API Endpoints

### Products API

| Method | Endpoint                            | Description                           |
|--------|-------------------------------------|---------------------------------------|
| GET    | /api/products                     | Retrieve all products                 |
| GET    | /api/products/{id}                | Get product by ID                     |
| POST   | /api/products                     | Create a new product                  |
| PUT    | /api/products/{id}                | Update a product                      |
| DELETE | /api/products/{id}                | Delete a product                      |
| GET    | /api/products/product-balances/{id} | Get balances for a specific product   |

### Locations API

| Method | Endpoint                            | Description                           |
|--------|-------------------------------------|---------------------------------------|
| GET    | /api/locations                    | Retrieve all locations                |
| GET    | /api/locations/{id}               | Get location by ID                    |
| POST   | /api/locations                    | Create a new location                 |
| PUT    | /api/locations/{id}               | Update a location                     |
| DELETE | /api/locations/{id}               | Delete a location                     |
| GET    | /api/locations/{id}/inventory     | Get inventory for a specific location |

### Inventory Movements API

| Method | Endpoint                            | Description                           |
|--------|-------------------------------------|---------------------------------------|
| GET    | /api/productMovement              | Retrieve all movements                |
| GET    | /api/productMovement/{id}         | Get movement by ID                    |
| POST   | /api/productMovement              | Create a new movement                 |
| PUT    | /api/productMovement/{id}         | Update a movement                     |
| DELETE | /api/productMovement/{id}         | Delete a movement                     |

### Reports API

| Method | Endpoint                               | Description                           |
|--------|----------------------------------------|---------------------------------------|
| GET    | /api/reports/product-balances        | Get all product balances by location  |
| GET    | /api/reports/productDistribution     | Get product category distribution     |
| GET    | /api/reports/product-counts-per-location | Get product counts by location   |
| GET    | /api/reports/dashboard-stats         | Get dashboard statistics              |

## 🔍 Key Business Rules

Products cannot be deleted if they have movement history
Locations cannot be deleted if they have movement history
Inventory transfers require sufficient stock at the source location
Products cannot be transferred to/from the same location
Movement quantities must be positive values
Unique constraints on product codes and location names


## 📈 Reports and Analytics

The system provides several analytics features:

1. *Dashboard Statistics:*
   - Total locations count
   - Movement counts by type (IN, OUT, TRANSFER)
   - Total inventory count

2. *Product Location Report:*
   - Current inventory balance for each product at each location

3. *Category Distribution:*
   - Product distribution across different categories

4. *Location-Based Analysis:*
   - Product counts per location for warehouse utilization analysis

## 🛠️ Setup & Installation

### Prerequisites

JDK 17 or higher
MySQL 8.0
Gradle


### Configuration

Clone the repository:

   
   git clone https://github.com/yourusername/inventory-management-2025.git
   cd inventory-management-2025
   

2. Configure MySQL database connection in application.properties:
   
properties
   spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   

Build the project:

   
   ./gradlew build
   

Run the application:

   
   ./gradlew bootRun
   

Access the API documentation:

   
   http://localhost:8080/swagger-ui.html
   



## 💡 Advanced Features

- *DTO Pattern:* Clean separation between API and domain models
- *Custom Exception Handling:* Global exception handler with appropriate HTTP responses
- *OpenAPI Documentation:* Comprehensive API documentation with Swagger
- *Validation:* Input validation with Jakarta Validation API
- *Mapper Layer:* Efficient object transformations
- *Enum Types:* Type-safe constants for status and categories

## 👨‍💻 Author


### Hala Abdel Halim

<p>Developed with 💻 and ☕ for <strong>ERPMax Solutions</strong><br>
<em>Full Stack Developer specializing in Spring Boot and Modern Web Technologies</em></p>

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png" width="100%" />
</div>
 
