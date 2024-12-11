package com.calorease.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;

public class ChatGPTIntegration {
	private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	private static final String API_KEY = System.getenv("OPENAI_API_KEY");
	
	public static String sendPrompt(String prompt) throws Exception {
		//create URl object
		URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        //setup HTTP request properties
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);
        
        // Create the JSON request body
        String requestBody = """
            {
                "model": "gpt-4",
                "messages": [{"role": "user", "content": "%s"}],
                "max_tokens": 1000
            }
            """.formatted(prompt);

        // Send the request
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestBody.getBytes());
            os.flush();
        }

        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        // Return the response as a string
        return response.toString();
	}
	
	//method to extract the content field from the JSON response
	public static String extractGeneratedCode(String jsonResponse) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonResponse);
		return rootNode.path("choices").get(0).path("message").path("content").asText();
	}
	
	//method to save the generated code to a file
	public static void saveToFile(String directory, String fileName,String content) throws Exception {
		File dir = new File(directory);
		if(!dir.exists()) {
			dir.mkdirs();   //creates the directory if it doesn't exist
		}
		
		File file = new File(dir, fileName);
		try(FileWriter writer = new FileWriter(file)) {
			writer.write(content);
			System.out.println("File saved at: " + file.getAbsolutePath());
		}
	}

}
























