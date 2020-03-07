package com.subrata.poc.pdf;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggerUtil {
    // Reset
    private static final String RESET = "\033[0m";  // Text Reset
    // Regular Colors
    private static final String RED = "\033[0;31m";     // RED
    private static final String GREEN = "\033[0;32m";   // GREEN
    private static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String YELLOW = "\033[0;33m";  // YELLOW

    public static void logInfo(String msg) {
        System.out.println(msg);
    }

    public static void logBanner(String msg) {
        System.out.println(PURPLE + msg + RESET);
    }

    public static void logError(String msg, Throwable e) {
        System.out.println(RED + msg + RESET);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        System.out.println(RED + exceptionAsString + RESET);
    }

    public static void logSuccess(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void logWarning(String msg) {
        System.out.println(YELLOW + msg + RESET);
    }
}
