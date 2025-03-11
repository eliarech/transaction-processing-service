# Transaction Processing Service

A Spring Boot microservice for processing financial transactions with MongoDB persistence.

## Overview

This service provides a RESTful API for creating, retrieving, updating, and deleting financial transactions. It uses MongoDB as the database backend and includes comprehensive transaction management capabilities including status tracking, filtering by account, and date range queries.

## Features

- Create and manage financial transactions
- Track transaction statuses (PENDING, COMPLETED, FAILED, CANCELLED)
- Query transactions by ID, reference, account, type, and date range
- Detect and prevent duplicate transactions
- Fully containerized with Docker and docker-compose
- API documentation with Swagger

## Technology Stack

- Java 11
- Spring Boot 2.3.1
- Spring Data MongoDB
- MongoDB 4.4
- Maven 3.8.1
- Docker & Docker Compose
- Lombok
- Swagger/Springfox

## Prerequisites

- JDK 11 or higher
- Maven 3.6+
- MongoDB (local installation or via Docker)
- Docker and Docker Compose (for containerized deployment)

## Getting Started

### Running Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/eliarech/transaction-processing-service.git
   cd transaction-processing-service
   ```

2. **Configure MongoDB**
   
   Ensure MongoDB is running locally on port 27017 or update the `application.properties` file with your MongoDB connection details.

3. **Build the application**
   ```bash
   mvn clean package
   ```

4. **Run the application**
   ```bash
   java -jar target/transaction-processing-service-1.0.0-SNAPSHOT.jar
   ```

### Using Docker Compose

1. **Build and start containers**
   ```bash
   docker-compose up -d
   ```

   This will start both the application and MongoDB containers.

2. **Stop containers**
   ```bash
   docker-compose down
   ```

## API Endpoints

Once the application is running, you can access:

- API Base URL: `http://localhost:8080/api/transactions`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

### Available Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transactions` | Create a new transaction |
| GET | `/api/transactions/{id}` | Get transaction by ID |
| GET | `/api/transactions/reference/{reference}` | Get transaction by reference |
| GET | `/api/transactions/account/{accountId}` | List transactions by account ID |
| GET | `/api/transactions/account/{accountId}/range` | List transactions by account ID and date range |
| GET | `/api/transactions/type/{type}` | List transactions by type |
| PATCH | `/api/transactions/{id}/status` | Update transaction status |
| DELETE | `/api/transactions/{id}` | Delete a transaction |

## Transaction Model

```json
{
  "reference": "TRX123456",
  "accountId": "ACC987654",
  "amount": 100.50,
  "currency": "USD",
  "type": "PAYMENT",
  "description": "Monthly subscription payment",
  "status": "PENDING"
}
```

## Project Structure

```
transaction-processing-service/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── eliarech/
│       │           └── transaction/
│       │               ├── config/
│       │               ├── controller/
│       │               ├── exception/
│       │               ├── model/
│       │               ├── repository/
│       │               ├── service/
│       │               └── TransactionServiceApplication.java
│       └── resources/
│           └── application.properties
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Performance Tuning

The service is configured with MongoDB connection pooling and timeout settings for optimal performance:

- Connections per host: 100
- Max wait time: 10 seconds
- Socket timeout: 10 seconds

These settings can be adjusted in the `application.properties` file.

## Development

### Building

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

## Troubleshooting

- **Connection issues with MongoDB**: Ensure MongoDB is running and accessible
- **Application fails to start**: Check the logs for specific error messages
- **API returns errors**: Use Swagger UI to ensure correct request format
