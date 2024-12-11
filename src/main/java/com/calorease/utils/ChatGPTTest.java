package com.calorease.utils;

public class ChatGPTTest {
    public static void main(String[] args) {
        try {
            // Example prompt
            String prompt = "Write a Java class for a User entity with fields id, name, and email.";
            
            // Call the ChatGPTIntegration utility
            String jsonResponse = ChatGPTIntegration.sendPrompt(prompt);
            
            //Extract the generated code
            String generatedCode = ChatGPTIntegration.extractGeneratedCode(jsonResponse);

            // Print the response
            System.out.println("Generated Code:");
            System.out.println(generatedCode);
        } catch (Exception e) {
            System.err.println("Error occurred while sending the prompt:");
            e.printStackTrace();
        }
    }
}
