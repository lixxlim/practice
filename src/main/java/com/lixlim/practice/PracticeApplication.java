package com.lixlim.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class PracticeApplication {
	public static void main(String[] args) {
		String API_URL = "https://challenge-server.tracks.run/hotel-reservation/hotels";
		String API_TOKEN = getAccessToken();
		String PARAMS = "サウス";

		String response = "";
		try {
			response = ApiUtils.apiCallByGet(API_URL, API_TOKEN, PARAMS);
			ObjectMapper mapper = new ObjectMapper();
			List<Hotel> hotels = mapper.readValue(response, new TypeReference<List<Hotel>>() {});
			String hotelNames = hotels.stream().map(Hotel::getName).collect(Collectors.joining("\n"));
			System.out.println(hotelNames);
		} catch (Exception e) {

			log.error("response=`{}`", response);
			e.printStackTrace();
		}
	}

	private static String getAccessToken() {
		StringBuilder uuid = new StringBuilder();
		Random random = new Random();
		String pattern = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx";

		for (char c : pattern.toCharArray()) {
			switch (c) {
				case 'x':
					uuid.append(Integer.toHexString(random.nextInt(16)));
					break;
				case 'y':
					uuid.append(Integer.toHexString((random.nextInt(4) + 8))); // 8~11 -> hex 8~b
					break;
				default:
					uuid.append(c);
					break;
			}
		}

		return uuid.toString();
	}
}
