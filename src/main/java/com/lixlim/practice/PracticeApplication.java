package com.lixlim.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

public class PracticeApplication {

	static Logger logger = Logger.getLogger(PracticeApplication.class.getName());

	public static void main(String[] args) {
		final String url = "https://challenge-server.tracks.run/hotel-reservation/hotels";
		final String accessToken = AccessTokenProvider.getAccessToken();

		try {
			final String responseBody = ApiUtils.callAsGet(url, accessToken, null);
			ObjectMapper mapper = new ObjectMapper();
			List<Hotel> hotels = mapper.readValue(responseBody, new TypeReference<>(){});
			hotels.stream().map(Hotel::getRooms).forEach(System.out::println);

		} catch (URISyntaxException | IOException | InterruptedException ex) {
			logger.severe(ex.getMessage());

		}
    }
}
