package com.java11features.app.controller;

import com.java11features.app.service.JavaRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller to expose Java file execution functionality
 */
@RestController
@RequestMapping("/api/java")
public class JavaRunnerController {

    @Autowired
    private JavaRunnerService javaRunnerService;

    /**
     * Compile and run a Java file
     * @param request Request containing source file path and class name
     * @return Response with execution result
     */
    @PostMapping("/run")
    public ResponseEntity<Map<String, String>> runJavaFile(@RequestBody RunRequest request) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String output = javaRunnerService.compileAndRun(
                request.getSourceFilePath(), 
                request.getClassName()
            );
            response.put("status", "success");
            response.put("output", output);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Java Runner Service");
        return ResponseEntity.ok(response);
    }

    /**
     * Request class for running Java files
     */
    public static class RunRequest {
        private String sourceFilePath;
        private String className;

        public String getSourceFilePath() {
            return sourceFilePath;
        }

        public void setSourceFilePath(String sourceFilePath) {
            this.sourceFilePath = sourceFilePath;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
