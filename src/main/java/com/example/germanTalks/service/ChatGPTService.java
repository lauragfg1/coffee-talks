package com.example.germanTalks.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String apiUrl = "https://api.openai.com/v1/chat/completions";
    private final String model = "gpt-3.5-turbo";

    public String getChatGPTResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        JSONObject body = new JSONObject();
        body.put("model", model);
        body.put("messages", new JSONObject[] { new JSONObject().put("role", "user").put("content", prompt) });

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return extractMessageFromJSONResponse(response.getBody());
        } else {
            throw new RuntimeException("Failed to get response from OpenAI API");
        }
    }

    private String extractMessageFromJSONResponse(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
}
