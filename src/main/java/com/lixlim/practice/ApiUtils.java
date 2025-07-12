package com.lixlim.practice;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class ApiUtils implements ApiClient {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String EMPTY_STRING = "";

    private ApiUtils() {};

    @Override
    public String apiCallGet(String url, String accessToken, String queryString)
            throws URISyntaxException, IOException, InterruptedException {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(url + getEncodedQueryString(queryString)));

        return doApiCall(builder, accessToken);
    }

    @Override
    public String apiCallPost(String url, String accessToken, String jsonString)
            throws URISyntaxException, IOException, InterruptedException {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(getJsonStringOrNoBody(jsonString))
                .uri(new URI(url));

        return doApiCall(builder, accessToken);
    }

    private static HttpRequest.BodyPublisher getJsonStringOrNoBody(String jsonString) {

        return Optional.ofNullable(jsonString)
                .filter(string -> !string.isEmpty())
                .map(HttpRequest.BodyPublishers::ofString)
                .orElse(HttpRequest.BodyPublishers.noBody());
    }

    private static String doApiCall(HttpRequest.Builder builder, String accessToken)
            throws IOException, InterruptedException {

        Optional.ofNullable(accessToken)
                .ifPresent(token -> builder.header("X-ACCESS-TOKEN", accessToken));
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString()).body();
    }

    private static String getEncodedQueryString(String queryString) {

        if (queryString.isBlank()) {
            return EMPTY_STRING;
        }

        return "?keyword=" + URLEncoder.encode(queryString, StandardCharsets.UTF_8);
    }
}
