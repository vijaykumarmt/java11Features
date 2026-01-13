package com.java11features.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner to demonstrate Java file execution on startup
 */
@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========================================");
        System.out.println("Java 11 Features Application Started!");
        System.out.println("========================================\n");
        
        // Run Java11Features example directly (already compiled)
        System.out.println("--- Running Java 11 Features Demo ---\n");
        try {
            com.java11features.app.examples.Java11Features.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Error running Java11Features demo: " + e.getMessage());
        }
        
        System.out.println("\n========================================");
        System.out.println("Application Ready!");
        System.out.println("========================================");
        System.out.println("REST API available at: http://localhost:8080/api/java");
        System.out.println("Health check: GET http://localhost:8080/api/java/health");
        System.out.println("\nYou can use the JavaRunnerService to compile and run");
        System.out.println("external Java files dynamically via the REST API.");
        System.out.println("========================================\n");
    }
}
