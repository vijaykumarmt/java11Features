package com.java11features.app.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates Java 11 Features
 */
public class Java11Features {

    public static void main(String[] args) {
        System.out.println("=== Java 11 Features Demo ===\n");
        
        demonstrateVarInLambda();
        demonstrateStringMethods();
        demonstrateFileMethods();
        demonstrateCollectionToArray();
        demonstrateHttpClient();
    }

    /**
     * Java 11 Feature: var keyword in lambda parameters
     */
    private static void demonstrateVarInLambda() {
        System.out.println("1. Var in Lambda Parameters:");
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        // Using var in lambda
        names.forEach((var name) -> System.out.println("  - " + name));
        System.out.println();
    }

    /**
     * Java 11 Feature: New String methods
     */
    private static void demonstrateStringMethods() {
        System.out.println("2. New String Methods:");
        
        // isBlank()
        String blank = "   ";
        System.out.println("  '   '.isBlank(): " + blank.isBlank());
        
        // lines()
        String multiline = "Line 1\nLine 2\nLine 3";
        System.out.println("  Lines in multiline string:");
        multiline.lines().forEach(line -> System.out.println("    " + line));
        
        // strip(), stripLeading(), stripTrailing()
        String padded = "  Hello World  ";
        System.out.println("  Original: '" + padded + "'");
        System.out.println("  Stripped: '" + padded.strip() + "'");
        
        // repeat()
        System.out.println("  'Java'.repeat(3): " + "Java".repeat(3));
        System.out.println();
    }

    /**
     * Java 11 Feature: Files.readString() and Files.writeString()
     */
    private static void demonstrateFileMethods() {
        System.out.println("3. File Methods (readString/writeString):");
        try {
            Path tempFile = Files.createTempFile("java11-demo", ".txt");
            
            // writeString
            Files.writeString(tempFile, "Hello from Java 11!");
            System.out.println("  Written to temp file: " + tempFile);
            
            // readString
            String content = Files.readString(tempFile);
            System.out.println("  Read from file: " + content);
            
            // Clean up
            Files.delete(tempFile);
        } catch (IOException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Java 11 Feature: Collection.toArray(IntFunction)
     */
    private static void demonstrateCollectionToArray() {
        System.out.println("4. Collection.toArray() with IntFunction:");
        List<String> fruits = List.of("Apple", "Banana", "Orange");
        
        // Old way (before Java 11)
        // String[] array = fruits.toArray(new String[0]);
        
        // New way (Java 11)
        String[] array = fruits.toArray(String[]::new);
        System.out.println("  Array: " + String.join(", ", array));
        System.out.println();
    }

    /**
     * Java 11 Feature: New HttpClient API
     */
    private static void demonstrateHttpClient() {
        System.out.println("5. HttpClient API:");
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/zen"))
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            
            System.out.println("  Status Code: " + response.statusCode());
            System.out.println("  GitHub Zen: " + response.body());
        } catch (Exception e) {
            System.out.println("  Note: HttpClient demo requires internet connection");
            System.out.println("  Error: " + e.getMessage());
        }
        System.out.println();
    }
}
