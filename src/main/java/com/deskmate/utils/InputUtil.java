package com.deskmate.utils;

import java.util.Scanner;

public final class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    private InputUtil() {
        // prevent instantiation
    }

    public static int readInt(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        return Integer.parseInt(input);
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
