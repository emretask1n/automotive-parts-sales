# ITG Hiring Challenge - Mini Automotive Parts Sales E-Commerce Project

This project is a submission for the ITG Hiring Challenge, focused on creating a mini automotive parts e-commerce application. The project includes the backend implementation, while the frontend part is not completed.

## Project Description

The Mini Automotive Parts Sales E-Commerce Project is a simplified e-commerce system for automotive parts. It provides functionality for user registration, authentication, product listing, shopping cart management, and order placement. The project is built using a layered architecture and follows n-tier principles.

### Backend Technologies

The backend of the project is developed using Java and the Spring Boot framework. The following technologies and libraries are utilized:

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Maven

### API Documentation

The project's backend includes a set of RESTful APIs, which are documented using the OpenAPI 3 specification. The API documentation can be accessed by running the application and visiting the following URL in a web browser:

http://localhost:8086/swagger-ui.html

The Swagger UI provides an interactive interface to explore and test the available API endpoints.

### Project Structure

The project follows a modular structure, with separate packages for different concerns. The main packages in the project are as follows:

The project follows a modular structure, with separate packages for different concerns. The main packages in the project are as follows:

- `com.emretaskin.itg.config` - Contains configuration classes for Spring Boot and other components.
- `com.emretaskin.itg.constants` - Defines constants used throughout the application.
- `com.emretaskin.itg.controller` - Contains the controllers for handling HTTP requests and defining the API endpoints.
- `com.emretaskin.itg.dto` - Contains Data Transfer Objects (DTOs) used for data exchange between layers.
- `com.emretaskin.itg.entity` - Defines the data models and entities used in the application.
- `com.emretaskin.itg.enums` - Defines enums used in the application.
- `com.emretaskin.itg.exception` - Handles custom exceptions and error responses.
- `com.emretaskin.itg.logger` - Provides logging functionality for the application.
- `com.emretaskin.itg.repository` - Provides the data access layer for interacting with the database.
- `com.emretaskin.itg.security` - Implements security-related configurations and authentication/authorization logic.
- `com.emretaskin.itg.service` - Implements the business logic and handles data operations.

## Running the Application

### Prerequisites

Make sure you have the following prerequisites installed on your system:

- Docker
- Docker Compose

### Steps

To run the application, follow these steps:

1. Clone the project repository to your local machine.

2. Open a terminal or command prompt and navigate to the project's root directory.

3. Build and start the Docker containers using Docker Compose:

   ```shell
   docker-compose up -d
   ```
The backend application will be accessible on `http://localhost:8087` from within the Docker container.

## Disclaimer

This submission only includes the backend implementation of the project. The frontend part is not completed. However, the backend provides a set of RESTful APIs that can be used for further development of the frontend or integration with other systems.

For any inquiries or support, please contact the project maintainer.
