package com.lixlim.practice;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ApiUtils {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String callApiGet(ApiRequestDto dto) throws IOException, InterruptedException {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(dto.url() + getEncodedQueryString(dto.queryString())));

        return callApi(builder, dto.accessToken());
    };

    public static String callApiPost(ApiRequestDto dto) throws IOException, InterruptedException {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(getBodyPublisher(dto))
                .uri(URI.create(dto.url()));

        return callApi(builder, dto.accessToken());
    }

    private static HttpRequest.BodyPublisher getBodyPublisher(ApiRequestDto dto) {

        return Optional.ofNullable(dto.jsonString())
                .filter(StringUtils::hasText)
                .map(HttpRequest.BodyPublishers::ofString)
                .orElse(HttpRequest.BodyPublishers.noBody());
    }

    private static String getEncodedQueryString(String queryString) {

        return Optional.ofNullable(queryString)
                .filter(StringUtils::hasText)
                .map(str -> "?keyword=" + URLEncoder.encode(str, StandardCharsets.UTF_8))
                .orElse("");
    }

    private static String callApi(HttpRequest.Builder builder) throws IOException, InterruptedException {

        return callApi(builder, null);
    }

    private static String callApi(HttpRequest.Builder builder, String accessToken) throws IOException, InterruptedException{

        Optional.ofNullable(accessToken)
                .ifPresent(token -> builder.header("X-ACCESS-TOKEN", token));

        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString()).body();
    }
}
