package com.lixlim.practice;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiUtils {

    private ApiUtils() {}

    private static final String EQUAL_MARK = "=";
    private static final String AMPERSAND_MARK = "&";
    private static final String QUESTION_MARK = "?";
    private static final String EMPTY_STRING = "";

    /**
     * Call Api as GET Method
     *
     * @param accessToken Access Token
     * @param url API URL
     * @param params API Params
     * @return ResponseBody
     */
    public static String callApiGet(String accessToken, String url, Map<String, String> params) throws IOException, InterruptedException {
        // Response
        HttpResponse<String> response;

        // Prepare Query String
        String queryString = params.entrySet().stream()
                .map(entry -> entry.getKey() + EQUAL_MARK + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.collectingAndThen(
                    Collectors.joining(AMPERSAND_MARK),
                    joinString -> joinString.isEmpty() ? EMPTY_STRING : QUESTION_MARK + joinString
                ));

        // Execute request
        try (HttpClient client = HttpClient.newHttpClient()){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url+ queryString))
                    .header("X-ACCESS-TOKEN", accessToken)
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
        }
        return response.body();
    }
}
