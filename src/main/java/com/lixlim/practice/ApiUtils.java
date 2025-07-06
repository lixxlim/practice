package com.lixlim.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiUtils {

    /**
     * API Call GET Method
     *
     * @param url api url
     * @param accessToken access token
     * @param params query params
     * @return response body
     * @throws URISyntaxException When URL is wrong
     * @throws IOException When fail to send request by HttpClient
     * @throws InterruptedException When fail to send request by HttpClient
     */
    public static String apiCallByGet(String url, String accessToken, String params) throws URISyntaxException, IOException, InterruptedException {
        // params
        String queryString = "?keyword=" + URLEncoder.encode(params, StandardCharsets.UTF_8) ;

        // execute request
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url + queryString))
                    .header("X-ACCESS-TOKEN", accessToken)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }

    /**
     * API Call POST Method
     *
     * @param url api url
     * @param accessToken access token
     * @param object request dto
     * @return response body
     * @throws JsonProcessingException When fail to convert object to json string
     * @throws URISyntaxException When fail to convert URL to URI
     * @throws IOException When fail to send request by HttpClient
     * @throws InterruptedException When fail to send request by HttpClient
     */
    public static String apiCallByPost(String url, String accessToken, Object object) throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(object);
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .header("X-ACCESS-TOKEN", accessToken)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
}
