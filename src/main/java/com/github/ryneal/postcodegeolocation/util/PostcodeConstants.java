package com.github.ryneal.postcodegeolocation.util;

public class PostcodeConstants {
    public static final String POSTCODE_REGEX = "^(([gG][iI][rR] {0,}0[aA]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y]?[0-9][0-9]?)|(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y])))[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$";
    public static final String POSTCODE_DISTRICT_REGEX = "^[a-zA-Z]+\\d\\d?[a-zA-Z]?";
}
