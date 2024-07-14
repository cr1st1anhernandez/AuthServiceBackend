# Authentication Service

This Authentication Service is a Spring Boot application designed to handle user authentication and management. It provides functionalities for user registration, login, and user management, including the ability to block and unblock users by name, and delete users, even allowing users to delete themselves.

## Features

- User Registration: Allows new users to register by providing their name, email, and password.
- User Login: Handles user login and issues JWT tokens for session management.
- User Management: Supports operations such as blocking/unblocking users by name and deleting users.

## Getting Started

### Prerequisites

- Java 11 or later
- Gradle 6.3 or later (for building the project)
- MySQL (or any other relational database compatible with Spring Data JPA)

### Setting Up the Database

1. Create a new database for the application.
2. Update `src/main/resources/application.properties` with your database connection details.

### Running the Application

1. Clone the repository to your local machine.
2. Navigate to the project directory and run the following command to build the project:

```bash
gradle build
```

3. To start the application, run:

```bash
java -jar build/libs/auth_service-0.0.1-SNAPSHOT.jar
```

The application will start and be accessible at `http://localhost:8080`.

## API Endpoints

### User Registration

- **POST** `/auth/register` - Registers a new user.

### User Login

- **POST** `/auth/login` - Authenticates a user and returns a JWT token.

### Delete User

- **DELETE** `/users/{userId}` - Deletes a user by their ID.

### Block User

- **POST** `/users/block/{userName}` - Blocks a user by their name.

### Unblock User

- **POST** `/users/unblock/{userName}` - Unblocks a user by their name.

## Security

This application uses Spring Security for authentication and authorization, ensuring that only authenticated users can access certain endpoints.

## Contributing

Contributions are welcome! Please feel free to submit a pull request.
