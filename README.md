# springboot-jwt-mysql

This is a simple demo of creating REST APIs using Spring Boot and MySQL database.

## Features

- Spring Boot 3.5.7
- Spring Data JPA
- MySQL 9.5.0 with Docker Compose
- Automatic Docker Compose lifecycle management
- RESTful API

## Prerequisites

- Java 21 or higher
- Docker Desktop or Docker Engine
- Maven 3.6.3 or higher

## Database Configuration

This project uses Spring Boot Docker Compose support to automatically manage the MySQL database container.

### MySQL Credentials

- **Host**: localhost
- **Port**: 3306
- **Database**: jwt
- **Username**: yu71
- **Password**: 53cret

### Docker Compose

The `docker-compose.yml` file is automatically detected and managed by Spring Boot. When you start the application, it
will:

1. Pull the MySQL 9.5.0 image (if not already available)
2. Create and start the MySQL container
3. Wait for the database to be healthy
4. Connect the application to the database
5. Automatically stop the container when the application shuts down

## Running the Application

### Option 1: Using Maven

```bash
mvn spring-boot:run
```

The application will start on port 8090 and automatically manage the Docker Compose services.

### Option 2: Using the JAR file

```bash
mvn clean package
java -jar target/springboot-jwt-mysql-0.0.1-SNAPSHOT.jar
```

### Option 3: Manual Docker Compose (without Spring Boot management)

If you prefer to manage Docker Compose manually:

```bash
# Start MySQL container
docker-compose up -d

# Run the application
mvn spring-boot:run

# Stop MySQL container when done
docker-compose down
```

## Accessing the Application

Once started, the application will be available at:

```
http://localhost:8090
```

## Technology Stack

- **Spring Boot 3.5.7** - Main framework
- **Spring Data JPA** - Database access layer
- **Hibernate 6.6** - ORM
- **MySQL Connector/J** - MySQL JDBC driver
- **Spring Boot Docker Compose** - Automatic container lifecycle management
- **Lombok** - Reduce boilerplate code

## Additional Notes

- The MySQL data is persisted in a Docker volume named `mysql-data`
- The application automatically creates the database schema on startup using Hibernate's `ddl-auto=update`
- SQL queries are logged to the console for debugging purposes