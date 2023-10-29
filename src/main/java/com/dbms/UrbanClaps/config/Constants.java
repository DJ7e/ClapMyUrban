package com.dbms.UrbanClaps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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

    @Bean
    public static String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
        return formattedDate;
    }

    @Bean
    public static String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
        return formattedDate;
    }

    LocalTime localTime = LocalTime.now();

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
