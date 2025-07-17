package com.lixlim.practice;

public record ApiRequestDto(
        String url,
        String accessToken,
        String queryString,
        String jsonString,
        Object bodyObject
) {
}
