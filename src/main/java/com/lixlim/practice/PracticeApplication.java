package com.lixlim.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PracticeApplication {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Please input search word: ");
			String inp = scanner.nextLine();

			String accessToken = "0111e7a5-de02-4703-b0cc-b01a8a65c511";
			String apiUrl = "https://challenge-server.tracks.run/hotel-reservation/hotels";
			Map<String, String> params = new LinkedHashMap<>(Map.of("keyword", inp));
			String api_result = ApiUtils.callApiGet(accessToken, apiUrl, params);

			ObjectMapper mapper = new ObjectMapper();
			List<Hotel> hotels = mapper.readValue(api_result, new TypeReference<List<Hotel>>() {});
			System.out.println(hotels.stream().map(Hotel::getName).collect(Collectors.joining("\n")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
