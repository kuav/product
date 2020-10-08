package ru.otpbank.service.template.common;

public final class CommonUtils {

    public static String trimUpperOrNull(String str) {
        return str == null ? "": str.toUpperCase().trim();
    }
}
