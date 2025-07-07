package com.lixlim.practice;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HotelApi {

    private HotelApi() {}

    public static String callApiGet(String url, String accessToken, String queryString) throws Exception {
        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("X-ACCESS-TOKEN", accessToken)
                    .uri(new URI(url + "?keyword=" + URLEncoder.encode(queryString, StandardCharsets.UTF_8)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
}
