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

    private static final HttpClient client = HttpClient.newHttpClient();

    private ApiUtils() {};

    public static String callApiGet(String url, @Nullable String accessToken, @Nullable String queryString)
        throws IOException, InterruptedException, URISyntaxException {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(url + getQueryStringEncoded(queryString)));

        return sendRequest(builder, accessToken);
    }

    public static String callApiPost(String url, @Nullable String accessToken, @Nullable String jsonString)
        throws IOException, InterruptedException, URISyntaxException {

        HttpRequest.BodyPublisher bodyPublisher = Optional.ofNullable(jsonString)
                .filter(body -> !body.isEmpty())
                .map(HttpRequest.BodyPublishers::ofString)
                .orElse(HttpRequest.BodyPublishers.noBody());

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(bodyPublisher)
                .uri(new URI(url));

        return sendRequest(builder, accessToken);
    }

    private static String getQueryStringEncoded(String queryString) {

        if(queryString == null) {
            return Constant.EMPTY_STRING;
        }

        final String queryStringEncoded = URLEncoder.encode(queryString, StandardCharsets.UTF_8);
        return String.format("?keyword=%s", queryStringEncoded);
    }

    private static String sendRequest(HttpRequest.Builder builder, String accessToken) throws IOException, InterruptedException {

        Optional.ofNullable(accessToken)
                .ifPresent(token -> builder.header("X-ACCESS-TOKEN", token));

        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString()).body();
    }
}
