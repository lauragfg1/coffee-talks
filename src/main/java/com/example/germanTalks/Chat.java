package com.example.germanTalks;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Chat {

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-3.5-turbo";

    public static String chatGPT(String prompt) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Authorization", "Bearer " + API_KEY);
            post.setHeader("Content-Type", "application/json");

            // Construye el cuerpo de la solicitud JSON
            String jsonBody = String.format(
                    "{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                    MODEL, prompt
            );
            post.setEntity(new StringEntity(jsonBody));

            // Ejecuta la solicitud
            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // Procesa la respuesta
                    String responseString = EntityUtils.toString(entity);
                    return extractMessageFromJSONResponse(responseString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private static String extractMessageFromJSONResponse(String response) {
        // Extrae el mensaje del contenido JSON
        int start = response.indexOf("\"content\":\"") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public static void main(String[] args) {
        String response = chatGPT("hello, how are you? Can you tell me what's a Fibonacci Number?");
        System.out.println(response);
    }
}
