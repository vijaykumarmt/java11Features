# Java 11 Features Application

A Spring Boot standalone application that demonstrates Java 11 features and can dynamically compile and run Java files using Maven build.

## Features

- **Spring Boot Application**: Standalone application with embedded Tomcat server
- **Dynamic Java Compilation**: Compile and run Java files at runtime using JavaCompiler API
- **Java 11 Features Demo**: Automatic demonstration of Java 11 features on startup including:
  - `var` keyword in lambda parameters
  - New String methods (`isBlank()`, `lines()`, `strip()`, `repeat()`)
  - File methods (`Files.readString()`, `Files.writeString()`)
  - Collection.toArray(IntFunction) enhancement
  - New HttpClient API
- **REST API**: Endpoints to compile and run external Java files via HTTP

## Prerequisites

- JDK 11 or higher (JDK is required for dynamic compilation)
- Maven 3.6 or higher

## Quick Start

### 1. Clone the repository
```bash
git clone https://github.com/vijaykumarmt/java11Features.git
cd java11Features
```

### 2. Build the application
```bash
mvn clean package
```

### 3. Run the application
```bash
mvn spring-boot:run
```

Or run the JAR file directly:
```bash
java -jar target/java11-features-app-1.0.0.jar
```

The application will start and automatically run the Java 11 features demo on startup, then listen on port 8080.

## Project Structure

```
java11Features/
├── pom.xml                                    # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/java11features/app/
│   │   │   ├── Java11FeaturesApplication.java    # Main Spring Boot application
│   │   │   ├── StartupRunner.java                # Runs demo on startup
│   │   │   ├── controller/
│   │   │   │   └── JavaRunnerController.java     # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── JavaRunnerService.java        # Java compilation/execution service
│   │   │   └── examples/
│   │   │       ├── Java11Features.java           # Java 11 features demo
│   │   │       └── HelloWorld.java               # Simple example
│   │   └── resources/
│   │       └── application.properties             # Application configuration
│   └── test/
│       └── java/com/java11features/app/
│           └── Java11FeaturesApplicationTests.java
└── README.md
```

## Usage

### On Startup

When you run the application, it automatically executes the Java 11 features demo and displays:
- Variable usage in lambda parameters
- String manipulation methods
- File I/O operations
- Collection conversions
- HTTP client demonstration

### REST API Endpoints

#### Health Check
Check if the service is running:
```bash
curl http://localhost:8080/api/java/health
```

**Response:**
```json
{
  "status": "UP",
  "service": "Java Runner Service"
}
```

#### Compile and Run Java File
Compile and execute an external Java file:
```bash
curl -X POST http://localhost:8080/api/java/run \
  -H "Content-Type: application/json" \
  -d '{
    "sourceFilePath": "/path/to/YourJavaFile.java",
    "className": "YourClassName"
  }'
```

**Request Parameters:**
- `sourceFilePath`: Absolute path to the Java source file
- `className`: Fully qualified class name (e.g., `com.example.MyClass` or just `MyClass` for default package)

**Response:**
```json
{
  "status": "success",
  "output": "Program output here..."
}
```

### Example: Creating and Running a Java File

1. Create a simple Java file:
```bash
cat > /tmp/HelloJava11.java << 'EOF'
public class HelloJava11 {
    public static void main(String[] args) {
        var greeting = "Hello from Java 11!";
        System.out.println(greeting);
        System.out.println("Line count: " + greeting.lines().count());
    }
}
EOF
```

2. Run it via the REST API:
```bash
curl -X POST http://localhost:8080/api/java/run \
  -H "Content-Type: application/json" \
  -d '{
    "sourceFilePath": "/tmp/HelloJava11.java",
    "className": "HelloJava11"
  }'
```

## Java 11 Features Demonstrated

### 1. Local Variable Type Inference in Lambda
```java
List<String> names = List.of("Alice", "Bob");
names.forEach((var name) -> System.out.println(name));
```

### 2. New String Methods
```java
// Check if string contains only whitespace
"   ".isBlank();  // true

// Split string into lines
"Line1\nLine2".lines().forEach(System.out::println);

// Strip leading/trailing whitespace (Unicode-aware)
"  text  ".strip();

// Repeat string
"Java".repeat(3);  // "JavaJavaJava"
```

### 3. File Methods
```java
// Write string to file
Files.writeString(path, "content");

// Read string from file
String content = Files.readString(path);
```

### 4. Collection to Array
```java
List<String> list = List.of("A", "B", "C");
String[] array = list.toArray(String[]::new);
```

### 5. HTTP Client API
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .GET()
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
```

## Maven Commands

```bash
# Clean and compile
mvn clean compile

# Build executable JAR
mvn clean package

# Run tests
mvn test

# Run the application
mvn spring-boot:run

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Configuration

The application can be configured via `src/main/resources/application.properties`:

```properties
# Server port (default: 8080)
server.port=8080

# Application name
spring.application.name=Java11FeaturesApp

# Logging
logging.level.root=INFO
logging.level.com.java11features=DEBUG
```

## Troubleshooting

### "Java compiler not available" error
Make sure you're running with JDK (not JRE). The JavaCompiler API is only available in JDK:
```bash
java -version  # Should show "openjdk" or similar JDK distribution
```

### Port 8080 already in use
Change the port in `application.properties`:
```properties
server.port=8081
```

Or set via environment variable:
```bash
SERVER_PORT=8081 mvn spring-boot:run
```

## License

This project is for learning and demonstration purposes.

## Author

Created to demonstrate Java 11 features and Spring Boot integration.
