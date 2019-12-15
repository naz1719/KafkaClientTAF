package com.project.inftrastructure.utils;

import java.util.regex.Pattern;

public class RegexPatterns {

    public static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    public static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");


    private RegexPatterns(){
        throw new IllegalStateException("RegexPatterns class is not expected to be instantiated");
    }
}
