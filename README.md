# springboot-jwt-mysql

[![Java CI with Maven](https://github.com/hendisantika/springboot-jwt-mysql/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/springboot-jwt-mysql/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-blue?logo=dependabot)

This is a simple demo of creating REST APIs using Spring Boot and MySQL database.

## Features

- Spring Boot 4.1.2
- Java 25
- Spring Data JPA
- MySQL 9.5.0 with Docker Compose
- Automatic Docker Compose lifecycle management
- RESTful API

## Prerequisites

- Java 25 (JDK 25)
- Docker Desktop or Docker Engine
- Maven 3.9.x (or use the bundled Maven Wrapper `./mvnw`)

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
./mvnw spring-boot:run
```

The application will start on port 8090 and automatically manage the Docker Compose services.

### Option 2: Using the JAR file

```bash
./mvnw clean package
java -jar target/springboot-jwt-mysql-0.0.1-SNAPSHOT.jar
```

### Option 3: Manual Docker Compose (without Spring Boot management)

If you prefer to manage Docker Compose manually:

```bash
# Start MySQL container
docker-compose up -d

# Run the application
./mvnw spring-boot:run

# Stop MySQL container when done
docker-compose down
```

## Accessing the Application

Once started, the application will be available at:

```
http://localhost:8090
```

## API Endpoints

The application provides the following REST API endpoints:

### Get All Users

```bash
curl http://localhost:8090/users
```

**Response:**

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "salary": 50000
  }
]
```

### Create a New User

```bash
curl -X POST http://localhost:8090/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "salary": 50000
  }'
```

**Response:**

```json
{
  "id": 1,
  "name": "John Doe",
  "salary": 50000
}
```

### Example Workflow

```bash
# 1. Create first user
curl -X POST http://localhost:8090/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice Smith", "salary": 60000}'

# 2. Create second user
curl -X POST http://localhost:8090/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Bob Johnson", "salary": 55000}'

# 3. Get all users
curl http://localhost:8090/users
```

## Technology Stack

- **Java 25** - Language & runtime
- **Spring Boot 4.1.2** - Main framework
- **Spring Data JPA** - Database access layer
- **Hibernate 7** - ORM
- **MySQL Connector/J** - MySQL JDBC driver
- **Spring Boot Docker Compose** - Automatic container lifecycle management
- **Lombok** - Reduce boilerplate code

## Continuous Integration

GitHub Actions workflow [`.github/workflows/maven.yml`](.github/workflows/maven.yml) runs on every push and pull request to `master`:

- Sets up **Temurin JDK 25** with Maven dependency caching
- Starts a **MySQL 9.5.0 service container** so the `contextLoads` integration test can connect to the database
- Builds and tests the project with `./mvnw -B verify`
- On push to `master`, submits the Maven dependency graph to GitHub to improve Dependabot alerts

Run the same build locally (MySQL must be running, e.g. `docker compose up -d`):

```bash
./mvnw -B verify
```

## Dependency Updates (Dependabot)

Dependabot is configured in [`.github/dependabot.yml`](.github/dependabot.yml):

| Ecosystem        | Schedule (Asia/Jakarta) | Notes                                                                 |
|------------------|-------------------------|-----------------------------------------------------------------------|
| `maven`          | Daily, 21:00            | Spring Boot updates grouped; other minor/patch updates grouped        |
| `github-actions` | Weekly (Monday), 21:00  | All action updates grouped into a single PR                           |
| `docker-compose` | Weekly (Monday), 21:00  | Keeps the MySQL image in `docker-compose.yml` up to date              |

Dependabot PRs are labeled (`dependencies`, `java`, `github-actions`, `docker`) and use conventional commit prefixes
(`chore(deps)`, `chore(ci)`, `chore(docker)`).

## Additional Notes

- The MySQL data is persisted in the local `./mysql-data` directory (bind mount)
- The application automatically creates the database schema on startup using Hibernate's `ddl-auto=update`
- SQL queries are logged to the console for debugging purposes
- Lombok is configured as an explicit annotation processor in `pom.xml`, which is required on JDK 23+
