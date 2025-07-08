package com.lixlim.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PracticeApplication {

	public static void main(String[] args) throws Exception {
		String url = "https://challenge-server.tracks.run/hotel-reservation/hotels";
		String accessToken = "0211e7a5-de02-4703-b0cc-b01a8a65c511";

		try(HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.uri(new URI(url))
					.header("X-ACCESS-TOKEN", accessToken)
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			ObjectMapper mapper = new ObjectMapper();
			List<Hotel> hotels = mapper.readValue(response.body(), new TypeReference<>(){});
			hotels.stream().map(Hotel::getName).forEach(System.out::println);
		}
	}
}
