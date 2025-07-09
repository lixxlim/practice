package com.lixlim.practice;

import jakarta.annotation.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ApiUtils {

    private ApiUtils() {}

    public static String callAsGet(String url,
                                   @Nullable String accessToken,
                                   @Nullable String queryString)
            throws URISyntaxException, IOException, InterruptedException {

        // Prepare QueryString
        final String queryStringEncoded = URLEncoder.encode(
                Optional.ofNullable(queryString).orElse(Const.EMPTY_STRING),
                StandardCharsets.UTF_8
        );

        try(HttpClient client = HttpClient.newHttpClient()) {
            // Prepare Request
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(url + queryStringEncoded));
            Optional.ofNullable(accessToken)
                    .ifPresent(token -> builder.header(Const.X_ACCESS_TOKEN, token));

            // Call API
            HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

            // Return ResponseBody
            return response.body();
        }
    }

    public static String callApiPost(String url,
                                     @Nullable String accessToken,
                                     @Nullable String jsonString)
            throws URISyntaxException, IOException, InterruptedException {

        try(HttpClient client = HttpClient.newHttpClient()) {
            // Prepare JsonString as BodyPublisher
            HttpRequest.BodyPublisher bodyPublisher = Optional.ofNullable(jsonString)
                    .filter(body -> !body.isBlank())
                    .map(HttpRequest.BodyPublishers::ofString)
                    .orElse(HttpRequest.BodyPublishers.noBody());

            // Prepare Request
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .POST(bodyPublisher)
                    .uri(new URI(url));

            // AccessToken
            Optional.ofNullable(accessToken)
                    .ifPresent(token -> builder.header(Const.X_ACCESS_TOKEN, token));

            // MediaType
            Optional.ofNullable(jsonString)
                    .ifPresent(json -> builder.header(Const.CONTENT_TYPE, Const.APPLICATION_JSON));

            // Call API
            HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

            // Return ResponseBody
            return response.body();
        }
    }
}
