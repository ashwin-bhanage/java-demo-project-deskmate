package com.deskmate.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtil() {
        // prevent instantiation
    }

    public static LocalDateTime parseDateTime(String input) {
        return LocalDateTime.parse(input.trim(), DATE_TIME_FORMAT);
    }

    public static LocalDate parseDate(String input) {
        return LocalDate.parse(input.trim(), DATE_FORMAT);
    }
}
