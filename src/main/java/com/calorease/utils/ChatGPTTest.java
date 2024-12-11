package com.calorease.utils;

public class ChatGPTTest {
    public static void main(String[] args) {
    	String apiKey = System.getenv("OPEN_API_KEY");
    	if(apiKey != null) {
    		System.out.println("API Key successfully retrieved: " + apiKey.substring(0,8) + "...");
    	}else {
    		System.err.println("Failed to retriev API Key. Ensure the environment variable OPENAI_API_KEY is set.");
    	}
        try {
            // Example prompt
            String prompt = "Write a Java class for a User entity with fields id, name, and email.";
            
            // Call the ChatGPTIntegration utility
            String jsonResponse = ChatGPTIntegration.sendPrompt(prompt);
            
            //Extract the generated code
            String generatedCode = ChatGPTIntegration.extractGeneratedCode(jsonResponse);
            
            //Save the generated code to a file
//            String basePath = "src/main/java/";
//            String packagePath = "com/calorease/model";
//            String outputDir =  basePath + packagePath;
//            String fileName = "User.java";
//            ChatGPTIntegration.saveToFile(outputDir, fileName, generatedCode);

            // Print the response
            System.out.println("Generated Code:");
            System.out.println(generatedCode);
        } catch (Exception e) {
            System.err.println("Error occurred while sending the prompt:");
            e.printStackTrace();
        }
    }
}
