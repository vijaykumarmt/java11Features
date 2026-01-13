# Java 11 Features Application

A Spring Boot standalone application that demonstrates Java 11 features and can dynamically compile and run Java files using Maven build.

## Features

- **Spring Boot Application**: Standalone application with embedded Tomcat
- **Dynamic Java Compilation**: Compile and run Java files at runtime
- **Java 11 Features Demo**: Examples of Java 11 features including:
  - var keyword in lambda parameters
  - New String methods (isBlank, lines, strip, repeat)
  - Files.readString() and Files.writeString()
  - Collection.toArray(IntFunction)
  - New HttpClient API
- **REST API**: Endpoints to compile and run Java files via HTTP

## Prerequisites

- JDK 11 or higher
- Maven 3.6 or higher

## Build

Build the application using Maven:

```bash
mvn clean install
```

## Run

Run the application:

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/java11-features-app-1.0.0.jar
```

## Usage

### On Startup

The application automatically runs the Java 11 features demo on startup, displaying examples of various Java 11 features.

### REST API

#### Health Check
```bash
curl http://localhost:8080/api/java/health
```

#### Compile and Run Java File
```bash
curl -X POST http://localhost:8080/api/java/run \
  -H "Content-Type: application/json" \
  -d '{
    "sourceFilePath": "/path/to/YourJavaFile.java",
    "className": "com.example.YourClassName"
  }'
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/java11features/app/
│   │       ├── Java11FeaturesApplication.java  # Main application
│   │       ├── StartupRunner.java               # Runs demo on startup
│   │       ├── controller/
│   │       │   └── JavaRunnerController.java    # REST endpoints
│   │       ├── service/
│   │       │   └── JavaRunnerService.java       # Compilation/execution service
│   │       └── examples/
│   │           ├── Java11Features.java          # Java 11 features demo
│   │           └── HelloWorld.java              # Simple example
│   └── resources/
│       └── application.properties               # Configuration
└── test/
    └── java/
        └── com/java11features/app/
            └── Java11FeaturesApplicationTests.java
```

## Java 11 Features Demonstrated

1. **Var in Lambda Parameters**: Use `var` keyword in lambda expressions
2. **String Methods**: `isBlank()`, `lines()`, `strip()`, `repeat()`
3. **File Methods**: `Files.readString()`, `Files.writeString()`
4. **Collection to Array**: Improved `toArray()` with method reference
5. **HttpClient API**: New standard HTTP client

## License

This project is for learning and demonstration purposes.
