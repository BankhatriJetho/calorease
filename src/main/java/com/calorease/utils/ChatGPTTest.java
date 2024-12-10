package com.calorease.utils;

public class ChatGPTTest {
    public static void main(String[] args) {
        try {
            // Example prompt
            String prompt = "Write a Java class for a User entity with fields id, name, and email.";
            
            // Call the ChatGPTIntegration utility
            String response = ChatGPTIntegration.sendPrompt(prompt);

            // Print the response
            System.out.println("Response from ChatGPT:");
            System.out.println(response);
        } catch (Exception e) {
            System.err.println("Error occurred while sending the prompt:");
            e.printStackTrace();
        }
    }
}
