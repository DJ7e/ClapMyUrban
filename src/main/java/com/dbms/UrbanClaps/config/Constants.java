package com.dbms.UrbanClaps.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Constants {
    public final HashMap<Integer, String> typeUserCode;

    public final String FRONTEND_URL;

    public Constants() {
        typeUserCode = new HashMap<>();
        typeUserCode.put(1, "AUDIENCE");
        typeUserCode.put(2, "MANAGER");
        typeUserCode.put(3, "ADMIN");

        FRONTEND_URL = "http://localhost:3000";
    }

    public static String getRandomString(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
