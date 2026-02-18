package com.deskmate.utils;

public final class ValidationUtil {

    private ValidationUtil() {
        // prevent instantiation
    }

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " is required");
        }
    }

    public static String normalizePhone(String phone) {
        if (phone == null) {
            throw new ValidationException("Phone is required");
        }

        String cleaned = phone.trim().replaceAll("\\s+", "");

        if (!cleaned.matches("\\d{10}")) {
            throw new ValidationException("Phone must be exactly 10 digits");
        }

        return cleaned;
    }
}
