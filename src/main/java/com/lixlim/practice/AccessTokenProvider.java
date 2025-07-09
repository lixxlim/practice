package com.lixlim.practice;

import java.util.Random;

public class AccessTokenProvider {

    private AccessTokenProvider() {}

    public static String getAccessToken() {
        StringBuilder uuid = new StringBuilder();
        Random random = new Random();
        String pattern = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx";

        for (char c : pattern.toCharArray()) {
            switch (c) {
                case 'x':
                    uuid.append(Integer.toHexString(random.nextInt(16)));
                    break;
                case 'y':
                    uuid.append(Integer.toHexString((random.nextInt(4) + 8)));
                    break;
                default:
                    uuid.append(c);
                    break;
            }
        }

        return uuid.toString();
    }
}
