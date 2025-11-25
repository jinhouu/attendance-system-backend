# Gemini Project: Jiu-Jitsu Management

## Project Overview

This is a Jiu-Jitsu management system designed to track members and their attendance. It's a Spring Boot application written in Kotlin, following a modular architecture. The project is divided into three main modules:

*   `core:core-api`: This module contains the main application logic, including the REST API controllers and services.
*   `core:core-enum`: This module defines the `BeltGrade` and `BeltRank` value objects.
*   `storage`: This module handles data persistence, including the database entities and repositories.

The application uses Spring Data JPA for database interaction and an H2 in-memory database for local development.

## Building and Running

### Build

To build the project, run the following command from the root directory:

```bash
./gradlew build
```

### Run

To run the application, use the following command:

```bash
./gradlew :core:core-api:bootRun
```

The application will be accessible at `http://localhost:8080`.

### Test

The project includes several test tasks defined in the root `build.gradle.kts` file:

*   `./gradlew test`: Runs all tests.
*   `./gradlew unitTest`: Runs unit tests.
*   `./gradlew contextTest`: Runs context tests.
*   `./gradlew developTest`: Runs development tests.

## Development Conventions

*   **Kotlin:** The project is written in Kotlin, and it follows standard Kotlin coding conventions.
*   **Spring Boot:** The application is built on top of the Spring Boot framework.
*   **REST API:** The application exposes a REST API for managing members and attendance.
*   **Testing:** The project has a suite of unit and integration tests. It's recommended to write tests for any new features or bug fixes.
