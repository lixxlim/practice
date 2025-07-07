package com.lixlim.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PracticeApplication {

	public static void main(String[] args) {
		try {
			String url = "https://challenge-server.tracks.run/hotel-reservation/hotels";
			String accessToken = "776d1945-69a9-4823-9290-c9622115e2db";
			String queryString = "サウス";

			String response = HotelApi.callApiGet(url, accessToken, queryString);
			System.out.println(response);
			ObjectMapper mapper = new ObjectMapper();
			List<Hotel> list = mapper.readValue(response, new TypeReference<List<Hotel>>(){});
			list.forEach(h -> System.out.println(h.getName()));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
