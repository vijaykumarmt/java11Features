package com.java11features.app.service;

import org.springframework.stereotype.Service;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Service to compile and run Java files dynamically
 */
@Service
public class JavaRunnerService {

    /**
     * Compile a Java source file
     * @param sourceFilePath Path to the Java source file
     * @return true if compilation is successful
     */
    public boolean compileJavaFile(String sourceFilePath) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                throw new RuntimeException("Java compiler not available. Make sure you're running with JDK, not JRE.");
            }

            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = 
                fileManager.getJavaFileObjects(sourceFilePath);
            
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
            boolean success = task.call();
            fileManager.close();
            
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Run a compiled Java class
     * @param className Fully qualified class name
     * @param classpath Classpath for running the class
     * @return Output from running the class
     */
    public String runJavaClass(String className, String classpath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", classpath, className
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            process.waitFor();
            return output.toString();
        } catch (Exception e) {
            return "Error running class: " + e.getMessage();
        }
    }

    /**
     * Compile and run a Java file in one operation
     * @param sourceFilePath Path to the Java source file
     * @param className Fully qualified class name
     * @return Output from running the class
     */
    public String compileAndRun(String sourceFilePath, String className) {
        if (!compileJavaFile(sourceFilePath)) {
            return "Compilation failed for: " + sourceFilePath;
        }

        // Get the directory containing the source file for classpath
        Path sourcePath = Paths.get(sourceFilePath);
        String classpath = sourcePath.getParent().toString();

        return runJavaClass(className, classpath);
    }
}
