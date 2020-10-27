package ru.otpbank.service.product.common;

import java.util.UUID;

public final class CommonUtils {

    public static String trimUpperOrNull(String str) {
        return str == null ? "": str.toUpperCase().trim();
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
