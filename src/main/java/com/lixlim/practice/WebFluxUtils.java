package com.lixlim.practice;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class WebFluxUtils implements ApiClient{

    private final WebClient webClient = WebClient.create();

    @Override
    public String apiCallGet(String url, String accessToken, String queryString) throws URISyntaxException, IOException, InterruptedException {

        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("keyword", queryString)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }

    @Override
    public String apiCallPost(String url, String accessToken, String jsonString) throws URISyntaxException, IOException, InterruptedException {

        Mono<String> response = webClient.post()
                .uri(url)
                .bodyValue(jsonString)
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }
}
