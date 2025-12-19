package com.example.demo.util;

public class ValidationUtil {

    public static boolean validSeason(String season) {
        return season != null &&
               (season.equals("Kharif") ||
                season.equals("Rabi") ||
                season.equals("Summer"));
    }
}
