package com.calorease.utils;

public class ChatGPTTest {
    public static void main(String[] args) {
//    	String apiKey = System.getenv("OPENAI_API_KEY");
//    	if(apiKey != null) {
//    		System.out.println("API Key successfully retrieved: " + apiKey.substring(0,8) + "...");
//    	}else {
//    		System.err.println("Failed to retrieve API Key. Ensure the environment variable OPENAI_API_KEY is set.");
//    	}
        try {
            String prompt = "Update the UserDTO class to include a parameterized constructor and a static from() method."
            		+ " The parameterized constructor should accept id, name, and email as arguments. The from() method "
            		+ "should convert a User entity into a UserDTO by mapping the id, name, and email fields.";
            				
            				
            
            // Call the ChatGPTIntegration utility
            String jsonResponse = ChatGPTIntegration.sendPrompt(prompt);
            
            //Extract the generated code
            String generatedCode = ChatGPTIntegration.extractGeneratedCode(jsonResponse);
            
            //Save the generated code to a file
            String basePath = "src/main/java/";
            String packagePath = "com/calorease/calorease/dto";
            String outputDir =  basePath + packagePath;
            String fileName = "UserDTO.java";
            ChatGPTIntegration.saveToFile(outputDir, fileName, generatedCode);

            // Print the response
            System.out.println("Generated Code:");
            System.out.println(generatedCode);
        } catch (Exception e) {
            System.err.println("Error occurred while sending the prompt:");
            e.printStackTrace();
        }
    }
}
