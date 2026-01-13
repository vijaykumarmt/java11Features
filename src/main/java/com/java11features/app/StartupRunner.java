package com.java11features.app;

import com.java11features.app.service.JavaRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

/**
 * CommandLineRunner to demonstrate Java file execution on startup
 */
@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private JavaRunnerService javaRunnerService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========================================");
        System.out.println("Java 11 Features Application Started!");
        System.out.println("========================================\n");
        
        // Get the project directory
        String projectDir = System.getProperty("user.dir");
        System.out.println("Project Directory: " + projectDir);
        
        // Run Java11Features example
        System.out.println("\n--- Running Java 11 Features Demo ---");
        String examplePath = Paths.get(projectDir, "src/main/java/com/java11features/app/examples/Java11Features.java").toString();
        String output = javaRunnerService.compileAndRun(examplePath, "com.java11features.app.examples.Java11Features");
        System.out.println(output);
        
        System.out.println("\n========================================");
        System.out.println("Application Ready!");
        System.out.println("REST API available at: http://localhost:8080/api/java");
        System.out.println("Health check: GET http://localhost:8080/api/java/health");
        System.out.println("========================================\n");
    }
}
