package com.github.ryneal.postcodegeolocation.util;

public class NumberUtil {

    public static Long parseLong(String s) throws NumberFormatException {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Long.parseLong(s);
    }

    public static Double parseDouble(String s) throws NumberFormatException {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Double.parseDouble(s);
    }

}
