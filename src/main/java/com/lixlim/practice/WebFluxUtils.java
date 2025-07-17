package com.lixlim.practice;

import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class WebFluxUtils {

    private final WebClient client = WebClient.create();

    public String callApiGet(ApiRequestDto dto) {
        Optional.ofNullable(dto.url())
                .filter(StringUtils::hasLength)
                .orElseThrow(() -> new UrlNotFoundException(dto.toString()));

        Mono<String> response = client.get()
                .uri(uriBuilder -> {
                    UriBuilder builder = uriBuilder.path(dto.url());
                    Optional.ofNullable(dto.queryString())
                            .filter(StringUtils::hasLength)
                            .map(queryString -> URLEncoder.encode(queryString, StandardCharsets.UTF_8))
                            .ifPresent(queryString -> builder.queryParam("keyword", queryString));
                    return builder.build();
                })
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }

    public String callApiPost(ApiRequestDto dto) {
        Optional.ofNullable(dto.url())
                .filter(StringUtils::hasLength)
                .orElseThrow(() -> new UrlNotFoundException(dto.toString()));
        Optional.ofNullable(dto.bodyObject())
                .orElseThrow(() -> new BodyObjectNotFoundException(dto.toString()));

        Mono<String> response = client.post()
                .uri(dto.url())
                .bodyValue(dto.bodyObject())
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }
}
